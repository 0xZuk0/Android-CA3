package com.example.myfinalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MySubmissionCustomAdapter extends RecyclerView.Adapter<MySubmissionCustomAdapter.MyViewHolder> {
    Context ct;
    ArrayList<AdoptionPet> al;

    @Override
    public int getItemCount() {
        return al.size();
    }

    public MySubmissionCustomAdapter(Context ct, ArrayList<AdoptionPet> al) {
        this.ct = ct;
        this.al = al;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AdoptionPet adoptionPet = al.get(position);
        holder.name.setText("Name : " + adoptionPet.getName());
        holder.age.setText("Age : " + String.valueOf(adoptionPet.getAge()) + " Year");
        byte[] image = adoptionPet.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.image.setImageBitmap(bmp);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ct);
        View view = layoutInflater.inflate(R.layout.mysubmissionitems, parent, false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView age;
        ImageView image;
        RelativeLayout layout;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.my_submission_pet_name);
            age = (TextView) itemView.findViewById(R.id.my_submission_pet_age) ;
            image = (ImageView) itemView.findViewById(R.id.my_submission_pet_image);
            layout = (RelativeLayout) itemView.findViewById(R.id.my_submission_layout);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AdoptionPet animal = al.get(getAdapterPosition());
                    Intent intent = new Intent(ct, PetViewerActivity.class);
                    intent.putExtra("name", animal.getName());
                    intent.putExtra("image", animal.getImage());
                    intent.putExtra("email", animal.getEmail());
                    intent.putExtra("age", animal.getAge());
                    intent.putExtra("address", animal.getAddress());
                    intent.putExtra("features", animal.getFeatures());
                    intent.putExtra("mobile", animal.getMobile());
                    intent.putExtra("description", animal.getDescription());
                    intent.putExtra("flag", false);
                    ct.startActivity(intent);
                }
            });

        }
    }
}
