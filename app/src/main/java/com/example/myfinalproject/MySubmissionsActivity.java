package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MySubmissionsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<AdoptionPet> al;
    MySubmissionCustomAdapter mySubmissionCustomAdapter;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_submissions);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.my_submissions_recycle_view);
        al = new ArrayList<>();
        populateAdoptionPetArrayList();
        mySubmissionCustomAdapter = new MySubmissionCustomAdapter(this, al);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mySubmissionCustomAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    public void populateAdoptionPetArrayList() {
        DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        al = dbHandler.getAllAdoptionPets();
        dbHandler.close();
    }
}