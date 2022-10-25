package ru.pavlenty.cityfragment_example.fragments;
// Выводит описание города

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.pavlenty.cityfragment_example.R;


public class CityDetail extends Fragment {
    int position = 0;
    TextView tvTitle;
    TextView tvDetails;
    public List<String> cityMenu = new ArrayList<>();
    public List<String> cityDesc = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityMenu = Arrays.asList(getResources().getStringArray(R.array.cities));
        cityDesc = Arrays.asList(getResources().getStringArray(R.array.descriptions));

        // Получаем позицию (номер) аргументов
        if (savedInstanceState == null) {
            if (getArguments() != null) {
                position = getArguments().getInt("position", 0);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_detail, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Заполняем элементы с учетом полученной позиции
        tvTitle = view.findViewById(R.id.tvTitle);
        tvDetails = view.findViewById(R.id.tvDetails);
        tvTitle.setText(cityMenu.get(position));
        tvDetails.setText(cityDesc.get(position));
    }


}
