package com.example.foodcall.ui.LogOut;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class logoutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public logoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is logout fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}