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

import com.example.project_countries.asyncTask.Callback;
import com.example.project_countries.database.entities.Question;
import com.example.project_countries.database.entities.User;
import com.example.project_countries.database.manager.DatabaseManager;
import com.example.project_countries.database.operations.QuestionOperations;
import com.example.project_countries.database.operations.UserOperations;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_USER_REQUEST_CODE = 210;

    private Button btnLogin;
    private  Button btnCreateAccount;
    private List<User> users = new ArrayList<>();
    private SharedPreferences preferences;
    UserOperations userOperations;
    QuestionOperations questionOperations;
    private User user;
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(Login.LOGIN_SHARED_PREF, MODE_PRIVATE);
        initComponent();
        getAllQuestions();
        checkUserLoggedIn();
    }
    private void initComponent(){
        btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(changeActivityOnLogin());
        btnCreateAccount=findViewById(R.id.btn_createAcc);
        btnCreateAccount.setOnClickListener(startActivityOnCreate());
        userOperations = new UserOperations(getApplicationContext());
        questionOperations = new QuestionOperations(getApplicationContext());
    }

    private View.OnClickListener startActivityOnCreate() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                    startActivityForResult(intent, NEW_USER_REQUEST_CODE);
            }
        };
    }

    private boolean createAccount() {
        return false;
    }

    private View.OnClickListener changeActivityOnLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
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
                this.user = user;

            }
        }
    }

    private void checkUserLoggedIn() {
        boolean rememberMe = preferences.getBoolean(Login.LOGGED_IN, false);
        int id = preferences.getInt(Login.id_user, -1);
        if (rememberMe) {
            userOperations.findUserById(result -> {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                intent.putExtra("user", result);
                startActivity(intent);
                finish();
            }, id);
        }
    }

    private void insertQuestion(Question question) {
        questionOperations.insert(result -> {
        }, question);
    }

    private void insertQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Care este capitala Romaniei?", "Bucuresti", "Budapesta", "Bucuresti",
                "Targoviste", "Bacau"));
        questions.add(new Question("Care este moneda Bulgariei?", "leva", "euro", "ron",
                "leva", "dolar"));
        questions.add(new Question("Care este capitala Suediei?", "Stockholm", "Stockholm", "Strasbourg",
                "Minsk", "Helsinki"));
        questions.add(new Question("Care este moneda Marii Britanii?", "sterling pound", "ron", "dolar",
                "sterling pound", "euro"));
        questions.add(new Question("Care este sistemul guvernamental al Spaniei?", "monarhie constitutionala",
                "republica", "monarhie constitutionala",
                "dictatura", "republica semi-prezidentiala"));
        for (Question question:
             questions) {
            insertQuestion(question);
        }
    }

    private void getAllQuestions() {
        questionOperations.getAll(result -> {
            if (result != null && result.size() == 0) { //daca am un array gol, inserez entries
                insertQuestions();
            }
        });
    }
}