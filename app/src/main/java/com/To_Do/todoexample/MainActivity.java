package com.To_Do.todoexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import com.To_Do.todoexample.Adapter.ToDoAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclertodo;
    ImageView imgadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclertodo = findViewById(R.id.recyclertodo);
        imgadd = findViewById(R.id.imgadd);


        recyclertodo.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

         if(getIntent().hasExtra("task")){
             ArrayList<String> text;
             if(Constants.getArrayList(MainActivity.this,"task")==null){
                 text = new ArrayList<>();
             }else {
                 text = Constants.getArrayList(MainActivity.this,"task");
             }

             text.add(getIntent().getStringExtra("task"));

             Constants.saveArrayList(text,"task",MainActivity.this);
             recyclertodo.setAdapter(new ToDoAdapter(text,MainActivity.this));
         }else {
             ArrayList<String> text = null;
             if(Constants.getArrayList(MainActivity.this,"task")==null){
                 text = new ArrayList<>();
             }else {
                 text = Constants.getArrayList(MainActivity.this,"task");
             }
             recyclertodo.setAdapter(new ToDoAdapter(text,MainActivity.this));
         }




        imgadd.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this,Task_Activity.class);
            startActivity(intent);
        });
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}