package com.example.patient_online_wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class MedicalRecordDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record_details);

        TextView tvTitle = findViewById(R.id.txtMedicalRecordDetailsTitle);
        TextView tvDescription = findViewById(R.id.txtMedicalRecordDescription);
        TextView tvDate = findViewById(R.id.txtMedicalRecordDetailsDate);
        TextView tvBP = findViewById(R.id.txtMedicalRecordBloodPressure);
        TextView tvSugarLevel = findViewById(R.id.txtMedicalRecordSugarLevel);
        TextView tvPrescription = findViewById(R.id.txtMedicalRecordPrescription);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Medical Record Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Intent intent = getIntent();

        if (intent != null) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String date = intent.getStringExtra("date");
            String sugarLevel = intent.getStringExtra("sugarLevel");
            String bloodPressure = intent.getStringExtra("bloodPressure");
            String additionalInfo = intent.getStringExtra("additionalInfo");
            String prescription = intent.getStringExtra("prescription");

            tvTitle.setText(title);
            tvDescription.setText(description);
            tvDate.setText(date);
            tvBP.setText(bloodPressure);
            tvSugarLevel.setText(sugarLevel);
            tvPrescription.setText(prescription);
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