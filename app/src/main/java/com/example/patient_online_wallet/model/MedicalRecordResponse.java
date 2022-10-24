package com.example.patient_online_wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedicalRecordResponse {
    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("records")
    @Expose
    public List<MedicalRecord> records = null;

    @SerializedName("message")
    @Expose
    public String message;
}
