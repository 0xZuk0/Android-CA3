package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<IndividualCategories> al;
    CategoryAdapter categoryAdapter;
    LinearLayoutManager linearLayoutManager;
    FloatingActionButton mainFab, missingPetFab, missingReportFab, mySubmissionFab, setForAdoptionFab, debug, clearAllData;
    TextView missingPetTextView, missingReportTextView, myReportTextView, setForAdoptionTextView;
    boolean hidden = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mainFab = (FloatingActionButton) findViewById(R.id.main_fab);
        missingPetFab = (FloatingActionButton) findViewById(R.id.fab_missing);
        missingReportFab = (FloatingActionButton) findViewById(R.id.report_missing);
        mySubmissionFab = (FloatingActionButton) findViewById(R.id.my_submissions);
        missingReportTextView = (TextView) findViewById(R.id.missing_report_textview);
        missingPetTextView = (TextView) findViewById(R.id.missing_pet_textview);
        myReportTextView = (TextView) findViewById(R.id.my_report_textview);
        setForAdoptionFab = (FloatingActionButton) findViewById(R.id.set_for_adoption);
        setForAdoptionTextView = (TextView) findViewById(R.id.set_for_adoption_textview);
        debug = (FloatingActionButton) findViewById(R.id.debug);
        clearAllData = (FloatingActionButton) findViewById(R.id.clearalldata);

        missingPetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MissingNoticeBoardActivity.class);
                startActivity(intent);
            }
        });

        clearAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
                dbHandler.cleanTable1();
                dbHandler.cleanTable2();
                dbHandler.cleanTable3();
            }
        });

        debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
                dbHandler.showReportTable();
                dbHandler.showUsersTable();
                dbHandler.showAdoptionTable();
            }
        });

        missingPetFab.hide();
        missingReportFab.hide();
        mySubmissionFab.hide();
        setForAdoptionFab.hide();
        setForAdoptionTextView.setVisibility(View.INVISIBLE);
        missingReportTextView.setVisibility(View.INVISIBLE);
        missingPetTextView.setVisibility(View.INVISIBLE);
        myReportTextView.setVisibility(View.INVISIBLE);

        mySubmissionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MySubmissionsActivity.class);
                startActivity(intent);
            }
        });

        missingReportFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdoptionRegistry.class);
                startActivity(intent);
            }
        });

        setForAdoptionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReportMIssingRegistry.class);
                startActivity(intent);
            }
        });

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidden == true) {
                    missingPetFab.show();
                    missingReportFab.show();
                    mySubmissionFab.show();
                    setForAdoptionFab.show();
                    setForAdoptionTextView.setVisibility(View.VISIBLE);
                    missingReportTextView.setVisibility(View.VISIBLE);
                    missingPetTextView.setVisibility(View.VISIBLE);
                    myReportTextView.setVisibility(View.VISIBLE);
                    hidden = false;
                }
                else {
                    missingPetFab.hide();
                    missingReportFab.hide();
                    setForAdoptionFab.hide();
                    setForAdoptionTextView.setVisibility(View.INVISIBLE);
                    mySubmissionFab.hide();
                    missingReportTextView.setVisibility(View.INVISIBLE);
                    missingPetTextView.setVisibility(View.INVISIBLE);
                    myReportTextView.setVisibility(View.INVISIBLE);
                    hidden = true;
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.categories);
        al = new ArrayList<>();
        populateCategoryArrayList();
        categoryAdapter = new CategoryAdapter(this, al);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categoryAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void populateCategoryArrayList() {
        al.add(new IndividualCategories("Dog", R.drawable.dog));
        al.add(new IndividualCategories("Cat", R.drawable.cat));
        al.add(new IndividualCategories("Rabbit", R.drawable.rabbit));
        al.add(new IndividualCategories("Hamster", R.drawable.hamster));
        al.add(new IndividualCategories("Bird", R.drawable.bird));
    }

}