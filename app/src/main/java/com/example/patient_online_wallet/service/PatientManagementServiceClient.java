package com.example.patient_online_wallet.service;

import com.example.patient_online_wallet.ui.SslCertificateUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientManagementServiceClient {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        SslCertificateUtil certificateUtil = new SslCertificateUtil();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://landmark-finder.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(certificateUtil.getUnsafeOkHttpClient().build())
                    .build();
        }
        return retrofit;
    }
}
