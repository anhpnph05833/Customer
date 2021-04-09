package com.example.polycustomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class AddProfileActivity extends AppCompatActivity {

    private TextInputLayout regName, regNumber, regClass, regAddress;
    private Button btnsaveInfor;

    FirebaseAuth auth;
    DatabaseReference reference;


      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        anhXa();

        auth = FirebaseAuth.getInstance();

        btnsaveInfor.setOnClickListener(v -> {
            String nameUser = regName.getEditText().getText().toString();
            String numberUser = regNumber.getEditText().getText().toString();
            String addressUser = regAddress.getEditText().getText().toString();
            String classUser = regClass.getEditText().getText().toString();

            if (TextUtils.isEmpty(nameUser)||TextUtils.isEmpty(numberUser)
            ||TextUtils.isEmpty(addressUser) || TextUtils.isEmpty(classUser)){
                Toast.makeText(this, "Moi ban nhap day du thong tin", Toast.LENGTH_SHORT).show();
            }else {
                register(nameUser,numberUser,addressUser,classUser);
            }
        });
    }

    private void register(String nameUser, String numberUser, String addressUser, String classUer ){
        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userID = firebaseUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userID);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", userID);
        hashMap.put("nameUser", nameUser);
        hashMap.put("numberUser" , numberUser);
        hashMap.put("addressUser", addressUser);
        hashMap.put("classUser", classUer);
        hashMap.put("imageURl", "default");

        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(AddProfileActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(AddProfileActivity.this, "Vui long thu lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhXa(){
        btnsaveInfor = findViewById(R.id.btnsaveInfor);
        regName = findViewById(R.id.reg_name);
        regNumber = findViewById(R.id.reg_number);
        regClass = findViewById(R.id.reg_class);
        regAddress = findViewById(R.id.reg_address);
    }
}