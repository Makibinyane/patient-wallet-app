package com.example.patient_online_wallet.service;

import com.example.patient_online_wallet.model.MedicalRecordResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MedicalRecordService {
    @FormUrlEncoded
    @POST("online_wallet_get_medical_records.php")
    Call<MedicalRecordResponse> getMedicalRecords(@Field("patientId") String patientID);
}
