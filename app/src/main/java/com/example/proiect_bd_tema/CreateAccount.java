package com.example.proiect_bd_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CreateAccount extends AppCompatActivity {
    private Button btnOk;
    public static final String USER_KEY = "user_key";
    private TextInputEditText tietFirstname;
    private TextInputEditText tietLastname;
    private TextInputEditText tietEmail;
    private TextInputEditText tietPassword;
    private  Intent intent;

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
        initComp();
        intent=getIntent();
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
                    User user = createUser();
                    //punere in intent a studentului pe care dorim sa-l trimitem catre MainActivity
                    intent.putExtra(USER_KEY, user);
                    //trimiterea intent-ului catre MainActivity
                    setResult(RESULT_OK, intent);
                    //inchidere activitate curenta
                    finish();
                }
            }
        };
    }
    private User createUser(){
        String firstName = tietFirstname.getText().toString();
        String lastName = tietLastname.getText().toString();
        String email = tietEmail.getText().toString();
        String password = tietPassword.getText().toString();
        User user = new User(firstName, lastName, email, password);
        return user;
    }

}