package com.example.patient_online_wallet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientResponse implements Parcelable {
    @SerializedName("PatientID")
    @Expose
    public String patientID;

    @SerializedName("PatientName")
    @Expose
    public String patientName;

    @SerializedName("PatientSurname")
    @Expose
    public String patientSurname;

    @SerializedName("CellphoneNumber")
    @Expose
    public String cellphoneNumber;

    @SerializedName("Email")
    @Expose
    public String email;

    @SerializedName("Town")
    @Expose
    public String town;

    @SerializedName("Province")
    @Expose
    public String province;

    @SerializedName("PostalCode")
    @Expose
    public String postalCode;

    @SerializedName("Password")
    @Expose
    public String password;

    protected PatientResponse(Parcel in) {
        patientID = in.readString();
        patientName = in.readString();
        patientSurname = in.readString();
        cellphoneNumber = in.readString();
        email = in.readString();
        town = in.readString();
        province = in.readString();
        postalCode = in.readString();
        password = in.readString();
    }

    public static final Creator<PatientResponse> CREATOR = new Creator<PatientResponse>() {
        @Override
        public PatientResponse createFromParcel(Parcel in) {
            return new PatientResponse(in);
        }

        @Override
        public PatientResponse[] newArray(int size) {
            return new PatientResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(patientID);
        parcel.writeString(patientName);
        parcel.writeString(patientSurname);
        parcel.writeString(cellphoneNumber);
        parcel.writeString(email);
        parcel.writeString(town);
        parcel.writeString(province);
        parcel.writeString(postalCode);
        parcel.writeString(password);
    }
}
