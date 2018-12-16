package com.foodie.foodie;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToppingListAdapter extends BaseAdapter {

    private ArrayList<Food> toppings;
    private LayoutInflater layoutInflater;

    public ToppingListAdapter(ArrayList<Food> toppings, LayoutInflater layoutInflater) {
        this.toppings = toppings;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return toppings.size();
    }

    @Override
    public Object getItem(int position) {
        return toppings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.topping, parent, false);
        }

        TextView toppingName = convertView.findViewById(R.id.tv_topping_name);
        TextView toppingPrice = convertView.findViewById(R.id.tv_topping_price);
        CheckBox checkBox = convertView.findViewById(R.id.checkbox);

        toppingName.setText(toppings.get(position).getTH_name());
        toppingPrice.setText(String.valueOf(toppings.get(position).getPrice()));

        return convertView;
    }
}
