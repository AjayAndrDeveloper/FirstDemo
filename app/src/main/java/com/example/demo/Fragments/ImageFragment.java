package com.example.demo.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demo.Adapter.ImageAdapter;
import com.example.demo.R;

import java.util.ArrayList;


public class ImageFragment extends Fragment {

RecyclerView recyclerView; ArrayList<String> imageList;



    public ImageFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        recyclerView = view.findViewById(R.id.imageRecyclerView);
        imageList = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        String currentCategory = intent.getStringExtra("categoryName");
        switch (currentCategory){
            case "Happy":
                imageList.add("https://i.pinimg.com/736x/9a/9a/7f/9a9a7f0b8ef9fbf56645ee753e5f2746.jpg");
                imageList.add("data:image/jpeg;base64,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");
                imageList.add("https://images.unsplash.com/photo-1522363757703-61225a8e04ab?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8aGFwcHklMjBxdW90ZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");

                break;
            case "Love":
                imageList.add("https://images.unsplash.com/photo-1582319990242-6476d9c447ea?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8bG92ZSUyMHF1b3Rlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
                imageList.add("https://images.unsplash.com/photo-1564121211835-e88c852648ab?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fGxvdmUlMjBxdW90ZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
                break;
            case "Motivational":
                imageList.add("https://media.istockphoto.com/photos/dreams-dont-work-unless-you-do-inspiring-creative-motivation-quote-picture-id1350192262?b=1&k=20&m=1350192262&s=170667a&w=0&h=1VZGX4KmHkINbar4xCSsHaLmkaZGUCN-Bd_xkPeyl9I=");
                imageList.add("https://media.istockphoto.com/photos/aspiration-concept-picture-id1355553989?b=1&k=20&m=1355553989&s=170667a&w=0&h=TkUwZkHLkgzTv7ONq-QBCOSyfvfSFGnLseJglkcg5jk=");
                imageList.add("https://images.unsplash.com/photo-1617251137884-f135eccf6942?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bW90aXZhdGlvbmFsJTIwcXVvdGVzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
                    break;

            case "Positive":
                imageList.add("https://media.istockphoto.com/photos/yes-you-can-motivational-inspirational-message-on-sand-picture-id1003163388?b=1&k=20&m=1003163388&s=170667a&w=0&h=blFYVKvdfgt98jDgGwxjjkg1s_pQvRjRPGygwmBZ3ZE=");
                imageList.add("https://images.unsplash.com/photo-1515288207449-100e125abccb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8cG9zaXRpdmUlMjBxdW90ZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");


                imageList.add("https://images.unsplash.com/photo-1534196511436-921a4e99f297?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8cG9zaXRpdmUlMjBxdW90ZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
                break;
            case "inspirational":
                imageList.add("https://media.istockphoto.com/photos/the-best-way-to-predict-the-future-is-to-create-it-motivational-quote-picture-id1304357935?b=1&k=20&m=1304357935&s=170667a&w=0&h=tflkvGz1i1XroFMBz4chm33aWj8UbmehLDKjeuhJ-pk=");
                imageList.add("https://images.unsplash.com/photo-1548438294-1ad5d5f4f063?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8aW5zcGlyYXRpb25hbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");

                imageList.add("https://images.unsplash.com/photo-1564410267841-915d8e4d71ea?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aW5zcGlyYXRpb25hbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
                break;
            case "Sad":
                imageList.add("https://images.unsplash.com/photo-1542986130-28287a25eae7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8c2FkJTIwcXVvdGVzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
                imageList.add("https://images.unsplash.com/photo-1588031710631-aa2488c34ca7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8c2FkJTIwcXVvdGVzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
                imageList.add("https://images.unsplash.com/photo-1596726037118-aa3629a6930e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c2FkJTIwcXVvdGVzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");

               break;
            case "Deep Relationship":
                imageList.add("https://images.unsplash.com/photo-1518406479616-cd3f1cde0a50?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZGVlcHJlbGF0aW9uc2hpcCUyMHF1b3Rlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
                imageList.add("https://images.unsplash.com/photo-1524724572488-bee7947c6a29?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fGRlZXByZWxhdGlvbnNoaXAlMjBxdW90ZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");

                imageList.add("https://images.unsplash.com/photo-1479107574602-b42882f52baa?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8ZGVlcHJlbGF0aW9uc2hpcCUyMHF1b3Rlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
                break;
            case "Gratitude":
                imageList.add("https://images.unsplash.com/photo-1616442751986-fe0df84ad730?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjB8fGdyYXRpdHVkZSUyMHF1b3Rlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
                imageList.add("https://media.istockphoto.com/photos/hope-acronym-concept-picture-id1269365067?b=1&k=20&m=1269365067&s=170667a&w=0&h=qonvtKbij2s5OBf59INY89PKwSExC9qC8q3Z8EU5qjo=");
                imageList.add( "https://media.istockphoto.com/photos/morning-coffee-with-positive-message-picture-id1309254970?b=1&k=20&m=1309254970&s=170667a&w=0&h=cac-hC39nYRrkOmloVlZKHIdrlvyCnFiPQSrT-QL0mo=");
                break;

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ImageAdapter(imageList,getContext()));
        return view;

    }
}