package com.example.polycustomer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.polycustomer.Model.Canteen;
import com.example.polycustomer.Model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {


    CircleImageView profile_image;
    TextView username;


    FirebaseUser fuser;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userDbReference;
    
    EditText edt_text;
    ImageButton btn_send;
    Chat chat;
    String userid;

    Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        userid = intent.getStringExtra("id");
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        String token = intent.getStringExtra("token");

        profile_image = findViewById(R.id.profile_image_user);
        username = findViewById(R.id.username_user);
        btn_send = findViewById(R.id.btn_send_msg);
        edt_text = findViewById(R.id.edt_text_send);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_send.setOnClickListener(v -> {
            String msg = edt_text.getText().toString();

        });
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        userDbReference = firebaseDatabase.getReference("Canteen");
        Query query = userDbReference.orderByChild("id").equalTo(userid);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String name = "" + dataSnapshot.child("nameCanteen").getValue();
                    String image = "" + dataSnapshot.child("avatar").getValue();
                    username.setText(name);
                    try {
                        Picasso.get().load(image).placeholder(R.drawable.iconavatar).into(profile_image);
                    } catch (Exception e) {
                        Picasso.get().load(R.drawable.iconavatar).into(profile_image);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void sendMessage(){
        
    }

}