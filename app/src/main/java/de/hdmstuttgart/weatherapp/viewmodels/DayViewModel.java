package de.hdmstuttgart.weatherapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DayViewModel extends ViewModel {
    private final MutableLiveData<Integer> selected = new MutableLiveData<>(0);

    /**
     * Method to set selected
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
