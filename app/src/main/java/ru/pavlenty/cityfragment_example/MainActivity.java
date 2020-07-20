package ru.pavlenty.cityfragment_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

import ru.pavlenty.cityfragment_example.fragments.CityDetail;
import ru.pavlenty.cityfragment_example.fragments.MenuDetail;


public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            MenuDetail md = new MenuDetail();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.flContainer,md);
            ft.commit();
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            CityDetail cdet = new CityDetail();
            Bundle cdet_args = new Bundle();
            cdet_args.putInt("position",0);
            cdet.setArguments(cdet_args);
            FragmentTransaction ft_cdet = getSupportFragmentManager().beginTransaction();
            ft_cdet.add(R.id.flContainer2,cdet);
            ft_cdet.commit();
        }

    }

    @Override
    public void onCityItemSelected(int position) {
        CityDetail cd = new CityDetail();
        FragmentTransaction ftsel = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putInt("position",position);
        cd.setArguments(args);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ftsel.replace(R.id.flContainer2,cd).commit();
        } else {
            ftsel.replace(R.id.flContainer, cd).addToBackStack(null).commit();
        }

    }
}