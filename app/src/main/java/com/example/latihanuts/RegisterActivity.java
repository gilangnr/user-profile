package com.example.latihanuts;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private Button btnSubmit;
    private EditText editEmail, editPassword, editNama, editTanggal, editAlamat;
    private RadioButton rbLaki;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi UI
        initUI();

        // Konfigurasi DatePickerDialog
        configureDatePicker();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String nama = editNama.getText().toString();
                String tanggal = editTanggal.getText().toString();
                String alamat = editAlamat.getText().toString();
                String jenisKelamin = rbLaki.isChecked() ? "Laki-laki" : "Perempuan";

                if (nama.isEmpty() || email.isEmpty() || password.isEmpty() || tanggal.isEmpty() || alamat.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Semua data harus diisi", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);

                    intent.putExtra("Email", email);
                    intent.putExtra("Password", password);
                    intent.putExtra("Nama", nama);
                    intent.putExtra("Tanggal", tanggal);
                    intent.putExtra("Alamat", alamat);
                    intent.putExtra("Jenis Kelamin", jenisKelamin);

                    startActivity(intent);
                }
            }
        });
    }

    private void initUI() {
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editNama = findViewById(R.id.edit_name);
        editAlamat = findViewById(R.id.edit_alamat);
        editTanggal = findViewById(R.id.edit_tanggal);
        rbLaki = findViewById(R.id.rd_laki);
        btnSubmit = findViewById(R.id.btn_submit);
        editTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void configureDatePicker() {
        // Konfigurasi Tanggal
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                        editTanggal.setText(date);
                    }
                },
                year, month, dayOfMonth
        );
    }

    private void showDatePickerDialog() {
        datePickerDialog.show();
    }
}
