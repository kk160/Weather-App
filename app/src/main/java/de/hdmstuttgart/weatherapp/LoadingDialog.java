package de.hdmstuttgart.weatherapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {
    private final Activity activity;
    private AlertDialog alertDialog;
    @SuppressLint("StaticFieldLeak")
    private static LoadingDialog instance;

    /**
     * Constructor LoadingDialog
     * */
    public LoadingDialog(Activity activity){
        this.activity = activity;
    }

    /**
     * Method to get instance
     * */
    public static LoadingDialog getLoadingDialog(Activity activity){
        if(instance == null){
            instance = new LoadingDialog(activity);
        }
        return instance;
    }

    /**
     * Method to start the LoadingDialog
     * */
    @SuppressLint("InflateParams")
    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog, null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Method to end LoadingDialog
     * */
    public void endLoadingDialog(){
        alertDialog.dismiss();
    }
}
