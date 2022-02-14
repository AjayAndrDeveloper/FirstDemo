package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.demo.Adapter.VideoListAdapter;
import com.example.demo.Model.VideoFolderModel;
import com.example.demo.R;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {
    VideoView videoView;
    float curBrightnessValue;
    float brightStartY, brightEndY,volumeStartY,volumeEndY;
    int position;
    ArrayList<VideoFolderModel> nameList;
    //    ImageView backImage , lockImage;
    boolean flag = true;
    RelativeLayout zoomLayout, coverLayout, leftForBrightness, rightForVolume;
    boolean isOpen = true, isEnable = true;
    int brightness;
    private Window window;
    private ContentResolver contentResolver;

    //    following Items from custom_controls
    LinearLayout videoView_one_layout, videoView_two_layout,
            videoView_three_layout, videoView_four_layout, video_five_layout, videoView_lock_screen, video_unlock_layout, videoView_rotation;
    ImageButton goBack, menu, forward, rewind, playPause;
    TextView videoView_title, videoView_endTime;
    SeekBar videoView_brightness, videoView_seekbar;
    int brightnessValue;
    WindowManager.LayoutParams lp;
    AudioManager audioManager  ;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        }
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        videoView = findViewById(R.id.videoView2);
        leftForBrightness = findViewById(R.id.leftForBrightness);
        rightForVolume = findViewById(R.id.rightForVolume);
//       lp = getWindow().getAttributes();
//        lp.screenBrightness = 1.0f; // 0.0 - 1.0
//        getWindow().setAttributes(lp);


        //    following Items from custom_controls
        zoomLayout = findViewById(R.id.zoom_layout);
        videoView_one_layout = findViewById(R.id.videoView_one_layout);
//        videoView_two_layout = findViewById(R.id.videoView_two_layout);
        videoView_three_layout = findViewById(R.id.videoView_three_layout);
        videoView_four_layout = findViewById(R.id.videoView_four_layout);
        video_five_layout = findViewById(R.id.video_five_layout);
        videoView_lock_screen = findViewById(R.id.videoView_lock_screen);
        video_unlock_layout = findViewById(R.id.video_unlock_layout);
        videoView_rotation = findViewById(R.id.videoView_rotation);
        goBack = findViewById(R.id.videoView_go_back);
        menu = findViewById(R.id.videoView_more);
        rewind = findViewById(R.id.videoView_rewind);
        forward = findViewById(R.id.videoView_forward);
        playPause = findViewById(R.id.videoView_play_pause_btn);
        videoView_title = findViewById(R.id.videoView_title);
        videoView_endTime = findViewById(R.id.videoView_endTime);
        videoView_seekbar = findViewById(R.id.videoView_seekbar);
//        videoView_brightness = findViewById(R.id.videoView_brightness);
        coverLayout = findViewById(R.id.coverLayout);

        
        rightForVolume.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ((event.getAction())){
                    case MotionEvent.ACTION_DOWN:{
                        volumeStartY = event.getY();
                        return true;
                    }
                    case MotionEvent.ACTION_UP:{
                        if (isOpen) {
                            hideDefaultControls();
                            isOpen = false;
                        } else {
                            showDefaultControls();
                            isOpen = true;
                        }
                        return true;
                        
                    }
                    case MotionEvent.ACTION_MOVE:{
                        volumeEndY = event.getY();
                        volumeCountY(volumeStartY,volumeEndY);
                    }
                }
                return false;
            }

            private void volumeCountY(float volumeStartY, float volumeEndY) {
                if (volumeStartY > volumeEndY) {
                    increaseVolume();
                }else if(volumeStartY<volumeEndY){
                    decreaseVolume();
                }
            }

            private void decreaseVolume() {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);

            }

            private void increaseVolume() {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }
        });
        leftForBrightness.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ((event.getAction())) {
                    case MotionEvent.ACTION_DOWN: {
                        brightStartY = event.getY();
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        if (isOpen) {
                            hideDefaultControls();
                            isOpen = false;
                        } else {
                            showDefaultControls();
                            isOpen = true;
                        }
                        return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        brightEndY = event.getY();
                        brightCountY(brightStartY, brightEndY);
                        Log.d("video123", "onTouch: " + brightStartY + "  hello" + brightEndY);
                        return true;
                    }
                }
                return false;
            }
        });


        zoomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    hideDefaultControls();
                    isOpen = false;
                } else {
                    showDefaultControls();
                    isOpen = true;
                }
            }

        });
        videoView_rotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int orientation = getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                }
            }
        });
        coverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnable) {
                    video_unlock_layout.setVisibility(View.GONE);
                    isEnable = false;
                } else {
                    video_unlock_layout.setVisibility(View.VISIBLE);
                    isEnable = true;
                }

            }
        });
        videoView_lock_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                video_five_layout.setVisibility(View.VISIBLE);
                hideDefaultControls();
                flag = false;
                coverLayout.setVisibility(View.VISIBLE);
            }
        });
