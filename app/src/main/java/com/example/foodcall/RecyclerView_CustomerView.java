package com.example.foodcall;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerView_CustomerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "RecyclerView_Customer";

    private ArrayList<Integer> image_Recycle;
    private ArrayList<String> image_Name;
    private ArrayList<String> image_Price;
    private Context context;

    public RecyclerView_CustomerView(ArrayList<Integer> image_Recycle, ArrayList<String> image_Name, ArrayList<String> image_Price, Context context) {
        this.image_Recycle = image_Recycle;
        this.image_Name = image_Name;
        this.image_Price = image_Price;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item_customer_view, parent, false);
        RecyclerView_CustomerView.ViewHolder holder = new RecyclerView_CustomerView.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        RecyclerView_CustomerView.ViewHolder temp = (RecyclerView_CustomerView.ViewHolder) holder;

//        byte[] imageBytes = Base64.decode(image_Recycle.get(position), Base64.DEFAULT);
//        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//        temp.image.setImageBitmap(decodedImage);

//        Glide.with(context)
//                .asBitmap()
//                .load(image_Recycle.get(position))
//                .into(temp.image);


        ((RecyclerView_CustomerView.ViewHolder) holder).image.setBackgroundResource(image_Recycle.get(position));
        ((RecyclerView_CustomerView.ViewHolder) holder).res_name.setText(image_Name.get(position));
        ((RecyclerView_CustomerView.ViewHolder) holder).delivery_price.setText(image_Price.get(position));
        ((RecyclerView_CustomerView.ViewHolder) holder).layout_Parent.setOnClickListener(new View.OnClickListener() {
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
        TextView res_name;
        TextView delivery_price;
        RelativeLayout layout_Parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.res_img);
            res_name = itemView.findViewById(R.id.res_name);
            delivery_price = itemView.findViewById(R.id.res_del);
            layout_Parent = itemView.findViewById(R.id.parent);
        }
    }
}
