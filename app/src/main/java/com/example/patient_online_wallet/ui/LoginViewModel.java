package com.example.patient_online_wallet.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.patient_online_wallet.model.LoginResponse;
import com.example.patient_online_wallet.repository.PatientManagementRepository;

public class LoginViewModel extends AndroidViewModel {
    private final PatientManagementRepository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new PatientManagementRepository();
    }

    public LiveData<LoginResponse> userLogin(String email, String password) {
        return repository.userLogin(email, password);
    }
}
