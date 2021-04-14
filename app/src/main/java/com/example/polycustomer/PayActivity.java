package com.example.polycustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


public class PayActivity extends AppCompatActivity {
    private TextView txtBack, txtPriceOder, txtNameOder;
    private ImageView imgOder;
    private RadioButton mcheckTTNH;
    private Button btnConfigOder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        anhXa();

        txtBack.setOnClickListener(v -> {
            startActivity(new Intent(PayActivity.this, HomeActivity.class));
        });

    }



    private void anhXa(){
        txtBack = findViewById(R.id.txtBack);
        txtPriceOder = findViewById(R.id.txtPriceOder);
        txtNameOder = findViewById(R.id.txtNameOder);
        imgOder = findViewById(R.id.imgOder);
        mcheckTTNH = findViewById(R.id.chekcTTNH);
        btnConfigOder = findViewById(R.id.btnConfigOder);
    }
}