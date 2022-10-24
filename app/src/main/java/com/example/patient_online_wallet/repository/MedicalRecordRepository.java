package com.example.patient_online_wallet.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.patient_online_wallet.model.MedicalRecordResponse;
import com.example.patient_online_wallet.service.MedicalRecordService;
import com.example.patient_online_wallet.service.MedicalRecordServiceClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalRecordRepository {
    private final MedicalRecordService service;

    public MedicalRecordRepository() {
        service = MedicalRecordServiceClient.getRetrofitInstance().create(MedicalRecordService.class);
    }

    public LiveData<MedicalRecordResponse> getMedicalRecords(String patientId) {
        final MutableLiveData<MedicalRecordResponse> data = new MutableLiveData<>();
        service.getMedicalRecords(patientId).enqueue(new Callback<MedicalRecordResponse>() {
            @Override
            public void onResponse(Call<MedicalRecordResponse> call, Response<MedicalRecordResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
                Log.d("DATA: ", response.body().status);
            }

            @Override
            public void onFailure(Call<MedicalRecordResponse> call, Throwable t) {
                data.setValue(null);
                Log.d("DATA: ", "Nothing");

            }
        });
        return data;
    }
}
