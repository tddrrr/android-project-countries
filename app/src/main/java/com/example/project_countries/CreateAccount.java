package com.example.project_countries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project_countries.asyncTask.Callback;
import com.example.project_countries.database.entities.User;
import com.example.project_countries.database.operations.UserOperations;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CreateAccount extends AppCompatActivity {
    private List<User> users;
    private Button btnOk;
    public static final String USER_KEY = "user_key";
    private TextInputEditText tietFirstname;
    private TextInputEditText tietLastname;
    private TextInputEditText tietEmail;
    private TextInputEditText tietPassword;
    private  Intent intent;
    UserOperations userOperations;

    private void initComp(){
        btnOk=findViewById(R.id.create_acc_btn_ok);
        tietFirstname = findViewById(R.id.create_acc_tied_firstName);
        tietLastname = findViewById(R.id.create_acc_tied_lastName);
        tietEmail = findViewById(R.id.create_acc_tiet_email);
        tietPassword = findViewById(R.id.create_acc_tiet_password);
        btnOk.setOnClickListener(addSaveClickEvent());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        userOperations = new UserOperations(getApplicationContext());
        initComp();
//        intent=getIntent();
    }
    private boolean validate(){
        if (tietFirstname.getText().toString().length() < 2) {
            Toast.makeText(getApplicationContext(), "Enter a valid name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tietLastname.getText().toString().length() < 2) {
            Toast.makeText(getApplicationContext(), "Enter a valid name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!tietEmail.getText().toString().trim().contains("@")){
            Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tietPassword.getText().toString().length()<5){
            Toast.makeText(getApplicationContext(), "Enter a valid password larger than 5 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private View.OnClickListener addSaveClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validarea campurilor de intrare
                if (validate()) {
                    //construire obiect java cu informatiile din interfata
                    createUser();
                    //punere in intent a studentului pe care dorim sa-l trimitem catre Login
                    Intent intent = new Intent(getApplicationContext(), Login.class);
//                    finish();
                    startActivity(intent);
                }
            }
        };
    }
    private void createUser(){
        String firstName = tietFirstname.getText().toString();
        String lastName = tietLastname.getText().toString();
        String email = tietEmail.getText().toString();
        String password = tietPassword.getText().toString();
        User user = new User(firstName, lastName, email, password);
        userOperations.insert(insertUserCallback(), user);
    }

    private Callback<User> insertUserCallback() {
        return new Callback<User>() {
            @Override
            public void runResultOnUiTread(User result) {
                if (result == null) {
                    Toast.makeText(getApplicationContext(), R.string.error_insert_user, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.success_insert_user, Toast.LENGTH_LONG).show();
                }
            }
        };
    }

}