package com.example.patient_online_wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicalRecord {
    @SerializedName("RecordTitle")
    @Expose
    public String recordTitle;

    @SerializedName("Description")
    @Expose
    public String description;

    @SerializedName("Date")
    @Expose
    public String date;

    @SerializedName("SugarLevel")
    @Expose
    public String sugarLevel;

    @SerializedName("BloodPressure")
    @Expose
    public String bloodPressure;

    @SerializedName("AdditionalInformation")
    @Expose
    public String additionalInformation;

    @SerializedName("MedicationPrescribed")
    @Expose
    public String medicationPrescribed;


}
