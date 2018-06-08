package com.example.sprath.aquent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Model> modelArrayList;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(rawjson());
            JSONArray jsonArray = jsonObject.getJSONArray("acquaint");
            for(int i=0;i<=jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                JSONArray array = object.getJSONArray("course_name");
                for(int j=0;j<=array.length();j++){

                    JSONObject object1 = array.getJSONObject(j);

                    modelArrayList.add(new Model(
                            object1.getString("co_name")
                    ));
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(),modelArrayList);
        recyclerView.setAdapter(recyclerAdapter);
    }





    public String rawjson() {
        String JSONString = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.raws);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((JSONString = bufferedReader.readLine()) != null) {
                stringBuilder.append(JSONString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(stringBuilder);
    }


}
