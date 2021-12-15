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
 * Use the {@link DogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogFragment extends Fragment {
    List<Animals> listOfDogs = new ArrayList<>();
    GridView gridView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DogFragment newInstance(String param1, String param2) {
        DogFragment fragment = new DogFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animals animal = listOfDogs.get(i);
                Intent intent = new Intent(getActivity(), PetViewerActivity.class);
                intent.putExtra("name", animal.getName());
                intent.putExtra("image", animal.getImage());
                intent.putExtra("email", animal.getEmail());
                intent.putExtra("age", animal.getAge());
                intent.putExtra("address", animal.getAddress());
                intent.putExtra("features", animal.getFeatures());
                intent.putExtra("mobile", animal.getMobileNumber());
                intent.putExtra("description", animal.getDescription());
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        populateDogs();
        View rootView = inflater.inflate(R.layout.fragment_dog, container, false);
        GridViewAdapter customAdapter = new GridViewAdapter(getActivity(), R.layout.grid_animals, listOfDogs);
        gridView = (GridView) rootView.findViewById(R.id.dogs_grid_view);
        gridView.setAdapter(customAdapter);
        return rootView;
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_dog, container, false);
    }

    public void populateDogs() {
        String longDescription = "one of those dogs which you cant resist. He is so adorable you will feel like hugging him all day. He is very obedient and loves to play with other animals.";
        listOfDogs.add(new Animals("Muko", R.drawable.muko, "2", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "adoptanimals@gmail.com", "+(217) 2341-45642", "27 Sherando Trl Hedgesville, West Virginia(WV), 25427", longDescription));
        listOfDogs.add(new Animals("Franky", R.drawable.dog2, "2", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "adoptanimals@gmail.com", "+(217) 2341-45642", "27 Sherando Trl Hedgesville, West Virginia(WV), 25427", longDescription));
        listOfDogs.add(new Animals("Ludo", R.drawable.dog3, "2", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "adoptanimals@gmail.com", "+(217) 2341-45642", "27 Sherando Trl Hedgesville, West Virginia(WV), 25427", longDescription));
        listOfDogs.add(new Animals("Mike", R.drawable.dog4, "2", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "adoptanimals@gmail.com", "+(217) 2341-45642", "27 Sherando Trl Hedgesville, West Virginia(WV), 25427", longDescription));
        listOfDogs.add(new Animals("Karl", R.drawable.dog5, "2", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "adoptanimals@gmail.com", "+(217) 2341-45642", "27 Sherando Trl Hedgesville, West Virginia(WV), 25427", longDescription));
        listOfDogs.add(new Animals("Gonjiam", R.drawable.dog6, "2", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "adoptanimals@gmail.com", "+(217) 2341-45642", "27 Sherando Trl Hedgesville, West Virginia(WV), 25427", longDescription));
        listOfDogs.add(new Animals("Jumba", R.drawable.dog7, "2", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "adoptanimals@gmail.com", "+(217) 2341-45642", "27 Sherando Trl Hedgesville, West Virginia(WV), 25427", longDescription));
        listOfDogs.add(new Animals("Parker", R.drawable.dog8, "2", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "adoptanimals@gmail.com", "+(217) 2341-45642", "27 Sherando Trl Hedgesville, West Virginia(WV), 25427", longDescription));

    }

}