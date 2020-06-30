package com.example.quanlyluong.ui.quanlychamcong;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DSChamCongViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DSChamCongViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("danh sách chấm công");
    }

    public LiveData<String> getText() {
        return mText;
    }
}