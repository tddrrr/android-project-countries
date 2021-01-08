package com.example.proiect_bd_tema;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_USER_REQUEST_CODE = 210;

    private Button btnLogin;
    private  Button btnCreateAccount;
    private List<User> users = new ArrayList<>();
    private ListView lvusers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Buzau", "Ioana","buzaulioana@gmail.com","ioaaana23"));
//        CountryDatabase.getInstance(this).countryDAO().insertCountry(new Country("Romania", "Bucharest","romanian", "RON", "Klaus Iohannis"));
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Sofia"));
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Teodora2"));
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Teodora3"));

//       List<User> users = UserDatabase.getInstance(this).userDAO().getAllUsers();
//       Log.v("users_from_db", users.toString());
//
////       List <Country> countries = CountryDatabase.getInstance(this).countryDAO().getAllCountries();
//       Log.v("countries_from_dc", countries.toString());
//        writePreferences();
//        readPreferences();
        initComponent();


    }
    private void initComponent(){
        btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class );
                startActivity(intent);
                //hei hei
            }
        });
        btnCreateAccount=findViewById(R.id.btn_createAcc);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateAccount.class );
                startActivityForResult(intent, NEW_USER_REQUEST_CODE);
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

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verificam ca requestCode-ul este cel pe care l-am trimis ca parametru pe startActivityForResult
        //verificam ca resultCode-ul este cel setat in AddActivity pe setResult
        //verificam ca intent-ul nu a venit null, deoarece l-am populat cu studentul introdus
        if (requestCode == NEW_USER_REQUEST_CODE
                && resultCode == RESULT_OK && data != null) {
            //preluare student din intent
            User user = (User) data
                    .getSerializableExtra(CreateAccount.USER_KEY);
            if (user != null) {
                //afisarea studentului pe care l-am primit din AddActivity
                Toast.makeText(getApplicationContext(),
                        getString(R.string.firstName),
                        Toast.LENGTH_LONG).show();
                users.add(user);
                Log.d("TAG",user.toString());
                //preluare adapter setat pe ListView
                //ArrayAdapter adapter = (ArrayAdapter) lvusers.getAdapter();
                //notificare adapter pentru redesenarea valorilor pe ecran, deoarece am modificat
                //lista de studenti si ar trebui sa avem unul nou pe ecran
                //adapter.notifyDataSetChanged();
            }
        }
    }
}