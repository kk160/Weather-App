package de.hdmstuttgart.weatherapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HourViewModel extends ViewModel {
    private final MutableLiveData<Integer> selected = new MutableLiveData<>(0);


    /**
     * Method to ser selected
     * */
    public void select(int i){
        selected.setValue(i);
    }

    /**
     * Method to get selected
     * */
    public LiveData<Integer> getSelected(){
        return selected;
    }
}
