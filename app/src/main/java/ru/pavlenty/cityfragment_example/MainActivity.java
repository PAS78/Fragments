package ru.pavlenty.cityfragment_example;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import ru.pavlenty.cityfragment_example.fragments.CityDetail;
import ru.pavlenty.cityfragment_example.fragments.MenuDetail;

// Имплементируемся от Адаптера для реализации кликов (onCityItemSelected)
public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Работаем с фрагментом
        // Если это первый запуск
        if (savedInstanceState == null) {
            // Создаем объект фрагмента
            MenuDetail md = new MenuDetail();
            // Открываем транзакцию
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Добавляем в flContainer RecyclerView с Меню
            ft.add(R.id.flContainer, md);
            // Подтверждает транзакцию
            ft.commit();
        }

        // Если ориентация альбомная сразу заполняем и flContainer2 0-позицией Города
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            CityDetail cdet = new CityDetail();
            Bundle cdet_args = new Bundle();
            cdet_args.putInt("position", 0);
            cdet.setArguments(cdet_args);
            FragmentTransaction ft_cdet = getSupportFragmentManager().beginTransaction();
            ft_cdet.add(R.id.flContainer2, cdet);
            ft_cdet.commit();
        }

    }

    // Реализауем Интерфейс MyAdapter.ItemClickListener
    // Обработка клика на меню
    @Override
    public void onCityItemSelected(int position) {
        CityDetail cd = new CityDetail();
        FragmentTransaction ftsel = getSupportFragmentManager().beginTransaction();

        // Посылка с выбранным Городом (для отоброжения в горизонтальном режиме)
        Bundle args = new Bundle();
        args.putInt("position", position);
        cd.setArguments(args);

        // Заполняем нужный фрейм от положение экрана
        // Если вертикальное, то заменяем второй контенер на содержимое фрагмента CityDetail
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ftsel.replace(R.id.flContainer2, cd).commit();
            // Если вертикальное то заменяем единый контейнер
        } else {
            // При вертикальном экране нужна кнопка Назад
            ftsel.replace(R.id.flContainer, cd).addToBackStack(null).commit();
        }

    }
}