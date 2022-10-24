package com.example.patient_online_wallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.patient_online_wallet.model.MedicalRecord;
import com.example.patient_online_wallet.model.MedicalRecordResponse;
import com.example.patient_online_wallet.model.PatientResponse;
import com.example.patient_online_wallet.ui.LoginViewModel;
import com.example.patient_online_wallet.ui.MedicalRecordViewModel;
import com.example.patient_online_wallet.ui.MedicalRecordsAdapter;

import java.util.Objects;

public class MedicalRecordActivity extends AppCompatActivity {
    private MedicalRecordViewModel medicalRecordViewModel;
    private MedicalRecordsAdapter adapter;
    private MedicalRecordsAdapter.OnClickListener clickListener;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);
        RecyclerView recyclerView = findViewById(R.id.rv_medicalRecords);
        progressBar = findViewById(R.id.simpleProgressBar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Medical Records");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Intent intent = getIntent();

        if (intent != null) {
            String name = intent.getStringExtra("name");
            String surname = intent.getStringExtra("surname");
            String email = intent.getStringExtra("email");
            String patientId = intent.getStringExtra("patientId");

            PatientResponse patient = intent.getParcelableExtra("patient");

            medicalRecordViewModel = new ViewModelProvider(this).get(MedicalRecordViewModel.class);
            clickListener = new MedicalRecordsAdapter.OnClickListener() {
                @Override
                public void onItemClick(MedicalRecord medicalRecord) {
                    Log.d("MED", medicalRecord.description);

                    Intent intent = new Intent(getApplicationContext(), MedicalRecordDetailsActivity.class);
                    intent.putExtra("title", medicalRecord.recordTitle);
                    intent.putExtra("description", medicalRecord.description);
                    intent.putExtra("date", medicalRecord.date);
                    intent.putExtra("sugarLevel", medicalRecord.sugarLevel);
                    intent.putExtra("bloodPressure", medicalRecord.bloodPressure);
                    intent.putExtra("additionalInfo", medicalRecord.additionalInformation);
                    intent.putExtra("prescription", medicalRecord.medicationPrescribed);
                    startActivity(intent);
                }
            };

            progressBar.setVisibility(View.VISIBLE);
            medicalRecordViewModel.getMedicalRecords(patientId).observe(this, new Observer<MedicalRecordResponse>() {
                @Override
                public void onChanged(MedicalRecordResponse medicalRecordResponse) {
                    progressBar.setVisibility(View.GONE);
                    Log.d("MATAZ", medicalRecordResponse.status);

                    adapter = new MedicalRecordsAdapter(getApplicationContext(), medicalRecordResponse.records, clickListener);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);
                }
            });


            addMenuProvider(new MenuProvider() {
                @Override
                public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                    menuInflater.inflate(R.menu.profile_menu_items, menu);
                }

                @Override
                public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.settings) {
                        //fetchAndShowPointsOfInterests(menuItem.toString());
                    } else if (menuItem.getItemId() == R.id.profile) {
                        Intent intent = new Intent(getApplicationContext(), ProfileManagementActivity.class);
                        intent.putExtra("patient", patient);
                        startActivity(intent);
                    }
                    return false;
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