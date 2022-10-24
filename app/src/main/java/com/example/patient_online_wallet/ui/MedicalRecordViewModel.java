package com.example.patient_online_wallet.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.patient_online_wallet.model.LoginResponse;
import com.example.patient_online_wallet.model.MedicalRecordResponse;
import com.example.patient_online_wallet.repository.MedicalRecordRepository;
import com.example.patient_online_wallet.repository.PatientManagementRepository;

public class MedicalRecordViewModel extends AndroidViewModel {
    private final MedicalRecordRepository repository;

    public MedicalRecordViewModel(@NonNull Application application) {
        super(application);
        repository = new MedicalRecordRepository();
    }

    public LiveData<MedicalRecordResponse> getMedicalRecords(String patientId) {
        return repository.getMedicalRecords(patientId);
    }
}
