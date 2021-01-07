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

import com.example.proiect_bd_tema.fragmente.FragmentProfil;
import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity {
    private DrawerLayout drawerLayout; // container ul din activity main xml -> responsabil cu managementul meniului lateral
    private NavigationView navigationView;
    private Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
                }
                else if (item.getItemId() == R.id.main_nav_test){
                    //am dat click pe home
                    Toast.makeText(getApplicationContext(),
                            "Test",
                            Toast.LENGTH_LONG).show();
                }
                    else{
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
    }

    private void openFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_container,currentFragment)
                .commit();
    }
}