//        video_five_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isOpen){
//                    video_five_layout.setVisibility(View.GONE);
//                    isOpen=true;
//                }else{
//                    video_five_layout.setVisibility(View.VISIBLE);
//                    isOpen=false;
//                }
//            }
//        });
        video_unlock_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                video_five_layout.setVisibility(View.GONE);
                coverLayout.setVisibility(View.GONE);
                showDefaultControls();
                flag = true;

            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              1000msec=1sec  10000msec = 10sec
                videoView.seekTo(videoView.getCurrentPosition() - 10000);
            }
        });
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    playPause.setImageResource(R.drawable.ic_baseline_play_circle_outline);
                } else {
                    videoView.start();
                    playPause.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(videoView.getCurrentPosition() + 10000);
            }
        });
//        backImage = findViewById(R.id.backBtn);
//        lockImage = findViewById(R.id.lock);
        Intent intent = getIntent();
        String videoPath = intent.getStringExtra("video");
        position = intent.getIntExtra("position", 0);
        ArrayList<String> PathList = intent.getStringArrayListExtra("PathList");
        nameList = (ArrayList<VideoFolderModel>) intent.getSerializableExtra("arrayList");
        videoView_title.setText(nameList.get(position).getDisplay_name());
        videoView.setVideoPath(videoPath);
        videoView.start();

//         =@+@+@+@+@+@+@+@+@+@+@+@+@+@+@+@+@+@+@+@+
//        for the default MediaControl
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//        if (PathList.size()>1) {
//           F mediaController.setPrevNextListeners(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Handle next click here
//                    ++position;
//                    videoView.setVideoPath(PathList.get(position));
//                    videoView.start();
//                }
//            }, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Handle previous click here
//                    --position;
//                    videoView.setVideoPath(PathList.get(position));
//                    videoView.start();
//                }
//            });
//        }
//        videoView.setMediaController(mediaController);
//
//        videoView.start();


//            window = getWindow();
//        //Getting Current screen brightness.
//        brightness = Settings.System.getInt(getApplicationContext().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,0);
//        videoView_brightness.setProgress(brightness);
//        videoView_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Settings.System.putInt(getApplicationContext().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,progress);
//
//                if(progress<=30)
//                {
//                    //Set the brightness to 20
//                    brightness=30;
//                }
//                else //brightness is greater than 20
//                {
//                    //Set brightness variable based on the progress bar
//                    brightness = progress;
//                }
//                //Calculate the brightness percentage
////                float perc = (brightness /(float)255)*100;
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
//           WindowManager.LayoutParams layoutParams = window.getAttributes();
//                layoutParams.screenBrightness = brightness / (float)255;
//                window.setAttributes(layoutParams);
//
//
//
//            }
//        });
        initializeSeekBars();
        setHandler();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView_seekbar.setMax(videoView.getDuration());
//                videoView_brightness.setMax(255);
//                curBrightnessValue = 0;
//                getWindow().setAttributes(lp);
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("Ajay", "onCompletion: " + "position" + position + "Path size" + PathList.size());
                if (position == PathList.size() - 1) {
                    onBackPressed();
                } else {
                    ++position;
                    videoView.setVideoPath(PathList.get(position));
                    videoView_title.setText(nameList.get(position).getDisplay_name());
                    videoView.start();
                }
            }
        });

