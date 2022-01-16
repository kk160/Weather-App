package de.hdmstuttgart.weatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;

import de.hdmstuttgart.weatherapp.viewmodels.LocationViewModel;
import de.hdmstuttgart.weatherapp.viewmodels.WeatherViewModel;

public class MainActivity extends AppCompatActivity implements LocationFragment.OnInteractionListener, SearchFragment.OnInteractionListenerSF {

    private LocationFragment locationFragment;
    //Google's API for location service
    private FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationViewModel.getInstance(MainActivity.this).requestLocation(MainActivity.this);
            LocationViewModel.getInstance(MainActivity.this).getLocation().observe(MainActivity.this, string -> {
                WeatherViewModel.getInstance(MainActivity.this).setLocation(string);
            });
        }
        else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        assignFragments();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


         if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
             LocationViewModel.getInstance(MainActivity.this).requestLocation(MainActivity.this);
             LifecycleOwner lifecycleOwner = MainActivity.this;
             LocationViewModel.getInstance(MainActivity.this).getLocation().observe(lifecycleOwner, string -> {
                 WeatherViewModel.getInstance(MainActivity.this).setLocation(string);
             });
         } else {
                WeatherViewModel.getInstance(MainActivity.this).setLocation("New York");
                Toast.makeText(this, "Permission Denied, Default City: New York", Toast.LENGTH_LONG).show();
         }

    }

    public void assignFragments() {
        locationFragment = new LocationFragment();
        DaysFragment daysFragment = new DaysFragment();
        WeatherFragment weatherFragment = new WeatherFragment();
        HoursFragment hoursFragment = new HoursFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.containerOne, locationFragment);
        fragmentTransaction.replace(R.id.containerTwo, daysFragment);
        fragmentTransaction.replace(R.id.containerThree, weatherFragment);
        fragmentTransaction.replace(R.id.containerFour, hoursFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changeFragment() {
        SearchFragment searchFragment = new SearchFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.containerOne, searchFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changeToLocationFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.containerOne, locationFragment);
        fragmentTransaction.commit();
    }
}

