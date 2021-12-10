package com.example.myfinalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginInFragment extends Fragment {
    EditText email, password;
    Button loginButton;
    TextView signUpButton;
    ImageView closeButton;
    FrameLayout parentFrameLayout;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginInFragment newInstance(String param1, String param2) {
        LoginInFragment fragment = new LoginInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToHomeActivity();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performTransaction(view, new SignUpFragment());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfInputsAreEmpty()) {
                    Snackbar.make(view, "Email or Password field is empty", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(!checkIfInputsAreValid()) {
                    Snackbar.make(view, "Enter valid email", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(checkIfCredentialsAreValid()) {
                    sendToHomeActivity();
                }
                else {
                    Snackbar.make(view, "Invalid Username or Password", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void sendToHomeActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public boolean checkIfCredentialsAreValid() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        DatabaseHandler dbHandler = new DatabaseHandler(getActivity());

        Users user = dbHandler.getUser(mail);

        if(user == null) {
            return false;
        }
        else {
            if (user.getPassword().equals(pass)) {
                return true;
            }
            else {
               return false;
            }
        }
    }

    public boolean checkIfInputsAreValid() {
        if(email.getText().toString().matches(emailPattern)) {
            return true;
        }
        return false;
    }

    public boolean checkIfInputsAreEmpty() {
        if(TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(password.getText())) {
            return true;
        }
        return false;
    }

    public void performTransaction(View view, Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(parentFrameLayout.getId(), fragment).addToBackStack(null).commit();
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
        View rootView = inflater.inflate(R.layout.fragment_login_in, container, false);
        email = (EditText) rootView.findViewById(R.id.login_email);
        password = (EditText) rootView.findViewById(R.id.login_password);
        loginButton = (Button) rootView.findViewById(R.id.login_button);
        closeButton = (ImageView) rootView.findViewById(R.id.sign_up_close_button);
        signUpButton = (TextView) rootView.findViewById(R.id.login_sign_up);
        parentFrameLayout = getActivity().findViewById(R.id.login_signup_frame_layout);

        return rootView;
    }
}