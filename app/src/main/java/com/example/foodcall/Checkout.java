package com.example.foodcall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Checkout extends AppCompatActivity {

    private static final String TAG = "Checkout";
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> count = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_checkout);

        initRecycler();
    }

    void initRecycler() {
        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.itemsList);
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(itemName, count, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
