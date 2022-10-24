package com.example.patient_online_wallet.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.patient_online_wallet.model.LoginResponse;
import com.example.patient_online_wallet.model.ServiceResponse;
import com.example.patient_online_wallet.repository.PatientManagementRepository;

public class PatientProfileViewModel extends AndroidViewModel {
    private final PatientManagementRepository repository;

    public PatientProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new PatientManagementRepository();
    }

    public LiveData<ServiceResponse> deletePatient(String patientID) {
        return repository.deletePatient(patientID);
    }

    public LiveData<LoginResponse> getPatientById(String patientID) {
        return repository.getPatientById(patientID);
    }
}
