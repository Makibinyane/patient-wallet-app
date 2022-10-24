package com.example.patient_online_wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.patient_online_wallet.model.LoginResponse;
import com.example.patient_online_wallet.model.PatientResponse;
import com.example.patient_online_wallet.model.ServiceResponse;
import com.example.patient_online_wallet.ui.EditPatientViewModel;
import com.example.patient_online_wallet.ui.LoginViewModel;
import com.example.patient_online_wallet.ui.PatientProfileViewModel;
import com.google.android.material.snackbar.Snackbar;

public class EditPatientProfileActivity extends AppCompatActivity {
    private EditPatientViewModel editPatientViewModel;
    private PatientProfileViewModel patientProfileViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient_profile);

        editPatientViewModel = new ViewModelProvider(this).get(EditPatientViewModel.class);
        patientProfileViewModel = new ViewModelProvider(this).get(PatientProfileViewModel.class);

        EditText txtFirstname = findViewById(R.id.txtEditFirstname);
        EditText txtLastname = findViewById(R.id.txtEditLastname);

        EditText txtEmail = findViewById(R.id.txtEditEmail);
        EditText txtCellphoneNumber = findViewById(R.id.txtEditCellNumber);

        EditText txtProvince = findViewById(R.id.txtEditProvince);
        EditText txtTown = findViewById(R.id.txtEditTown);
        EditText txtPostalCode = findViewById(R.id.txtEditPostalCode);

        Button btnSave = findViewById(R.id.btnSave);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit Patient Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Intent intent = getIntent();

        if (intent != null) {
            PatientResponse patient = intent.getParcelableExtra("patient");

            patientProfileViewModel.getPatientById(patient.patientID).observe(this, new Observer<LoginResponse>() {
                @Override
                public void onChanged(LoginResponse loginResponse) {
                    txtFirstname.setText(loginResponse.patient.get(0).patientName);
                    txtLastname.setText(loginResponse.patient.get(0).patientSurname);
                    txtEmail.setText(loginResponse.patient.get(0).email);
                    txtCellphoneNumber.setText(loginResponse.patient.get(0).cellphoneNumber);
                    txtProvince.setText(loginResponse.patient.get(0).province);
                    txtTown.setText(loginResponse.patient.get(0).town);
                    txtPostalCode.setText(loginResponse.patient.get(0).postalCode);
                }
            });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editPatientViewModel.updatePatient(patient.patientID, txtProvince.getText().toString(),
                            txtTown.getText().toString(), txtPostalCode.getText().toString(), txtEmail.getText().toString(), txtCellphoneNumber.getText().toString()).observe(EditPatientProfileActivity.this, new Observer<ServiceResponse>() {
                        @Override
                        public void onChanged(ServiceResponse serviceResponse) {
                            Snackbar.make(txtProvince, serviceResponse.message, Snackbar.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), ProfileManagementActivity.class);
                            intent.putExtra("patient", patient);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}