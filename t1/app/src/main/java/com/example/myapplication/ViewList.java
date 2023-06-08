package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewList extends AppCompatActivity {

    TextView test ;
    ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        test = (TextView) findViewById(R.id.testView);
        loadData();
        displayList();


    }

    private void displayList() {
        String s = "";
        for (String item : items)
        {
            s += item+"\n";
        }
        test.setText(s);
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