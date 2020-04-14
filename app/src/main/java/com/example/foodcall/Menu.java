package com.example.foodcall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Menu extends AppCompatActivity {

    ListView listView;
    ListAdapter l;
    ArrayList<menu_class> arraylist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listView = (ListView) findViewById(R.id.listview);

        feedData(arraylist);

        final Adapter_menu arrayAdapter = new Adapter_menu(this, R.layout.activity_adapter_menu, arraylist);
        listView.setAdapter(arrayAdapter);

    }


    public void feedData(ArrayList<menu_class> menu_classes){
        menu_class obj1 = new menu_class("Cheese Burger","300");
        menu_class obj2 = new menu_class("Chicken Burger","270");
        menu_class obj3 = new menu_class("Nuggets","350");
        menu_class obj4 = new menu_class("Sundae","320");
        menu_class obj5 = new menu_class("Big Mac","400");

        menu_classes.add(obj1);
        menu_classes.add(obj2);
        menu_classes.add(obj3);
        menu_classes.add(obj4);
        menu_classes.add(obj5);
    }

}
