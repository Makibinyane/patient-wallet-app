package com.example.patient_online_wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("patient")
    @Expose
    public List<PatientResponse> patient = null;

    @SerializedName("message")
    @Expose
    public String message;
}
