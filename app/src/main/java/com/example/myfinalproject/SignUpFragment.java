package com.example.myfinalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
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

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {
    EditText username, email, password, retypePassword;
    Button signUpButton;
    ImageView closeButton;
    TextView loginButton;
    FrameLayout parentFrameLayout;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performTransaction(view, new LoginInFragment());
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkIfInputIsEmpty(view)) {
                    return;
                }

                if(!checkIfPasswordsAreSame()) {
                    Snackbar.make(view, "Password Does not match!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                saveTheUserInDatabase();

            }
        });

    }

    public void saveTheUserInDatabase() {
        // TO-DO

        String uname = username.getText().toString();
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        DatabaseHandler dbHandler = new DatabaseHandler(getActivity());
        boolean result = dbHandler.addUser(uname, mail, pass);

        if(result) {
            sendToHomeActivity();
        }
        else {
            Toast.makeText(getActivity(), "Error Creating User Please Try Again!", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkIfPasswordsAreSame() {
        if(password.getText().toString().equals(retypePassword.getText().toString())) {
            return true;
        }
        return false;
    }

    public void createSnackBar(View view, String mesasge) {
        Snackbar.make(view, mesasge, Snackbar.LENGTH_SHORT).show();
    }

    public boolean checkIfInputIsEmpty(View view) {
        if(TextUtils.isEmpty(username.getText())) {
            createSnackBar(view, "Username is Empty");
            return true;
        }
        else if(TextUtils.isEmpty(email.getText())) {
            createSnackBar(view, "Email is Empty");
            return true;
        }
        else if(TextUtils.isEmpty(password.getText())) {
            createSnackBar(view, "Password is Empty");
            return true;
        }
        else if(TextUtils.isEmpty(retypePassword.getText())) {
            createSnackBar(view, "Retype Password is Empty");
            return true;
        }
        return false;
    }

    public void performTransaction(View view, Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(parentFrameLayout.getId(), fragment).addToBackStack(null).commit();
    }

    public void sendToHomeActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        username = (EditText) rootView.findViewById(R.id.signup_username);
        email = (EditText) rootView.findViewById(R.id.sign_up_email);
        password = (EditText) rootView.findViewById(R.id.sign_up_password);
        retypePassword = (EditText) rootView.findViewById(R.id.sign_up_retype_password);
        signUpButton = (Button) rootView.findViewById(R.id.sign_up_button);
        parentFrameLayout = (FrameLayout) getActivity().findViewById(R.id.login_signup_frame_layout);
        closeButton = (ImageView) rootView.findViewById(R.id.sign_up_close_button);
        loginButton = (TextView) rootView.findViewById(R.id.sign_up_login);
        // Inflate the layout for this fragment
        return rootView;
    }
}