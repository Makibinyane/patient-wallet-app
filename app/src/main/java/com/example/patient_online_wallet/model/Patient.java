package com.example.patient_online_wallet.model;

public class Patient {
    private final String patientName;
    private final String patientSurname;
    private final String cellphoneNumber;
    private final String email;
    private final String town;
    private final String province;
    private final String postalCode;
    private final String password;

    public Patient(String patientName, String patientSurname, String cellphoneNumber, String email,
                   String town, String province, String postalCode, String password) {
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.cellphoneNumber = cellphoneNumber;
        this.email = email;
        this.town = town;
        this.province = province;
        this.postalCode = postalCode;
        this.password = password;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTown() {
        return town;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPassword() {
        return password;
    }
}
