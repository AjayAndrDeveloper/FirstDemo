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


    public TextFragment() {

        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        recyclerView = view.findViewById(R.id.textRecycleView);
        Intent intent = getActivity().getIntent();
        String currentCategory = intent.getStringExtra("categoryName");
        ArrayList<Category> dataQuotes = QuoteActivity.getQoutes(currentCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new QuoteAdapter(dataQuotes, getContext()));

        // Inflate the layout for this fragment
        return view;
    }
}