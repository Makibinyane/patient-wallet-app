package com.example.patient_online_wallet.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.patient_online_wallet.model.ServiceResponse;
import com.example.patient_online_wallet.repository.PatientManagementRepository;

public class EditPatientViewModel extends AndroidViewModel {
    private final PatientManagementRepository repository;

    public EditPatientViewModel(@NonNull Application application) {
        super(application);
        repository = new PatientManagementRepository();
    }

    public LiveData<ServiceResponse> updatePatient(String patientID, String province, String town,
                                                   String postalCode, String email, String cellphoneNumber) {
        return repository.updatePatient(patientID, province, town, postalCode, email, cellphoneNumber);
    }
}
