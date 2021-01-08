package com.example.proiect_bd_tema;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryJSONParser {

    public static List<Country> fromJSON(String json) {
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            JSONArray array = new JSONArray(json);
            return getCountriesFromJSON(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<Country> getCountriesFromJSON(JSONArray array) throws JSONException {
        List<Country> results = new ArrayList<>();
        for (int i=0; i< array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            String name = object.getString("name");
            String capital = object.getString("capital");
            String currency = object.getString("currency");
            String language = object.getString("language");
            String systemOfGovernment = object.getString("systemOfGovernment");
            String population = object.getString("pop");
            String flagURL = object.getString("flag");
            //imi citesc atributele din json si fac un obiect nou
            Country country = new Country(name, capital, currency, language, systemOfGovernment, population, flagURL);
            //adaug obiectul la lista
            results.add(country);
        }
        return results;
    }
}
