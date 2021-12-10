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
 * Use the {@link RabbitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RabbitFragment extends Fragment {
    List<Animals> listOfRabbit = new ArrayList<>();
    GridView gridView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RabbitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animals animal = listOfRabbit.get(i);
                Intent intent = new Intent(getActivity(), PetViewerActivity.class);
                intent.putExtra("name", animal.getName());
                intent.putExtra("image", animal.getImage());
                intent.putExtra("email", animal.getEmail());
                intent.putExtra("age", animal.getAge());
                intent.putExtra("address", animal.getAddress());
                intent.putExtra("features", animal.getFeatures());
                intent.putExtra("mobile", animal.getMobileNumber());
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
     * @return A new instance of fragment RabbitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RabbitFragment newInstance(String param1, String param2) {
        RabbitFragment fragment = new RabbitFragment();
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
        populateRabbits();
        View rootView = inflater.inflate(R.layout.fragment_rabbit, container, false);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity(), R.layout.grid_animals, listOfRabbit);
        gridView = (GridView) rootView.findViewById(R.id.rabbits_grid_view);
        gridView.setAdapter(gridViewAdapter);
        return rootView;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_rabbit, container, false);
    }

    public void populateRabbits() {
        String longDescription = "Some Very Long Descritpion for testing i don't know what i can typeing whatever comes in my mind i will type this thing";

        listOfRabbit.add(new Animals("Snow White", R.drawable.rabbit, "1 Year", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));
        listOfRabbit.add(new Animals("Snow White", R.drawable.rabbit, "1 Year", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));
        listOfRabbit.add(new Animals("Snow White", R.drawable.rabbit, "1 Year", new String[]{"Cute", "Adorable", "Lovely", "Baby"}, "someemail@email.com", "222222222", "Some Very Big Address Given By the users i don't know what i can write here by let's see", longDescription));

    }
}