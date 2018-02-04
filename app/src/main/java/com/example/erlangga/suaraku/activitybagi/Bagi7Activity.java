package com.example.erlangga.suaraku.activitybagi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.erlangga.suaraku.Data.Rank;
import com.example.erlangga.suaraku.R;
import com.example.erlangga.suaraku.adapter.Bagi5Adapter;
import com.example.erlangga.suaraku.adapter.Bagi7Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Bagi7Activity extends AppCompatActivity {
    private static final String TAG = "Bagi7Activity";

    private RecyclerView recyclerViewBagi7;

    private Bagi7Adapter mAdapterBagi7;
    private FirebaseFirestore firestoreDB;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagi7);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewBagi7 = findViewById(R.id.bagi_7);
        firestoreDB = FirebaseFirestore.getInstance();
        loadBagi7List();
    }
    private void loadBagi7List() {
        CollectionReference bagi7Ref = firestoreDB.collection("suara");
        bagi7Ref.orderBy("jumlah_bagi7", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Rank> bagi7List = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                Rank bagi7 = doc.toObject(Rank.class);
                                bagi7.setId_partai(doc.getId());
                                bagi7List.add(bagi7);
                            }

                            mAdapterBagi7 = new Bagi7Adapter(bagi7List, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                            recyclerViewBagi7.setLayoutManager(mLayoutManager);
                            recyclerViewBagi7.setAdapter(mAdapterBagi7);
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
