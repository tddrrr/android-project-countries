package com.example.project_countries.fragmente;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_countries.R;

import static android.content.Context.MODE_PRIVATE;
import static com.example.project_countries.Login.FIRSTNAME_USER;
import static com.example.project_countries.Login.LASTNAME_USER;
import static com.example.project_countries.Login.LOGIN_SHARED_PREF;

/**
* A simple {@link Fragment} subclass.
 */

public class FragmentProfil extends Fragment {
    private SharedPreferences preferences;
    private String firstName;
    private String lastName;
    private TextView info;
    public FragmentProfil() {
        // Required empty public constructor
       // preferences = getSharedPreferences(LOGIN_SHARED_PREF, MODE_PRIVATE);
        //LOGIN_SHARED_PREF

        initComponent();
    }
    private void initComponent(){
        SharedPreferences.Editor editor = preferences.edit();
        preferences.getString(FIRSTNAME_USER,firstName);
        preferences.getString(LASTNAME_USER,lastName);
        String value = getContext().getString(R.string.fragment_profil_info,firstName,lastName);
        info= getView().findViewById(R.id.tv_nume);
        info.setText(value);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }
}