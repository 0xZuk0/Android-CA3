package com.example.myfinalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatFragment extends Fragment {
    List<Animals> listOfCats = new ArrayList<>();
    GridView gridView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animals animal = listOfCats.get(i);
                Intent intent = new Intent(getActivity(), PetViewerActivity.class);
                intent.putExtra("name", animal.getName());
                intent.putExtra("image", animal.getImage());
                intent.putExtra("email", animal.getEmail());
                intent.putExtra("age", animal.getAge());
                intent.putExtra("address", animal.getAddress());
                intent.putExtra("features", animal.getFeatures());
                intent.putExtra("mobile", animal.getMobileNumber());
                intent.putExtra("type", 1);
                intent.putExtra("description", animal.getDescription());
                startActivity(intent);
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatFragment newInstance(String param1, String param2) {
        CatFragment fragment = new CatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        populateCats();
        View rootView = inflater.inflate(R.layout.fragment_cat, container, false);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity(), R.layout.grid_animals, listOfCats);
        gridView = (GridView) rootView.findViewById(R.id.cats_grid_view);
        gridView.setAdapter(gridViewAdapter);
        return rootView;
    }

    public void populateCats() {
        String longDescription = "Some Very Long Descritpion for testing i don't know what i can typeing whatever comes in my mind i will type this thing";
        listOfCats.add(new Animals("Empress", R.drawable.cat1, "1", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));
        listOfCats.add(new Animals("Prinston", R.drawable.cat2, "1", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));
        listOfCats.add(new Animals("Katty", R.drawable.cat3, "1", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));
        listOfCats.add(new Animals("Jenny", R.drawable.cat4, "1", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));
        listOfCats.add(new Animals("Ross", R.drawable.cat5, "1", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));
        listOfCats.add(new Animals("Pheebee", R.drawable.cat6, "1", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));
        listOfCats.add(new Animals("Monica", R.drawable.cat7, "1", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));
        listOfCats.add(new Animals("Joey", R.drawable.cat8, "1", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));

    }
}