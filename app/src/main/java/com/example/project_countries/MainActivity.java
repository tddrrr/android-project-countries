package com.example.project_countries;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project_countries.database.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_USER_REQUEST_CODE = 210;

    private Button btnLogin;
    private  Button btnCreateAccount;
    private List<User> users = new ArrayList<>();
    private ListView lvusers;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(Login.LOGIN_SHARED_PREF, MODE_PRIVATE);
        initComponent();
        checkUserLoggedIn();

    }
    private void initComponent(){
        btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(changeActivityOnLogin());
        btnCreateAccount=findViewById(R.id.btn_createAcc);
        btnCreateAccount.setOnClickListener(startActivityOnCreate());
    }

    private View.OnClickListener startActivityOnCreate() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateAccount.class );
                startActivityForResult(intent, NEW_USER_REQUEST_CODE);
            }
        };
    }
    private View.OnClickListener changeActivityOnLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class );
                startActivity(intent);
                //hei hei
            }
        };
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

    private void checkUserLoggedIn() {
        boolean rememberMe = preferences.getBoolean(Login.LOGGED_IN, false);
        if (rememberMe) {
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            finish();
            startActivity(intent);
        }
    }
}