package com.example.patient_online_wallet.service;

import com.example.patient_online_wallet.model.LoginResponse;
import com.example.patient_online_wallet.model.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PatientManagementService {
    @FormUrlEncoded
    @POST("online_wallet_login.php")
    Call<LoginResponse> userLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("online_wallet_update_user.php")
    Call<ServiceResponse> updatePatient(@Field("patientId") String patientID, @Field("province") String province, @Field("town") String town, @Field("postalCode") String postalCode, @Field("email") String email, @Field("phoneNumber") String cellphoneNumber);

    @FormUrlEncoded
    @POST("online_wallet_delete_user.php")
    Call<ServiceResponse> deletePatient(@Field("patientId") String patientID);

    @FormUrlEncoded
    @POST("online_wallet_get_patient_by_id.php")
    Call<LoginResponse> getPatientById(@Field("patientId") String patientID);

}
