package com.example.polycustomer.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.polycustomer.Adapter.UserAdapter;
import com.example.polycustomer.Model.Canteen;
import com.example.polycustomer.Model.Chat;
import com.example.polycustomer.Model.Chatlist;
import com.example.polycustomer.Model.User;
import com.example.polycustomer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ChatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<Canteen> mCanteen;

    FirebaseUser fuser;
    DatabaseReference reference;

    private List<Chatlist> canteenList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView = view.findViewById(R.id.recycler_viewChat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        canteenList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chatlist").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                canteenList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Chatlist chatlist = dataSnapshot.getValue(Chatlist.class);
                    canteenList.add(chatlist);
                }
                chatList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    private void chatList() {
        mCanteen = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Canteen");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mCanteen.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Canteen canteen = dataSnapshot.getValue(Canteen.class);
                        for (Chatlist chatlist : canteenList){
                            if (canteen.getId().equals(chatlist.getId())){
                                mCanteen.add(canteen);
                            }
                        }
                    }
                userAdapter = new UserAdapter(getContext(),mCanteen);
                recyclerView.setAdapter(userAdapter);
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}