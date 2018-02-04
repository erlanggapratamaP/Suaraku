package com.example.erlangga.suaraku.activitybagi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.erlangga.suaraku.Data.Rank;
import com.example.erlangga.suaraku.R;
import com.example.erlangga.suaraku.adapter.Bagi3Adapter;
import com.example.erlangga.suaraku.adapter.Bagi5Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Bagi5Activity extends AppCompatActivity {
    private static final String TAG = "Bagi5Activity";

    private RecyclerView recyclerViewBagi5;

    private Bagi5Adapter mAdapterBagi5;
    private FirebaseFirestore firestoreDB;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagi5);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewBagi5 = findViewById(R.id.bagi_5);
        firestoreDB = FirebaseFirestore.getInstance();
        loadBagi5List();
    }
    private void loadBagi5List() {
        CollectionReference bagi5Ref = firestoreDB.collection("suara");
        bagi5Ref.orderBy("jumlah_bagi5", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Rank> bagi5List = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                Rank bagi5 = doc.toObject(Rank.class);
                                bagi5.setId_partai(doc.getId());
                                bagi5List.add(bagi5);
                            }

                            mAdapterBagi5 = new Bagi5Adapter(bagi5List, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                            recyclerViewBagi5.setLayoutManager(mLayoutManager);
                            recyclerViewBagi5.setAdapter(mAdapterBagi5);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
//
//        firestoreDB.collection("suara")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
//                        for(DocumentChange doc : documentSnapshots.getDocumentChanges()){
//                            doc.getDocument().toObject(Rank.class);
//                            //do something...
//                        }
//                    }
//                });
    }
}
