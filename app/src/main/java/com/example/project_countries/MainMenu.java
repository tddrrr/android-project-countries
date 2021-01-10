package com.example.project_countries;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.project_countries.asyncTask.AsyncTaskRunner;
import com.example.project_countries.asyncTask.Callback;
import com.example.project_countries.database.entities.ResultQuestion;
import com.example.project_countries.database.entities.User;
import com.example.project_countries.database.operations.ResultOperations;
import com.example.project_countries.database.operations.UserOperations;
import com.example.project_countries.fragmente.FragmentInvatare;
import com.example.project_countries.fragmente.FragmentProfil;
import com.example.project_countries.fragmente.FragmentTesteazaCunostintele;
import com.example.project_countries.fragmente.FragmentTop;
import com.example.project_countries.fragmente.FragmentWelcome;
import com.example.project_countries.network.HttpManager;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainMenu extends AppCompatActivity {
    private DrawerLayout drawerLayout; // container ul din activity main xml -> responsabil cu managementul meniului lateral
    private NavigationView navigationView;
    private Fragment currentFragment;
    private List<Country> countries = new ArrayList<>();
    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
    private SharedPreferences preferences;
    private static final String URL_COUNTRIES = "https://api.mocki.io/v1/d27e2a2b";
    private User user;
    TextView info, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getCountriesFromJSON();
        configNavigation();
        user = (User)getIntent().getSerializableExtra("user");

        // luam informatia din navigation view si punem din baza de date numele si emailul
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        info =(TextView) headerView.findViewById(R.id.nav_header_info);
        String a = user.getFirstName()+user.getLastName();
        info.setText(a);

        email = (TextView) headerView.findViewById(R.id.nav_header_email);
        email.setText(user.getEmail());

        navigationView = findViewById(R.id.nav_view);
        preferences = getSharedPreferences(Login.LOGIN_SHARED_PREF, MODE_PRIVATE);
        updateUserScore();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_nav_profil) {
                    //am dat click pe home
                    currentFragment = new FragmentProfil(user);
                } else if (item.getItemId() == R.id.main_nav_invata)
                {
                    //am dat click pe home
                    currentFragment = FragmentInvatare.newInstance((ArrayList<Country>) countries);

                } else if (item.getItemId() == R.id.main_nav_test) {
                    //am dat click pe home
                    currentFragment = new FragmentTesteazaCunostintele();

                } else if (item.getItemId() == R.id.main_nav_logout)
                {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear().commit();
                    //schimb la mainActivity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
                } else
                    {
                    currentFragment = new FragmentTop();
                }
                openFragment();
                drawerLayout.closeDrawer(GravityCompat.START); // pentru ca atunci cand selectam sa se inchida meniul
                return true;
            }
        });
    }

    private void getCountriesFromJSON() {
        Callable<String> asyncOperation = new HttpManager(URL_COUNTRIES); //http manager implements callable deci il putem socoti ca si callable
        //am definit op asincrona, facem callbackul
        Callback<String> mainThreadOperation = new Callback<String>() {
            @Override
            public void runResultOnUiTread(String result) { //am intrat in activitate
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                countries.addAll(CountryJSONParser.fromJSON(result));
//                notifyAdapter();
            }
        };
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private void configNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar); // bara de sus, componenta vizuala
        setSupportActionBar(toolbar); // acest set asigura faptul ca android va interpreta orice click de pe bara
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle( // acesta este burger menu
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open, // texte pentru fallback (daca nu functioneaza deschiderea, sa ne afiseze mesajul
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle); // aici este atasata dechiderea meniului lateral
        actionBarDrawerToggle.syncState(); // realizeaza rasucirea burger-ului
        currentFragment = new FragmentWelcome(); // putem inlocui cu un mesaj de bun venit
        openFragment();
    }

    private void openFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_container, currentFragment) // aici pot fi oricate metode de replace
                .commit();
    }

    private void updateUserScore() {
        int idUser = preferences.getInt(Login.id_user, MODE_PRIVATE);
        ResultOperations resultOperations = new ResultOperations(getApplicationContext());
        UserOperations userOperations = new UserOperations(getApplicationContext());
        resultOperations.getScore(result -> {
            Log.d("scoreResult", result.toString());
            userOperations.updateUserScore(result1 -> {}, idUser, result);
        }, idUser);
    }
}