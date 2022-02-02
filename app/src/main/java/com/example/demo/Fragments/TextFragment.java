package com.example.demo.Fragments;

import static com.example.demo.Activity.QuoteActivity.getQoutes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demo.Activity.QuoteActivity;
import com.example.demo.Adapter.QuoteAdapter;
import com.example.demo.Model.Category;
import com.example.demo.R;

import java.util.ArrayList;


public class TextFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Category> quotesArray = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TextFragment() {

        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
           recyclerView = view.findViewById(R.id.textRecycleView);
        Intent intent = getActivity().getIntent();
        String currentCategory = intent.getStringExtra("categoryName");
     ArrayList<Category> dataQuotes= QuoteActivity.getQoutes(currentCategory);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new QuoteAdapter(dataQuotes,getContext()));

        // Inflate the layout for this fragment
        return view;
    }
}