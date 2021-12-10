package com.example.myfinalproject;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    Context ct;
    ArrayList<IndividualCategories> al;

    public CategoryAdapter(Context ct, ArrayList<IndividualCategories> al) {
        this.ct = ct;
        this.al = al;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        IndividualCategories ic = al.get(position);
        holder.name.setText(ic.getCategoryName());
        holder.image.setImageResource(ic.getCategoryImage());
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ct);
        View view = layoutInflater.inflate(R.layout.categories, parent, false);

        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        LinearLayout layout;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.category_name);
            image = (ImageView) itemView.findViewById(R.id.category_image);
            layout = (LinearLayout) itemView.findViewById(R.id.constrain_layout1);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    switch (al.get(position).getCategoryName()) {
                        case "Dog" :
                            performFragmentTransaction(view, new DogFragment());
                            break;
                        case "Cat" :
                            performFragmentTransaction(view, new CatFragment());
                            break;
                        case "Rabbit" :
                            performFragmentTransaction(view, new RabbitFragment());
                            break;
                        case "Hamster" :
                            performFragmentTransaction(view, new HamsterFragment());
                            break;
                        case "Bird" :
                            performFragmentTransaction(view, new BirdFragment());
                            break;

                    }

                }
            });

        }
        public void performFragmentTransaction(View view, Fragment fragment) {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, fragment).addToBackStack(null).commit();
        }

    }

}
