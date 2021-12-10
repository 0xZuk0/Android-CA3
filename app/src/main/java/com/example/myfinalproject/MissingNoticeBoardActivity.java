package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MissingNoticeBoardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MissingPet> al;
    MissingBoardCustomAdapter missingBoardCustomAdapter;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_notice_board);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.missing_board_recycle_view);
        al = new ArrayList<>();
        populateMissingPetArrayList();
        missingBoardCustomAdapter = new MissingBoardCustomAdapter(this, al);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(missingBoardCustomAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void populateMissingPetArrayList() {
        DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        al = dbHandler.getAllMissingReport();
        dbHandler.close();
    }

}