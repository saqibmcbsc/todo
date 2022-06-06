package com.To_Do.todoexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;

import com.To_Do.todoexample.Adapter.ToDoAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

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


             //Give to arrays find which no in not present in second arrays
             findmissing();
             // remove duplicate value from array
             removeDuplicate();
         }




        imgadd.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this,Task_Activity.class);
            startActivity(intent);
        });
    }



    static void findmissing() {
        int[] a = {1,2,3,4,5};
        int[] b = {2,3,1,0,5};

        for(int i = 0; i<a.length; i++) {

            int j = 0;

            for( j = 0; j<b.length; j++)
                if(a[i] == b[j])
                    break;

            if(j == b.length)
            Log.d("TAG", "findmissing: "+a[i]);

        }


    }

    //duplicate array remove
    public static void removeDuplicate() {
        int[] c = {1,2,3,4,2,5,3};

        HashSet<Integer> hs = new HashSet<>();

        for(int i = 0; i<c.length; i++) {

            hs.add(c[i]);

        }

        for(int no: hs) {
            Log.d("TAG", "removeDuplicate: "+no+ "\t");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}