package com.example.proiect_bd_tema;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.proiect_bd_tema.asyncTask.AsyncTaskRunner;
import com.example.proiect_bd_tema.asyncTask.Callback;
import com.example.proiect_bd_tema.fragmente.FragmentInvatare;
import com.example.proiect_bd_tema.fragmente.FragmentProfil;
import com.example.proiect_bd_tema.network.HttpManager;
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
    private static final String URL_COUNTRIES = "https://api.mocki.io/v1/f6024002";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getCountriesFromJSON();
        configNavigation();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_nav_profil){
                    //am dat click pe home
                    currentFragment = new FragmentProfil();
                    Toast.makeText(getApplicationContext(),
                            "Profil",
                            Toast.LENGTH_LONG).show();
                }else
                    if (item.getItemId() == R.id.main_nav_invata){
                    //am dat click pe home
                    currentFragment = FragmentInvatare.newInstance((ArrayList<Country>) countries);
                    Toast.makeText(getApplicationContext(),
                            "Invata",
                            Toast.LENGTH_LONG).show();
                }
                else if (item.getItemId() == R.id.main_nav_test){
                    //am dat click pe home
                    currentFragment = new FragmentProfil();
                    Toast.makeText(getApplicationContext(),
                            "Test",
                            Toast.LENGTH_LONG).show();
                }
                    else{
                    currentFragment = new FragmentProfil();
                    Toast.makeText(getApplicationContext(),
                            "Show score",
                            Toast.LENGTH_LONG).show();
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
        currentFragment = new FragmentProfil(); // putem inlocui cu un mesaj de bun venit
        openFragment();
    }

    private void openFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_container,currentFragment) // aici pot fi oricate metode de replace
                .commit();
    }
}