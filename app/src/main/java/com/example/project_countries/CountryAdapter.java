package com.example.project_countries;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_countries.asyncTask.AsyncTaskRunner;
import com.example.project_countries.asyncTask.Callback;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

public class CountryAdapter extends ArrayAdapter<Country>{ //adapter doar pt tipul de liste country
    private Context context;
    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
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
            getImageFromURL(view, country.getFlagURL());
        }
        return view;
    }

    private void getImageFromURL(final View view, final String flagUrl) {
        Callable<Bitmap> callable = new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                URL url = new URL(flagUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;
            }
        };
        asyncTaskRunner.executeAsync(callable, new Callback<Bitmap>() {
            @Override
            public void runResultOnUiTread(Bitmap result) {
                if(result != null){
                    ImageView imageView = view.findViewById(R.id.lv_row_flag);
                    imageView.setImageBitmap(result);
                }
            }
        });
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
