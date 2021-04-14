package com.example.polycustomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.polycustomer.Model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends AppCompatActivity {
    private TextView namePerson;
    private TextInputEditText nameUser, numberUser, classUser, addressUser;
    private DatabaseReference reference;
    private FirebaseUser fuser;
    private CircleImageView avatarUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        anhXa();
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                namePerson.setText(user.getNameUser());
                nameUser.setText(user.getNameUser());
                numberUser.setText(user.getNumberUser());
                classUser.setText(user.getClassUser());
                addressUser.setText(user.getAddressUser());

                if (user.getImageURl().equals("default")){
                    avatarUser.setImageResource(R.drawable.backgroundsaveinfo);
                }else {
                    Glide.with(getBaseContext()).load(user.getImageURl()).into(avatarUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void anhXa(){
        namePerson = findViewById(R.id.namePerson);
        nameUser = findViewById(R.id.userName);
        numberUser = findViewById(R.id.numberUser);
        classUser = findViewById(R.id.classUser);
        addressUser = findViewById(R.id.addressUser);
        avatarUser = findViewById(R.id.imgPerson);
    }
}