package com.example.foodcall;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_CustomerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements Filterable {

    public static final String TAG = "RecyclerView_Customer";

    List<Data> list = new ArrayList<>();
    private Filter filter;
    List<Data> filter_list = new ArrayList<>();

    private Context context;

    public RecyclerView_CustomerView(List<Data> list, Context context) {
        this.list = list;
        this.filter_list = list;
        this.context = context;
    }

    //    public RecyclerView_CustomerView(Data list, Context context) {
////        this.image_Recycle = image_Recycle;
////        this.image_Name = image_Name;
////        this.image_Price = image_Price;
//        this.context = context;
//        this.list = list;
//        this.filter_list = list;
//    }

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

        ArrayList<Integer> image_Recycle;
        ArrayList<String> image_Name;
        ArrayList<String> image_Price;

        final Data obj = filter_list.get(position);

        ((RecyclerView_CustomerView.ViewHolder) holder).image.setBackgroundResource(obj.getImage_Recycle());
        ((RecyclerView_CustomerView.ViewHolder) holder).res_name.setText(obj.getImage_Name());
        ((RecyclerView_CustomerView.ViewHolder) holder).delivery_price.setText(obj.getImage_Price());
        ((RecyclerView_CustomerView.ViewHolder) holder).layout_Parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "on Click: clicked on: " + obj.getImage_Name());

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
        return filter_list.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new StudentsFilter();
        }
        return filter;
    }

    private class StudentsFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<Data> filteredList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getImage_Name().contains(constraint.toString())) {
                        filteredList.add(list.get(i));
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;

            } else {
                results.count = list.size();
                results.values = list;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filter_list = (ArrayList<Data>) results.values;
            notifyDataSetChanged();
        }

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
