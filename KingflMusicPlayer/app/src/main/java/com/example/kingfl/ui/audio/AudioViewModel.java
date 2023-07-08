package com.example.kingfl.ui.audio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AudioViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    public AudioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No Audio list");
    }
    public LiveData<String> getText(){
        return mText;
    }
}
