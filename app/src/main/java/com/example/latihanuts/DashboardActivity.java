package com.example.latihanuts;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    private TextView txtNama, txtEmail, txtJenisKelamin, txtTanggal, txtAlamat;
    private LinearLayout layoutPassword, layoutEmail, layoutMaps, layoutShare;

    private void initIU(){
        txtNama = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email_user);
        txtJenisKelamin = findViewById(R.id.txt_jk_user);
        txtTanggal = findViewById(R.id.txt_tgl_user);
        txtAlamat = findViewById(R.id.txt_alamat_user);
        layoutPassword = findViewById(R.id.layout_password);
        layoutEmail = findViewById(R.id.layout_email);
        layoutMaps = findViewById(R.id.layout_maps);
        layoutShare = findViewById(R.id.layout_share);
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initIU();

        Intent intent = getIntent();
        if(intent != null) {
            String nama = intent.getStringExtra("Nama");
            String email = intent.getStringExtra("Email");
            String tanggal = intent.getStringExtra("Tanggal");
            String jenisKelamin = intent.getStringExtra("Jenis Kelamin");
            String alamat = intent.getStringExtra("Alamat");


            txtNama.setText(nama);
            txtEmail.setText(email);
            txtAlamat.setText(alamat);
            txtTanggal.setText(tanggal);
            txtJenisKelamin.setText(jenisKelamin);

        }

        layoutPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = intent.getStringExtra("Password");

                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                builder.setMessage("Password Anda: " + password)
                        .setTitle("Info Password")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        layoutEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = intent.getStringExtra("Email");

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + email));
                startActivity(emailIntent);
            }
        });

        layoutMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String alamatMaps = intent.getStringExtra("Alamat");

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(alamatMaps));

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        layoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = intent.getStringExtra("Nama");
                String email = intent.getStringExtra("Email");
                String tanggal = intent.getStringExtra("Tanggal");
                String jenisKelamin = intent.getStringExtra("Jenis Kelamin");
                String alamat = intent.getStringExtra("Alamat");


                String shareText = "Nama: " + nama + "\n" +
                        "Email: " + email + "\n" +
                        "Tanggal lahir: " + tanggal + "\n" +
                        "Jenis Kelamin: " + jenisKelamin + "\n" +
                        "Alamat: " + alamat;

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

                startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"));
            }
        });



    }
}