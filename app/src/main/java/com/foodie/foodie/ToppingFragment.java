package com.foodie.foodie;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ToppingFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topping,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView plateList = view.findViewById(R.id.rv_plate_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        plateList.setLayoutManager(layoutManager);
        plateList.setHasFixedSize(true);
        ArrayList<Plate> plates = new ArrayList<>();
        plates.add(new Plate(new Food("Test","ทดสอบ",0,null)));
        plates.add(new Plate(new Food("Test","ทดสอบ",0,null)));
        PlateListAdapter adapter = new PlateListAdapter(getActivity(),plates);
        plateList.setAdapter(adapter);


    }
}
