package com.example.patient_online_wallet.service;

import com.example.patient_online_wallet.ui.SslCertificateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicalRecordServiceClient {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        SslCertificateUtil certificateUtil = new SslCertificateUtil();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://landmark-finder.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(certificateUtil.getUnsafeOkHttpClient().build())
                    .build();
        }
        return retrofit;
    }
}
