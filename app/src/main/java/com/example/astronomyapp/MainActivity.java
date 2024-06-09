package com.example.astronomyapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;
    Button feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        feedback =(Button) findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String UriText = "mailto:" +      Uri.encode("s.muniroff2018@yandex.ru") +"?subject="+
                        Uri.encode("Отзывы и предложения") ;
                Uri uri = Uri.parse(UriText);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent,"Обратная связь"));
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();
        androidData = new DataClass("Солнце", R.string.solar, " ", R.drawable.solar_detail);
        dataList.add(androidData);
        androidData = new DataClass("Земля", R.string.earth, " ", R.drawable.earth_detail);
        dataList.add(androidData);
        androidData = new DataClass("Черная дыра", R.string.black_hole, " ", R.drawable.black_hole_detail);
        dataList.add(androidData);
        androidData = new DataClass("Вслененная", R.string.universe, " ", R.drawable.universe_detail);
        dataList.add(androidData);
        androidData = new DataClass("Меркурий", R.string.mercury, " ", R.drawable.mercury_detail);
        dataList.add(androidData);
        androidData = new DataClass("Венера", R.string.venus, " ", R.drawable.venus_detail);
        dataList.add(androidData);
        androidData = new DataClass("Марс", R.string.mars, " ", R.drawable.mars_detail);
        dataList.add(androidData);
        androidData = new DataClass("Юпитер", R.string.jupiter, " ", R.drawable.jupiter_detail);
        dataList.add(androidData);
        androidData = new DataClass("Уран", R.string.uranus, " ", R.drawable.uranus_detail);
        dataList.add(androidData);
        androidData = new DataClass("Нептун", R.string.neptune, " ", R.drawable.neptune_detail);
        dataList.add(androidData);
        androidData = new DataClass("Сатурн", R.string.saturn, " ", R.drawable.saturn_detail);
        dataList.add(androidData);
        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }


    private void searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }

}

