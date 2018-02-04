package com.example.erlangga.suaraku;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.erlangga.suaraku.Data.Rank;
//import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
//import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.example.erlangga.suaraku.activitybagi.Bagi1Activity;
import com.example.erlangga.suaraku.activitybagi.Bagi3Activity;
import com.example.erlangga.suaraku.activitybagi.Bagi5Activity;
import com.example.erlangga.suaraku.activitybagi.Bagi7Activity;
import com.example.erlangga.suaraku.adapter.Bagi1Adapter;
import com.example.erlangga.suaraku.adapter.RankAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class HasilActivity extends AppCompatActivity {

    private static final String TAG = "HasilActivity";

    private RecyclerView recyclerViewRank;
    private RankAdapter mAdapterRank;
    private Button mButtonBagi1;
    private Button mButtonBagi3;
    private Button mButtonBagi5;
    private Button mButtonBagi7;
    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        recyclerViewRank = findViewById(R.id.rank_list);
 //       recyclerViewBagi1 = findViewById(R.id.bagi_1);
//        recyclerViewBagi3 = findViewById(R.id.bagi_3);
//        recyclerViewBagi5 = findViewById(R.id.bagi_5);
//        recyclerViewBagi7 = findViewById(R.id.bagi_7);

        //button
        mButtonBagi1 = findViewById(R.id.btnBagi1);
        mButtonBagi3 = findViewById(R.id.btnBagi3);
        mButtonBagi5 = findViewById(R.id.btnBagi5);
        mButtonBagi7 = findViewById(R.id.btnBagi7);
        firestoreDB = FirebaseFirestore.getInstance();

       loadRanksList();
 //      loadBagi1List();
//       loadBagi3List();
//       loadBagi5List();
//       loadBagi7List();

//        firestoreListener = firestoreDB.collection("suara")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
//                        if (e != null) {
//                            Log.e(TAG, "Listen failed!", e);
//                            return;
//                        }
//
//                        List<Rank> rankList = new ArrayList<>();
//
//                        for (DocumentSnapshot doc : documentSnapshots) {
//                            Rank rank = doc.toObject(Rank.class);
//                            rank.setId_partai(doc.getId());
//                            rankList.add(rank);
//                        }
//
//                        mAdapterRank = new RankAdapter(rankList, getApplicationContext(), firestoreDB);
//                        recyclerViewRank.setAdapter(mAdapterRank);
//                    }
//                });
//        init();
        mButtonBagi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilActivity.this, Bagi1Activity.class);
                startActivityForResult(intent, 0);
            }
        });
        mButtonBagi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilActivity.this, Bagi3Activity.class);
                startActivityForResult(intent, 0);
            }
        });
        mButtonBagi5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilActivity.this, Bagi5Activity.class);
                startActivityForResult(intent, 0);
            }
        });
        mButtonBagi7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilActivity.this, Bagi7Activity.class);
                startActivityForResult(intent, 0);
            }
        });
}

//    private void init(){
//        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewRank.setLayoutManager(linearLayoutManager);
//        firestoreDB = FirebaseFirestore.getInstance();
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        firestoreListener.remove();
//    }

    private void loadRanksList() {
        CollectionReference rankRef = firestoreDB.collection("suara");

        rankRef.orderBy("jumlah_suara", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Rank> rankList = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                Rank rank = doc.toObject(Rank.class);
                                rank.setId_partai(doc.getId());
                                rankList.add(rank);
                            }

                            mAdapterRank = new RankAdapter(rankList, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                            recyclerViewRank.setLayoutManager(mLayoutManager);
                            recyclerViewRank.setAdapter(mAdapterRank);
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
