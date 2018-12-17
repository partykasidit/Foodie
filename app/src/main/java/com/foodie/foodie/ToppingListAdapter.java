package com.foodie.foodie;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class ToppingListAdapter extends BaseAdapter {

    private Activity context;
    private ArrayList<Food> toppings;

    ToppingListAdapter(Activity context,ArrayList<Food> toppings) {
        this.toppings = toppings;
        this.context = context;
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
            convertView = context.getLayoutInflater().inflate(R.layout.topping, parent, false);
        }

        TextView toppingName = convertView.findViewById(R.id.tv_topping_name);
        TextView toppingPrice = convertView.findViewById(R.id.tv_topping_price);
        CheckBox checkBox = convertView.findViewById(R.id.checkbox);

        toppingName.setText(toppings.get(position).getTH_name());
        toppingPrice.setText(String.valueOf(toppings.get(position).getPrice()));

        return convertView;
    }
}
