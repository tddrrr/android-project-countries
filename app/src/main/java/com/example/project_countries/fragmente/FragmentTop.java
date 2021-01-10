package com.example.project_countries.fragmente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_countries.R;
import com.example.project_countries.database.entities.User;
import com.example.project_countries.database.operations.UserOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentTop extends Fragment {
    private List<User> users;
    UserOperations userOperations;
    public FragmentTop() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userOperations = new UserOperations(getContext().getApplicationContext());
        users = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        return view;
    }

    private void getUsersFromDB() {
        userOperations.getAll(result -> {
            if (result != null) {
                users.addAll(result);
            }
        });
    }


}