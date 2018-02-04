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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Bagi1Activity extends AppCompatActivity {

    private static final String TAG = "Bagi1Activity";

    private RecyclerView recyclerViewBagi1;

    private Bagi1Adapter mAdapterBagi1;
    private FirebaseFirestore firestoreDB;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagi1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewBagi1 = findViewById(R.id.bagi_1);
        firestoreDB = FirebaseFirestore.getInstance();
        loadBagi1List();

    }
    private void loadBagi1List() {
        CollectionReference bagi1Ref = firestoreDB.collection("suara");
        bagi1Ref.orderBy("jumlah_bagi1", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Rank> bagi1List = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                Rank bagi1 = doc.toObject(Rank.class);
                                bagi1.setId_partai(doc.getId());
                                bagi1List.add(bagi1);
                            }

                            mAdapterBagi1 = new Bagi1Adapter(bagi1List, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                            recyclerViewBagi1.setLayoutManager(mLayoutManager);
                            recyclerViewBagi1.setAdapter(mAdapterBagi1);


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
