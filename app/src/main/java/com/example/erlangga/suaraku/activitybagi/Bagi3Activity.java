package com.example.erlangga.suaraku.activitybagi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.erlangga.suaraku.Data.Rank;
import com.example.erlangga.suaraku.R;
import com.example.erlangga.suaraku.adapter.Bagi1Adapter;
import com.example.erlangga.suaraku.adapter.Bagi3Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Bagi3Activity extends AppCompatActivity {
    private static final String TAG = "Bagi3Activity";

    private RecyclerView recyclerViewBagi3;

    private Bagi3Adapter mAdapterBagi3;
    private FirebaseFirestore firestoreDB;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagi3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewBagi3 = findViewById(R.id.bagi_3);
        firestoreDB = FirebaseFirestore.getInstance();
        loadBagi3List();
    }
    private void loadBagi3List() {
        CollectionReference bagi3Ref = firestoreDB.collection("suara");
        bagi3Ref.orderBy("jumlah_bagi3", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Rank> bagi3List = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                Rank bagi3 = doc.toObject(Rank.class);
                                bagi3.setId_partai(doc.getId());
                                bagi3List.add(bagi3);
                            }

                            mAdapterBagi3 = new Bagi3Adapter(bagi3List, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                            recyclerViewBagi3.setLayoutManager(mLayoutManager);
                            recyclerViewBagi3.setAdapter(mAdapterBagi3);
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
