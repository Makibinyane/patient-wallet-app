package com.example.patient_online_wallet.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.patient_online_wallet.model.LoginResponse;
import com.example.patient_online_wallet.model.ServiceResponse;
import com.example.patient_online_wallet.service.PatientManagementService;
import com.example.patient_online_wallet.service.PatientManagementServiceClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class PatientManagementRepository {
    private final PatientManagementService service;

    public PatientManagementRepository() {
        service = PatientManagementServiceClient.getRetrofitInstance().create(PatientManagementService.class);
    }

    public LiveData<LoginResponse> userLogin(String email, String password) {
        final MutableLiveData<LoginResponse> data = new MutableLiveData<>();
        service.userLogin(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
                Log.d("DATA: ", response.body().message);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                data.setValue(null);
                Log.d("DATA: ", "Nothing");

            }
        });
        return data;
    }

    public LiveData<ServiceResponse> updatePatient(String patientID, String province, String town,
                                                String postalCode, String email, String cellphoneNumber) {
        final MutableLiveData<ServiceResponse> data = new MutableLiveData<>();
        service.updatePatient(patientID, province, town, postalCode, email, cellphoneNumber).enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
                Log.d("DATA: ", response.body().message);
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                data.setValue(null);
                Log.d("DATA: ", "Nothing");

            }
        });
        return data;
    }

    public LiveData<ServiceResponse> deletePatient(String patientID) {
        final MutableLiveData<ServiceResponse> data = new MutableLiveData<>();
        service.deletePatient(patientID).enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
                Log.d("DATA: ", response.body().message);
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                data.setValue(null);
                Log.d("DATA: ", "Nothing");

            }
        });
        return data;
    }

    public LiveData<LoginResponse> getPatientById(String patientID) {
        final MutableLiveData<LoginResponse> data = new MutableLiveData<>();
        service.getPatientById(patientID).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
                Log.d("DATA: ", response.body().message);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                data.setValue(null);
                Log.d("DATA: ", "Nothing");

            }
        });
        return data;
    }
}
