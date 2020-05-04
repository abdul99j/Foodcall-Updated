package com.example.foodcall.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcall.AddRestaurantItem;
import com.example.foodcall.Data;
import com.example.foodcall.Item;
import com.example.foodcall.Login_SignUp;
import com.example.foodcall.Menu;
import com.example.foodcall.R;
import com.example.foodcall.RecyclerView_CustomerView;
import com.example.foodcall.RecyclerView_Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String TAG = "Home Fragment";

    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<String> mImageIcons = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();

    List<Data> list=new ArrayList<>();

    private HomeViewModel homeViewModel;
    private ImageView imageView;
    String uid;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        Intent i = getActivity().getIntent();
//        Bundle extras = i.getExtras();
//        String temp = extras.get("bool").toString();

        //if (temp.equals("true")) {
        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        imageView = (ImageView) root.findViewById(R.id.res_img);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Menu.class);
//                startActivity(intent);
//            }
//        });

        Data obj1 = new Data(R.drawable.kfc,"KFC DHA","Free Delivery");
        Data obj2 = new Data(R.drawable.kfc,"McDonalds","50 Delivery");
//        mImageUrls.add(R.drawable.kfc);
//        mNames.add("KFC DHA");
//        mPrice.add("Free Delivery");
//
//        mImageUrls.add(R.drawable.mcdonalds);
//        mNames.add("McDonalds DHA");
//        mPrice.add("Rs. 50 per delivery");


        list.add(obj1);
        list.add(obj2);

        initRecyclerView(root);
//        }
//        else
//        {
//            root = inflater.inflate(R.layout.activity_main__restaurant, container, false);
//            FloatingActionButton fab = root.findViewById(R.id.floatingActionButton);
//
//            //Add restaurant item button
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(getContext(), AddRestaurantItem.class);
//                    startActivity(i);
//                }
//            });
//
//
//            //Logout btn
//            Button btn = root.findViewById(R.id.button4);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
//                    mAuth.signOut();
//                    Intent i = new Intent(getContext(), Login_SignUp.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
//                }
//            });
//
//            initData(root);
//        }
        return root;
    }

    private void initRecyclerView(View view) {
        Log.d(TAG, "initRecyclerView: init recyclerview_CustomerView.");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_home_fragment);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        final RecyclerView_CustomerView adapter = new RecyclerView_CustomerView(list, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final EditText search_bar;
        search_bar = root.findViewById(R.id.searchbar);

        Button btn = root.findViewById(R.id.search_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "inside OnClick");
                Toast.makeText(getContext(), "Toast generated", Toast.LENGTH_SHORT).show();

                adapter.getFilter().filter(search_bar.getText().toString());
            }
        });


    }

    private void initData(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference().child("users").child(uid).child("menu");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
                showData(dataSnapshot);
                initRecyclerView_Vendor();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        mImageUrls.clear();
        mNames.clear();
        mPrice.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Item data = new Item();
            data.setName(ds.getValue(Item.class).getName());
            data.setPrice(ds.getValue(Item.class).getPrice());
//            data.name = ds.getValue(Item.class).getName();
//            data.price = ds.getValue(Item.class).getPrice();

            mImageIcons.add(ds.getKey());
            mNames.add(data.getName());
            mPrice.add(data.getPrice());

            Log.d(TAG, " : inside showData " + ds.getKey() + " " + data.getName() + " " + data.getPrice());
        }
    }

    private void initRecyclerView_Vendor() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_restaurant);
        RecyclerView_Restaurant adapter = new RecyclerView_Restaurant(mImageIcons, mNames, mPrice, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }
}


/////////////////////////////////////


//package com.example.foodcall.ui.home;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.foodcall.AddRestaurantItem;
//import com.example.foodcall.Item;
//import com.example.foodcall.Login_SignUp;
//import com.example.foodcall.Menu;
//import com.example.foodcall.R;
//import com.example.foodcall.RecyclerView_CustomerView;
//import com.example.foodcall.RecyclerView_Restaurant;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class HomeFragment extends Fragment {
//
//    public static final String TAG = "Home Fragment";
//
//    private ArrayList<Integer> mImageUrls = new ArrayList<>();
//    private ArrayList<String> mImageIcons = new ArrayList<>();
//    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<String> mPrice = new ArrayList<>();
//
//    private HomeViewModel homeViewModel;
//    private ImageView imageView;
//    String uid;
//
//    View root;
//
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
////        Intent i = getActivity().getIntent();
////        Bundle extras = i.getExtras();
////        String temp = extras.get("bool").toString();
//
//        //if (temp.equals("true")) {
//        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
//        root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//
//        imageView = (ImageView) root.findViewById(R.id.res_img);
////        imageView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(getContext(), Menu.class);
////                startActivity(intent);
////            }
////        });
//
//        Button btn=root.findViewById(R.id.search_btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG,"inside OnClick");
//                Toast.makeText(getContext(),"Toast generated",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mImageUrls.add(R.drawable.kfc);
//        mNames.add("KFC DHA");
//        mPrice.add("Free Delivery");
//
//        mImageUrls.add(R.drawable.mcdonalds);
//        mNames.add("McDonalds DHA");
//        mPrice.add("Rs. 50 per delivery");
//        initRecyclerView(root);
////        }
////        else
////        {
////            root = inflater.inflate(R.layout.activity_main__restaurant, container, false);
////            FloatingActionButton fab = root.findViewById(R.id.floatingActionButton);
////
////            //Add restaurant item button
////            fab.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent i = new Intent(getContext(), AddRestaurantItem.class);
////                    startActivity(i);
////                }
////            });
////
////
////            //Logout btn
////            Button btn = root.findViewById(R.id.button4);
////            btn.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
////                    mAuth.signOut();
////                    Intent i = new Intent(getContext(), Login_SignUp.class);
////                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    startActivity(i);
////                }
////            });
////
////            initData(root);
////        }
//        return root;
//    }
//
//    private void initRecyclerView(View view) {
//        Log.d(TAG, "initRecyclerView: init recyclerview_CustomerView.");
//        RecyclerView recyclerView = view.findViewById(R.id.recycler_home_fragment);
//
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL));
//        RecyclerView_CustomerView adapter = new RecyclerView_CustomerView(mImageUrls, mNames, mPrice, getContext());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//
//    private void initData(View view) {
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        uid = mAuth.getCurrentUser().getUid();
//        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = mFirebaseDatabase.getReference().child("users").child(uid).child("menu");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
////                String value = dataSnapshot.getValue(String.class);
////                Log.d(TAG, "Value is: " + value);
//                showData(dataSnapshot);
//                initRecyclerView_Vendor();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//    }
//
//    private void showData(DataSnapshot dataSnapshot) {
//        mImageUrls.clear();
//        mNames.clear();
//        mPrice.clear();
//        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//            Item data = new Item();
//            data.setName(ds.getValue(Item.class).getName());
//            data.setPrice(ds.getValue(Item.class).getPrice());
////            data.name = ds.getValue(Item.class).getName();
////            data.price = ds.getValue(Item.class).getPrice();
//
//            mImageIcons.add(ds.getKey());
//            mNames.add(data.getName());
//            mPrice.add(data.getPrice());
//
//            Log.d(TAG, " : inside showData " + ds.getKey() + " " + data.getName() + " " + data.getPrice());
//        }
//    }
//
//    private void initRecyclerView_Vendor() {
//        Log.d(TAG, "initRecyclerView: init recyclerview.");
//        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_restaurant);
//        RecyclerView_Restaurant adapter = new RecyclerView_Restaurant(mImageIcons, mNames, mPrice, getContext());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//}