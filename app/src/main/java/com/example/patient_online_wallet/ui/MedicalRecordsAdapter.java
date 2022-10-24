package com.example.patient_online_wallet.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patient_online_wallet.R;
import com.example.patient_online_wallet.model.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsAdapter extends RecyclerView.Adapter<MedicalRecordsAdapter.ViewHolder> {
    private final Context context;
    private final List<MedicalRecord> medicalRecordArrayList;
    private OnClickListener clickListener;

    public MedicalRecordsAdapter(Context context, List<MedicalRecord> medicalRecordArrayList, OnClickListener clickListener) {
        this.context = context;
        this.medicalRecordArrayList = medicalRecordArrayList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MedicalRecordsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_record_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordsAdapter.ViewHolder holder, int position) {
        MedicalRecord model = medicalRecordArrayList.get(position);
        holder.txtMedicalRecordTitle.setText(model.recordTitle);
        holder.txtMedicalRecordDate.setText(model.date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return medicalRecordArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgMoreDetailsButton;
        private final TextView txtMedicalRecordTitle;
        private final TextView txtMedicalRecordDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMoreDetailsButton = itemView.findViewById(R.id.imgMoreDetailsButton);
            txtMedicalRecordTitle = itemView.findViewById(R.id.txtMedicalRecordTitle);
            txtMedicalRecordDate = itemView.findViewById(R.id.txtMedicalRecordDate);
        }
    }

    public interface OnClickListener {
        void onItemClick(MedicalRecord medicalRecord);
    }


}
