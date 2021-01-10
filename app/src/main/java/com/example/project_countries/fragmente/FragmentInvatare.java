package com.example.project_countries.fragmente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project_countries.Country;
import com.example.project_countries.adapters.CountryAdapter;
import com.example.project_countries.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInvatare#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInvatare extends Fragment {

    private List<Country> countries;
    private ListView lvCountries;

    public FragmentInvatare() {
        // Required empty public constructor
    }

    public static FragmentInvatare newInstance(ArrayList<Country> countriesList) {
        FragmentInvatare fragment = new FragmentInvatare();
        Bundle args = new Bundle();
        args.putParcelableArrayList("countries", countriesList); //mi-am facut country parcelable ca sa pot selecta una din proprietatile astea
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invatare, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        lvCountries = view.findViewById(R.id.lv_invatare_countries);
        if (getArguments() != null) {
            //preiau lista de tari
            countries = getArguments().getParcelableArrayList("countries");
            Log.i("FragmentInvatare", countries.toString());
        }
        //creez adapter pentru lv
        if (getContext() != null) {
//            ArrayAdapter<Country> adapterCountry = new ArrayAdapter<>(getContext().getApplicationContext(),
//                    android.R.layout.simple_list_item_1, countries);
            CountryAdapter adapterCountry = new CountryAdapter(getContext().getApplicationContext(),R.layout.lv_row_view_invatare, countries,
                    getLayoutInflater());
            lvCountries.setAdapter(adapterCountry);
        }
    }

    public void notifyInternalAdapter() {
        ArrayAdapter adapter = (ArrayAdapter)lvCountries.getAdapter();
        adapter.notifyDataSetChanged();
    }
}