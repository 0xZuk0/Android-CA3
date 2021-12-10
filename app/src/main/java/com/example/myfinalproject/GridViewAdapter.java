package com.example.myfinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends ArrayAdapter {

    List<Animals> animalList = new ArrayList<>();
    int custom_layout_id;

    public GridViewAdapter(Context context, int resources, List<Animals> objects) {
        super(context, resources, objects);
        animalList = objects;
        custom_layout_id = resources;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(custom_layout_id, null);
        }
        ImageView image = (ImageView) v.findViewById(R.id.dog_image);
        TextView name = (TextView) v.findViewById(R.id.dog_name);
        Animals animal = animalList.get(position);
        image.setImageResource(animal.getImage());
        name.setText(animal.getName());
        return v;
    }
}
