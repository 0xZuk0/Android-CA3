package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PetViewerActivity extends AppCompatActivity {
    ImageView petImage;
    TextView name, age, description, address, mobile, email, feature1, feature2, feature3, feature4, lastseen, lastseenContent, contactHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_viewer);
        getSupportActionBar().hide();
        petImage = findViewById(R.id.pet_viewer_image);
        name = findViewById(R.id.pet_viewer_pet_name);
        age = findViewById(R.id.pet_viewer_pet_age);
        description = findViewById(R.id.pet_viewer_pet_description_content);
        address = findViewById(R.id.pat_viewer_address);
        mobile = findViewById(R.id.pet_viewer_telephone);
        email = findViewById(R.id.pet_viewer_email);
        feature1 = findViewById(R.id.pet_viewer_pet_feature_1);
        feature2 = findViewById(R.id.pet_viewer_pet_feature_2);
        feature3 = findViewById(R.id.pet_viewer_pet_feature_3);
        feature4 = findViewById(R.id.pet_viewer_pet_feature_4);
        lastseen = findViewById(R.id.pet_viewer_pet_last_seen);
        lastseenContent = findViewById(R.id.pet_viewer_pet_lastseen_content);
        contactHeader = findViewById(R.id.pet_viewer_contact_details);

        Intent intent = getIntent();

        if (intent.getIntExtra("type", -1) == 1) {
            Animals animal = new Animals(
                    intent.getStringExtra("name"), intent.getIntExtra("image", -11111), intent.getStringExtra("age"),
                    intent.getStringArrayExtra("features"), intent.getStringExtra("email"), intent.getStringExtra("mobile"),
                    intent.getStringExtra("address"), intent.getStringExtra("description")
            );
            int image = animal.getImage();
            petImage.setImageResource(image);
            name.setText(animal.getName());
            age.setText(String.valueOf(animal.getAge()) + " Year");
            description.setText(animal.getDescription());
            address.setText(animal.getAddress());
            mobile.setText(animal.getMobileNumber());
            email.setText(animal.getEmail());
            feature1.setText(animal.getFeatures()[0]);
            feature2.setText(animal.getFeatures()[1]);
            feature3.setText(animal.getFeatures()[2]);
            feature4.setText(animal.getFeatures()[3]);
            lastseen.setVisibility(View.INVISIBLE);
            lastseenContent.setVisibility(View.INVISIBLE);
        } else
        {
            if (intent.getBooleanExtra("flag", true)) {
            MissingPet animal = new MissingPet(intent.getStringExtra("name"), intent.getIntExtra("age", 0), intent.getStringExtra("description"),
                    intent.getStringExtra("lastseen"), intent.getStringExtra("mobile"), intent.getStringExtra("email"), intent.getStringExtra("address"), intent.getByteArrayExtra("image"));
            byte[] image = animal.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            petImage.setImageBitmap(bmp);
            name.setText(animal.getName());
            age.setText(String.valueOf(animal.getAge()));
            description.setText(animal.getDescription());
            address.setText(animal.getAddress());
            mobile.setText(animal.getMobile());
            email.setText(animal.getEmail());
            lastseen.setVisibility(View.VISIBLE);
            lastseenContent.setVisibility(View.VISIBLE);
            lastseen.setTextSize(30);
            lastseenContent.setTextSize(20);
        } else {
            AdoptionPet animal = new AdoptionPet(intent.getStringExtra("name"), intent.getIntExtra("age", 0), intent.getStringExtra("description")
                    , intent.getStringArrayExtra("features"), intent.getStringExtra("mobile"), intent.getStringExtra("email"), intent.getStringExtra("address")
                    , intent.getByteArrayExtra("image")
            );

            byte[] image = animal.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            petImage.setImageBitmap(bmp);
            name.setText(animal.getName());
            age.setText(String.valueOf(animal.getAge()) + " Year");
            description.setText(animal.getDescription());
            address.setText(animal.getAddress());
            mobile.setText(animal.getMobile());
            email.setText(animal.getEmail());
            feature1.setText(animal.getFeatures()[0]);
            feature2.setText(animal.getFeatures()[1]);
            feature3.setText(animal.getFeatures()[2]);
            feature4.setText(animal.getFeatures()[3]);
            lastseen.setVisibility(View.INVISIBLE);
            lastseenContent.setVisibility(View.INVISIBLE);
        }
    }


        }
}