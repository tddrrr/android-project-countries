package com.example.proiect_bd_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    public static final String LOGIN_SHARED_PREF = "loginSharedPref";
    public static final String LOGGED_IN = "loggedIN";
    private Button btnOk;
    private TextInputEditText tietEmail;
    private TextInputEditText tietPassword;
    private CheckBox cbRemember;
    private SharedPreferences preferences;

    private void initComp(){
        btnOk = findViewById(R.id.btn_login_ok);
        tietEmail = findViewById(R.id.login_email);
        tietPassword = findViewById(R.id.login_password);
        cbRemember = findViewById(R.id.cb_remember);
        cbRemember.isChecked();
        //construire fisier de preferinte
        preferences = getSharedPreferences(LOGIN_SHARED_PREF, MODE_PRIVATE);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateComponents()) {
                    loginUser();
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initComp();
    }

    private void loginUser() {
        //salvarea in fisierul de preferinte
        SharedPreferences.Editor editor = preferences.edit();
        if (cbRemember.isChecked()) {
            editor.putBoolean(LOGGED_IN, true);
        }
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(intent);
    }

    private boolean validateComponents() {
        if(tietEmail.getText().toString().isEmpty() || tietPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.enter_credentials, Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!tietEmail.getText().toString().contains("@")){
            Toast.makeText(getApplicationContext(), R.string.invalid_email, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}