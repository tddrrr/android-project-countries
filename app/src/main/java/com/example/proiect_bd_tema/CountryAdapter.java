package com.example.proiect_bd_tema;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class CountryAdapter extends ArrayAdapter<Country>{ //adapter doar pt tipul de liste country
    private Context context;
    private int resource;
    private List<Country> countries;
    private LayoutInflater inflater;

    public CountryAdapter(@NonNull Context context, int resource, @NonNull List<Country> objects, LayoutInflater inflater) { //context,lv_row_view,lista de obiecte
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.countries = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false); //resource e lv row view invatare; attachToRoot e pt expandable listview
        Country country = countries.get(position);
        if (country != null) {
            addCountryName(view, country.getName());
            addCapitalName(view, country.getCapital());
            addCurrency(view, country.getCurrency());
            addLanguage(view, country.getLanguage());
            addGovernment(view, country.getSystemOfGovernment());
            addPopulation(view, country.getPopulation());

        }
        return view;
    }

    //metode
    private void addCountryName(View view, String countryName) {
        TextView textView = view.findViewById(R.id.tv_row_country_name);
        populateTextViewContent(countryName, textView);
    }

    private void addCapitalName(View view, String capital) {
        TextView textView = view.findViewById(R.id.tv_capital_name); //view e tot continutul, selectez tv-ul
        populateTextViewContent(capital, textView);
    }

    private void addLanguage(View view, String language) {
        TextView textView = view.findViewById(R.id.tv_language); //view e tot continutul, selectez tv-ul
        populateTextViewContent(language, textView);
    }

    private void addCurrency(View view, String currency) {
        TextView textView = view.findViewById(R.id.tv_currency); //view e tot continutul, selectez tv-ul
        populateTextViewContent(currency, textView);
    }

    private void addGovernment(View view, String government) {
        TextView textView = view.findViewById(R.id.tv_systemOfGovernment); //view e tot continutul, selectez tv-ul
        populateTextViewContent(government, textView);
    }

    private void addPopulation(View view, String population) {
        TextView textView = view.findViewById(R.id.tv_population); //view e tot continutul, selectez tv-ul
        populateTextViewContent(population, textView);
    }

    private void populateTextViewContent(String value, TextView textView) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.lv_row_default_value);
        }
    }


}
