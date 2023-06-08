package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddListItem extends Activity {

    EditText editText;
    Button addButton;

    ArrayList<String> items = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_item);

        editText = (EditText) findViewById(R.id.addItemText);
        addButton = (Button) findViewById(R.id.addButton);

        loadData();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(editText.getText().toString());
            }
        });
    }

    private void saveData(String li) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        items.add(li);
        String json = gson.toJson(items);
        editor.putString("list_items",json);
        editor.apply();
        loadData();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("list_items",null);

        Type type = new TypeToken<ArrayList<String>>()
        {

        }.getType();

        items=gson.fromJson(json,type);

        if(items==null) {
            items=new ArrayList<String>();
        }
    }
}