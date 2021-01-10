package com.example.project_countries.fragmente;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_countries.R;
import com.example.project_countries.adapters.UserScoreHistoryAdapter;
import com.example.project_countries.database.entities.User;
import com.example.project_countries.database.operations.UserOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentTop extends Fragment {
    private List<User> users;
    private UserOperations userOperations;
    private RecyclerView recyclerView;

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
        recyclerView = view.findViewById(R.id.userScoreHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUsersFromDB();
    }

    private void getUsersFromDB() {
        userOperations.getAll(result -> {
            if (result != null) {
                users.addAll(result);
                addMockupUsers();
                users = users
                        .stream()
                        .sorted((a, b) ->  b.getScore() - a.getScore())
                        .collect(Collectors.toList());
                recyclerView.setAdapter(new UserScoreHistoryAdapter(users));
            }
        });
    }

    private void addMockupUsers() {
        users.add(new User("John",
                "Doe1",
                "johndoe1@gmail.com",
                "12345",
                2));
        users.add(new User("John",
                "Doe2",
                "johndoe2@gmail.com",
                "12345",
                5));
        users.add(new User("John",
                "Doe3",
                "johndoe3@gmail.com",
                "12345",
                1));
        users.add(new User("John",
                "Doe4",
                "johndoe4@gmail.com",
                "12345",
                10));
        users.add(new User("John",
                "Doe5",
                "johndoe5@gmail.com",
                "12345",
                14));
    }


}