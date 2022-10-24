package com.example.patient_online_wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.patient_online_wallet.model.LoginResponse;
import com.example.patient_online_wallet.ui.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmail, txtPassword;
    private LoginViewModel loginViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Login");
        }

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        Button btnLogin = findViewById(R.id.btnLogin);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        progressBar = findViewById(R.id.simpleProgressBar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    private void Login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(txtPassword, "Please enter login credentials", Snackbar.LENGTH_LONG).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            loginViewModel.userLogin(email, password).observe(this, new Observer<LoginResponse>() {
                @Override
                public void onChanged(LoginResponse loginResponse) {
                    progressBar.setVisibility(View.GONE);
                    if (loginResponse != null) {
                        if (loginResponse.patient != null) {
                            if (Objects.equals(loginResponse.status, "1")) {
                                Intent intent = new Intent(getApplicationContext(), MedicalRecordActivity.class);
                                intent.putExtra("name", loginResponse.patient.get(0).patientName);
                                intent.putExtra("surname", loginResponse.patient.get(0).patientSurname);
                                intent.putExtra("email", loginResponse.patient.get(0).email);
                                intent.putExtra("patientId", loginResponse.patient.get(0).patientID);

                                intent.putExtra("patient", loginResponse.patient.get(0));
                                startActivity(intent);
                            } else {
                                Snackbar.make(txtPassword, loginResponse.message, Snackbar.LENGTH_LONG).show();
                            }
                        } else {
                            Snackbar.make(txtPassword, loginResponse.message, Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar.make(txtPassword, "Something went wrong", Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}