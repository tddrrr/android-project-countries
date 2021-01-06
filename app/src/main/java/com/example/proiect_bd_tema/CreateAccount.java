package com.example.proiect_bd_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccount extends AppCompatActivity {
    private Button btnOk;
    public static final String USER_KEY = "user_key";
    private  Intent intent;

    private void initComp(){
        btnOk=findViewById(R.id.create_acc_btn_ok);
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
        return  true;
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
        User a = new User("a", "a","a","a");
        return a;
    }

}