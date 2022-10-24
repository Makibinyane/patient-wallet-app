package com.example.patient_online_wallet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patient_online_wallet.model.LoginResponse;
import com.example.patient_online_wallet.model.PatientResponse;
import com.example.patient_online_wallet.model.ServiceResponse;
import com.example.patient_online_wallet.ui.PatientProfileViewModel;
import com.google.android.material.snackbar.Snackbar;

public class ProfileManagementActivity extends AppCompatActivity {
    private PatientProfileViewModel patientProfileViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        patientProfileViewModel = new ViewModelProvider(this).get(PatientProfileViewModel.class);

        TextView txtFirstname = findViewById(R.id.txtDisplayName);
        TextView txtLastname = findViewById(R.id.txtDisplaySurname);

        TextView txtEmail = findViewById(R.id.txtDisplayEmail);
        TextView txtCellphoneNumber = findViewById(R.id.txtDisplayCellNumber);

        TextView txtProvince = findViewById(R.id.txtDisplayProvince);
        TextView txtTown = findViewById(R.id.txtDisplayTown);
        TextView txtPostalCode = findViewById(R.id.txtDisplayPostalCode);

        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnDelete = findViewById(R.id.btnDelete);

        progressBar = findViewById(R.id.simpleProgressBar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Patient Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Intent intent = getIntent();

        if (intent != null) {
            PatientResponse patient = intent.getParcelableExtra("patient");
            progressBar.setVisibility(View.VISIBLE);
            patientProfileViewModel.getPatientById(patient.patientID).observe(this, new Observer<LoginResponse>() {
                @Override
                public void onChanged(LoginResponse loginResponse) {
                    progressBar.setVisibility(View.GONE);
                    txtFirstname.setText(loginResponse.patient.get(0).patientName);
                    txtLastname.setText(loginResponse.patient.get(0).patientSurname);
                    txtEmail.setText(loginResponse.patient.get(0).email);
                    txtCellphoneNumber.setText(loginResponse.patient.get(0).cellphoneNumber);
                    txtProvince.setText(loginResponse.patient.get(0).province);
                    txtTown.setText(loginResponse.patient.get(0).town);
                    txtPostalCode.setText(loginResponse.patient.get(0).postalCode);
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), EditPatientProfileActivity.class);
                    intent.putExtra("patient", patient);
                    startActivity(intent);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDeleteDialog(patient.patientID);
                }
            });
        }
    }

    private void showDeleteDialog(String patientId) {
        new AlertDialog.Builder(this)
                .setTitle("Delete profile")
                .setMessage("Are you sure you want to delete this profile")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);
                        patientProfileViewModel.deletePatient(patientId).observe(ProfileManagementActivity.this, new Observer<ServiceResponse>() {
                            @Override
                            public void onChanged(ServiceResponse serviceResponse) {
                                progressBar.setVisibility(View.GONE);
                                if (serviceResponse.status.equals("1")) {
                                    showSuccessDeletePatientDialog();
                                } else {
                                    Snackbar.make(findViewById(R.id.txtDisplayName), serviceResponse.message, Snackbar.LENGTH_LONG).show();
                                }
                            }
                        });
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showSuccessDeletePatientDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Delete profile")
                .setMessage("Profile has been successfully deleted")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}