package de.hdmstuttgart.weatherapp.viewmodels;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import de.hdmstuttgart.weatherapp.LoadingDialog;

/**
 * Class containing the LocationViewModel instance and allowing to set and observe data
 * */
public class LocationViewModel extends ViewModel {

    private MutableLiveData<String> location = new MutableLiveData<>();
    private static LocationViewModel instance;
    private LocationRequest locationRequest;

    /**
     * Method to get the LocationViewModel instance
     *
     * @param context of the current state of the application
     * */
    public static LocationViewModel getInstance(Context context) {
        if(instance == null){
            instance = new ViewModelProvider((ViewModelStoreOwner) context).get(LocationViewModel.class);
        }
        return instance;
    }

    /**
     * Method to get location
     * */
    public LiveData<String> getLocation(){
        return location;
    }

    /**
     * Method to request the current location
     * */
    public void requestLocation(Context context) {
        LoadingDialog.getLoadingDialog((Activity) context).startLoadingDialog();
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (isGPSEnable(context)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            LocationServices.getFusedLocationProviderClient(context)
                    .requestLocationUpdates(locationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            super.onLocationResult(locationResult);

                            LocationServices.getFusedLocationProviderClient(context)
                                    .removeLocationUpdates(this);

                            if (locationResult.getLocations().size() > 0) {
                                int index = locationResult.getLocations().size() - 1;
                                double lat = locationResult.getLocations().get(index).getLatitude();
                                double lon = locationResult.getLocations().get(index).getLongitude();

                                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                                try {
                                    List<Address> addressList = geocoder.getFromLocation(lat, lon, 1);
                                    location.postValue(addressList.get(0).getLocality());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, Looper.getMainLooper());
        }else{
            turnOnGPS(context);
        }
    }

    /**
     * Methode to turn on GPS
     * */
    private void turnOnGPS(Context context) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(context.getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(task -> {

            try {
                LocationSettingsResponse response = task.getResult(ApiException.class);
                Toast.makeText(context, "GPS is already tured on", Toast.LENGTH_SHORT).show();

            } catch (ApiException e) {

                switch (e.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                        try {
                            ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                            resolvableApiException.startResolutionForResult((Activity) context, 2);
                        } catch (IntentSender.SendIntentException ex) {
                            ex.printStackTrace();
                        }
                        requestLocation(context);
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        //Device does not have location
                        break;
                }
            }
        });

    }

    /**
     * Methode to control if GPS is enable
     * */
    private boolean isGPSEnable(Context context) {
        LocationManager locationManager = null;
        boolean isEnable = false;

        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }

        isEnable = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
        return isEnable;
    }
}
