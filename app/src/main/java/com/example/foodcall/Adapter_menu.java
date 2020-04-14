package com.example.foodcall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

class menu_class {

    String itemName;
    String price;

    public menu_class(String itemName, String price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}

public class Adapter_menu extends ArrayAdapter {

    int mResource;
    private Context context;
    List<menu_class> menuClassList = new ArrayList<>();

    public Adapter_menu(@NonNull Context context, int resource, @NonNull List<menu_class> objects) {
        super(context, resource, objects);
        this.context=getContext();
        this.mResource = resource;
        menuClassList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolderItem viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(mResource, parent, false);

            viewHolder = new ViewHolderItem();
            viewHolder.Item = (TextView) convertView.findViewById(R.id.itemname);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolderItem) convertView.getTag();

        }
        return convertView;
    }


    @Override
    public int getCount() {
        return menuClassList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuClassList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolderItem {
        TextView Item;
        TextView price;
        
    }
}

