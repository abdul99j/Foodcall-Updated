package com.example.foodcall;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodcall.ui.home.HomeFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Recycler_View";

    private ArrayList<String> itemName;
    private ArrayList<String> count;
    private Context context;

    public RecyclerView_Adapter(ArrayList<String> itemName, ArrayList<String> count, Context context) {
        this.itemName = itemName;
        this.count = count;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        ViewHolder temp = (ViewHolder) holder;

        //15:00 in video
        ((ViewHolder) holder).text.setText(itemName.get(position));
        ((ViewHolder) holder).counter.setText(count.get(position));


        ((ViewHolder) holder).plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View temp = holder.itemView;
                int temp1 = Integer.parseInt((String) ((ViewHolder) holder).counter.getText());
                temp1++;
                ((ViewHolder) holder).counter.setText(temp1);
            }
        });

        ((ViewHolder) holder).minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View temp = holder.itemView;
                int temp1 = Integer.parseInt((String) ((ViewHolder) holder).counter.getText());
                if (temp1 > 0)
                    temp1--;
                ((ViewHolder) holder).counter.setText(temp1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        Button plus;
        Button minus;
        TextView counter;
        RelativeLayout layout_Parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.food_name);
            layout_Parent = itemView.findViewById(R.id.parent);

        }
    }
}

