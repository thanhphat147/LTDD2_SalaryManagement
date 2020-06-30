package com.example.quanlyluong.ui.quanlytamung;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DSTamUngViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DSTamUngViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}