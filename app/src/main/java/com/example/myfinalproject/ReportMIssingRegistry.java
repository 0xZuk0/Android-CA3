package com.example.myfinalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Actually Working on Adoption Activity The name got messed by

public class ReportMIssingRegistry extends AppCompatActivity {
    ImageView petImage;
    private static final int PICK_IMAGE = 100;
    EditText petName, petAge, petDescription, pf1, pf2, pf3, pf4, petMobile, petEmail, petAddress;
    Button uploadButton, submitButton;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_missing_registry);
        getSupportActionBar().hide();
        petImage = (ImageView) findViewById(R.id.missing_registry_pet_image);
        uploadButton = (Button) findViewById(R.id.missing_report_upload_button);
        petName = (EditText) findViewById(R.id.missing_registry_pet_name);
        petAge = (EditText) findViewById(R.id.missing_registry_pet_age);
        pf1 = (EditText) findViewById(R.id.feature1);
        pf2 = (EditText) findViewById(R.id.feature2);
        pf3 = (EditText) findViewById(R.id.feature3);
        pf4 = (EditText) findViewById(R.id.feature4);
        petDescription = (EditText) findViewById(R.id.missing_registry_pet_description);
        petMobile = (EditText) findViewById(R.id.missing_registry_pet_mobile_number);
        petEmail = (EditText) findViewById(R.id.missing_registry_pet_email);
        petAddress = (EditText) findViewById(R.id.missing_registry_pet_address);
        submitButton = (Button) findViewById(R.id.missing_registry_pet_submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                   if(saveAdoptionToDatabase()) {
                       Toast.makeText(getApplicationContext(), "Adoption Form Saved", Toast.LENGTH_LONG).show();
                       petImage.setImageResource(R.drawable.upload);
                       petName.setText("");
                       petAddress.setText("");
                       petDescription.setText("");
                       pf1.setText("");
                       pf2.setText("");
                       pf3.setText("");
                       pf4.setText("");
                       petMobile.setText("");
                       petEmail.setText("");
                       petAddress.setText("");
                   }
                   else {
                       Toast.makeText(getApplicationContext(), "Error Saving Adoption Form", Toast.LENGTH_LONG).show();
                   }
               }
               catch (Exception error) {
                   error.printStackTrace();
               }
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            //Log.d("URI", ": " + imageUri.toString());
            petImage.setImageURI(imageUri);
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        /*
        StackOverflow code
        https://stackoverflow.com/questions/10296734/image-uri-to-bytesarray
         */
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public boolean saveAdoptionToDatabase() throws IOException {
        String name = petName.getText().toString();
        int age = Integer.parseInt(petAge.getText().toString());
        String description = petDescription.getText().toString();
        String[] features = new String[]{
               pf1.getText().toString(), pf2.getText().toString(), pf3.getText().toString(),  pf4.getText().toString()
        };

        String mobile = petMobile.getText().toString();
        String email = petEmail.getText().toString();
        String address = petAddress.getText().toString();

        InputStream fis = getContentResolver().openInputStream(imageUri);
        byte[] image = getBytes(fis);
        fis.read(image);
        fis.close();

        DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        if(dbHandler.saveAdoption(name, age, description, features, mobile, address, email, image)) {
            return true;
        }

        return false;
    }
}