package com.To_Do.todoexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class Task_Activity extends AppCompatActivity {

    TextInputEditText txtInputEdit;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        txtInputEdit = findViewById(R.id.txtInputEdit);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtInputEdit.getText().toString().isEmpty()){
                    txtInputEdit.setError("Please enter yout task");
                    txtInputEdit.requestFocus();
                }else {
                    Intent intent = new Intent(Task_Activity.this,MainActivity.class);
                    intent.putExtra("task",txtInputEdit.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}