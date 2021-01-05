package com.example.proiect_bd_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin = findViewById(R.id.btn_login);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserDatabase.getInstance(this).userDAO().insertUser(new User("Buzau", "Ioana","buzaulioana@gmail.com","ioaaana23"));
        CountryDatabase.getInstance(this).countryDAO().insertCountry(new Country("Romania", "Bucharest","romanian", "RON", "Klaus Iohannis"));
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Sofia"));
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Teodora2"));
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Teodora3"));

       List<User> users = UserDatabase.getInstance(this).userDAO().getAllUsers();
       Log.v("users_from_db", users.toString());

       List <Country> countries = CountryDatabase.getInstance(this).countryDAO().getAllCountries();
       Log.v("countries_from_dc", countries.toString());
        writePreferences();
        readPreferences();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class );
                startActivity(intent);
            }
        });


    }

    private void writePreferences() {
        SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", "Nume");
        editor.putInt("intValue", 22);
        editor.putInt("varsta", 45);

        editor.commit();
    }
    private void readPreferences() {
        SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        Log.v("read_string", preferences.getString("username", "N/A"));
        Log.v("read_int", preferences.getInt("varsta", 0)+"");
        Log.v("read_string", preferences.getString("dgs", "Not found"));

    }

}