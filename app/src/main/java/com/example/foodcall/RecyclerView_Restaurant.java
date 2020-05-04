package com.example.foodcall;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerView_Restaurant extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Recycler_View";

    private ArrayList<String> image_Recycle;
    private ArrayList<String> image_Name;
    private ArrayList<String> image_Price;
    private Context context;

    public RecyclerView_Restaurant(ArrayList<String> image_Recycle, ArrayList<String> image_Name, ArrayList<String> image_Price, Context context) {
        this.image_Recycle = image_Recycle;
        this.image_Name = image_Name;
        this.image_Price = image_Price;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item_restaurant, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        ViewHolder temp = (ViewHolder) holder;

//        byte[] imageBytes = Base64.decode(image_Recycle.get(position), Base64.DEFAULT);
//        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//        temp.image.setImageBitmap(decodedImage);

//        Glide.with(context)
//                .asBitmap()
//                .load(image_Recycle.get(position))
//                .into(temp.image);

        ((ViewHolder) holder).text_name.setText(image_Name.get(position));
        ((ViewHolder) holder).text_price.setText(image_Price.get(position));
        ((ViewHolder) holder).layout_Parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "on Click: clicked on: " + image_Name.get(position));

                //Toast.makeText(context,image_Name.get(position),Toast.LENGTH_SHORT).show();

                View temp = holder.itemView;
//                Intent i = new Intent(context, Info_Screen.class);
//                i.putExtra("image", image_Recycle.get(position));
//                i.putExtra("name", image_Name.get(position));
//                i.putExtra("contact", image_Contact.get(position));
//                i.putExtra("email", image_Email.get(position));
//                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return image_Name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text_name;
        TextView text_price;
        RelativeLayout layout_Parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profile_image);
            text_name = itemView.findViewById(R.id.row_text);
            text_price = itemView.findViewById(R.id.price);
            layout_Parent = itemView.findViewById(R.id.parent);
        }
    }
}
