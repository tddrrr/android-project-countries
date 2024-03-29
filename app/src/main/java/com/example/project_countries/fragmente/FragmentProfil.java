package com.example.project_countries.fragmente;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_countries.Login;
import com.example.project_countries.R;
import com.example.project_countries.database.entities.User;

import static android.content.Context.MODE_PRIVATE;
import static com.example.project_countries.Login.FIRSTNAME_USER;
import static com.example.project_countries.Login.LASTNAME_USER;
import static com.example.project_countries.Login.LOGIN_SHARED_PREF;
import static com.example.project_countries.Login.id_user;

/**
* A simple {@link Fragment} subclass.
 */

public class FragmentProfil extends Fragment {
    private SharedPreferences preferences ;
    private TextView firstName;
    private TextView lastName;
    private TextView info;
    private TextView Email;
    private TextView Score;
    private User user;
    public FragmentProfil(User user) {
        this.user=user;
    }

    private void initComponent(View v){
//        preferences = getActivity().getSharedPreferences(LOGIN_SHARED_PREF, MODE_PRIVATE);
//        preferences.getString(FIRSTNAME_USER,firstName);
//        preferences.getString(LASTNAME_USER,lastName);
        String value = getContext().getString(R.string.fragment_profil_info,user.getFirstName(),user.getLastName());
        info= v.findViewById(R.id.tv_nume);
        info.setText(value);
        String fname = getContext().getString(R.string.fragment_profil_firstName,user.getFirstName());
        String lname = getContext().getString(R.string.fragment_profil_lastName,user.getLastName());
        String email = getContext().getString(R.string.fragment_profil_email,user.getEmail());
        String score = getContext().getString(R.string.fragment_profil_score,user.getScore());
        firstName= v.findViewById(R.id.tv_profile_firstName);
        firstName.setText(fname);
        lastName= v.findViewById(R.id.tv_profile_lastName);
        lastName.setText(lname);
        Email= v.findViewById(R.id.tv_profile_Email);
        Email.setText(email);
        Score= v.findViewById(R.id.tv_profile_Score);
        Score.setText(score);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        initComponent(v);
        return v;

    }

}