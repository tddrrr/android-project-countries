package com.example.project_countries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.project_countries.database.operations.UserOperations;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    public static final String LOGIN_SHARED_PREF = "loginSharedPref";
    public static final String LOGGED_IN = "loggedIN";
    public static final String id_user = "idUser";
    public static final String NAME_USER = "nameUser";
    private Button btnOk;
    private TextInputEditText tietEmail;
    private TextInputEditText tietPassword;
    private CheckBox cbRemember;
    private SharedPreferences preferences;
    UserOperations userOperations;

    private void initComp(){
        btnOk = findViewById(R.id.btn_login_ok);
        tietEmail = findViewById(R.id.login_email);
        tietPassword = findViewById(R.id.login_password);
        cbRemember = findViewById(R.id.cb_remember);
        cbRemember.isChecked();
        //construire fisier de preferinte
        preferences = getSharedPreferences(LOGIN_SHARED_PREF, MODE_PRIVATE);

        btnOk.setOnClickListener(logUser());
    }

    private View.OnClickListener logUser() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateComponents()) {
                    loginUser();
                    //TODO: trebuie sa verificam daca in baza de date avem emailul si parola respectiva
                }
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userOperations = new UserOperations(getApplicationContext());
        initComp();
    }

    private void loginUser() {
        userOperations.findUserByEmail(tietEmail.getText().toString(), result -> {
            if (result == null) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_credentials), Toast.LENGTH_SHORT).show();
            } else {
                if (result.getPassword().equals(tietPassword.getText().toString())) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putLong(id_user, result.getUserId());
                    if (cbRemember.isChecked()) {
                        editor.putBoolean(LOGGED_IN, true);
                    }
                    editor.putString(NAME_USER, result.getFirstName());
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
        //salvarea in fisierul de preferinte

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