//        videoView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//               if (listener) {
//                   mediaController.setVisibility(View.GONE);
//                   backImage.setVisibility(View.GONE);
//                   listener=false;
//               }else {
//                   mediaController.setVisibility(View.VISIBLE);
//                   backImage.setVisibility(View.VISIBLE);
//                   listener=true;
//               }
//                return true;
//            }
//        });
//        videoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener) {
////                   mediaController.setVisibility(View.GONE);
//                   backImage.setVisibility(View.GONE);
//                   listener=false;
//               }else {
////                   mediaController.setVisibility(View.VISIBLE);
//                   backImage.setVisibility(View.VISIBLE);
//                   listener=true;
//               }
//            }
//        });
//        backImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        lockImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (flag) {
//                    mediaController.setVisibility(View.GONE);
//                    backImage.setVisibility(View.GONE);
//                    lockImage.setImageResource(R.drawable.ic_baseline_lock_24);
//                    flag =false ;
//                }else {
//                    mediaController.setVisibility(View.VISIBLE);
//                    backImage.setVisibility(View.VISIBLE);
//                    lockImage.setImageResource(R.drawable.ic_baseline_lock_open_24);
//                    flag =true ;
//                }
//            }
//        });


//        ending onCreateMethod
    }

    private void brightCountY(float startY, float endY) {
        if (startY > endY) {
            increase();
            Toast.makeText(VideoActivity.this, "UP", Toast.LENGTH_SHORT).show();


        } else if (startY < endY) {
            decrease();
            Toast.makeText(VideoActivity.this, "down", Toast.LENGTH_SHORT).show();


        }
    }

    private void initializeSeekBars() {
        videoView_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress);
                    videoView.start();


                    int currentPosition = videoView.getCurrentPosition();
                    videoView_endTime.setText("" + VideoListAdapter.convertMillieToHMmSs(videoView.getDuration() - currentPosition));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    private void setHandler() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (videoView.getDuration() > 0) {
                    int currentPosition = videoView.getCurrentPosition();
                    videoView_seekbar.setProgress(currentPosition);
                    videoView_endTime.setText(" " + VideoListAdapter.convertMillieToHMmSs(videoView.getDuration() - currentPosition));
                }
                handler.postDelayed(this, 0);
            }
        };
        handler.postDelayed(runnable, 500);
    }

    //  For the Show And Hide the Items_-=---=-=-=-==-=-====-========-------===========--------=>
    private void hideDefaultControls() {
        videoView_one_layout.setVisibility(View.GONE);
//        videoView_two_layout.setVisibility(View.GONE);
        videoView_three_layout.setVisibility(View.GONE);
        videoView_four_layout.setVisibility(View.GONE);


    }

    private void showDefaultControls() {
        videoView_one_layout.setVisibility(View.VISIBLE);
//        videoView_two_layout.setVisibility(View.VISIBLE);
        videoView_three_layout.setVisibility(View.VISIBLE);
        videoView_four_layout.setVisibility(View.VISIBLE);

    }


    @Override
    public void onBackPressed() {
        if (flag) {
            super.onBackPressed();

        } else {
            video_unlock_layout.setVisibility(View.VISIBLE);
            Toast.makeText(VideoActivity.this, "Your Screen is lock", Toast.LENGTH_SHORT).show();
        }
    }


    private void changeScreenBrightness(Context applicationContext, int brightnessValue) {
        Settings.System.putInt(
                applicationContext.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL

        );
        Settings.System.putInt(
                getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS,
                brightnessValue);

    }

    public void decrease() {
        if (brightnessValue >= 10) {
            brightnessValue -= 10;

            changeScreenBrightness(getApplicationContext(), brightnessValue);
            Log.d("increase", "increase: " + "brightnessValue" + brightnessValue + (brightnessValue * 100) / 250 + "%");

        }
    }

    public void increase() {
        if (brightnessValue <= 245) {
            brightnessValue += 10;
//            brightnessValue = brightnessValue / 100;

            changeScreenBrightness(getApplicationContext(), brightnessValue);
            Log.d("increase", "increase: " + "brightnessValue" + brightnessValue + (brightnessValue * 100) / 250 + "%");
        }

    }
}

