package ru.pavlenty.cityfragment_example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.pavlenty.cityfragment_example.MyAdapter;
import ru.pavlenty.cityfragment_example.R;

public class MenuDetail extends Fragment {

    MyAdapter adapter;
    public List<String> cityMenu = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityMenu = Arrays.asList(getResources().getStringArray(R.array.cities));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_detail, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // гарантия того что view создана (не будет null point)
        RecyclerView recyclerView = view.findViewById(R.id.rvCities);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MyAdapter(view.getContext(), cityMenu);
        adapter.setClickListener((MyAdapter.ItemClickListener) view.getContext());
        recyclerView.setAdapter(adapter);
    }

}
