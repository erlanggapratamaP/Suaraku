package com.android.suaraku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.suaraku.Data.Rank;
//import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
//import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.android.suaraku.activitybagi.Bagi1Activity;
import com.android.suaraku.activitybagi.Bagi3Activity;
import com.android.suaraku.activitybagi.Bagi5Activity;
import com.android.suaraku.activitybagi.Bagi7Activity;
import com.android.suaraku.adapter.RankAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class HasilActivity extends AppCompatActivity {

    private static final String TAG = "HasilActivity";

    private RecyclerView recyclerViewRank;
    private RankAdapter mAdapterRank;
    private TextView mJumlahKursi;
    private Button mButtonBagi1;
    private Button mButtonBagi3;
    private Button mButtonBagi5;
    private Button mButtonBagi7;
    private Button mButtonSeat;
    private ProgressDialog mProgress;
    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;
    LinearLayoutManager linearLayoutManager;
    private int jumlah_kursi = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
//        Intent i = getIntent();
//        String dapil = null;



        recyclerViewRank = findViewById(R.id.rank_list);
 //       recyclerViewBagi1 = findViewById(R.id.bagi_1);
//        recyclerViewBagi3 = findViewById(R.id.bagi_3);
//        recyclerViewBagi5 = findViewById(R.id.bagi_5);
//        recyclerViewBagi7 = findViewById(R.id.bagi_7);

        //button
        mJumlahKursi = findViewById(R.id.jumlah_kursi);
        mButtonBagi1 = findViewById(R.id.btnBagi1);
        mButtonBagi3 = findViewById(R.id.btnBagi3);
        mButtonBagi5 = findViewById(R.id.btnBagi5);
        mButtonBagi7 = findViewById(R.id.btnBagi7);
        mButtonSeat = findViewById(R.id.btnSeat);

        mProgress = new ProgressDialog(this);
        firestoreDB = FirebaseFirestore.getInstance();
//        Dapil dapil = new Dapil();
//        String mDapil = dapil.getName_dapil();

        loadJumlahKursi();

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
        mButtonSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilActivity.this, RekapActivity.class);
                startActivityForResult(intent,0);
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

    public void loadJumlahKursi(){
        mProgress.setMessage("Loading Data");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String dapil = prefs.getString("string_id", "no id"); //no id: default value
        mProgress.show();
//        Dapil dapil = (Dapil) getIntent().getSerializableExtra(Dapil.EXTRA);
//          String dapil = "Jatim";
            DocumentReference kursiRef = firestoreDB.collection("dapil").document(dapil);
            kursiRef.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                DocumentSnapshot doc = task.getResult();
                                StringBuilder fields = new StringBuilder("");
                                fields.append("Jumlah Kursi: ").append(doc.get("jumlah_kursi"));
                                mJumlahKursi.setText(fields.toString());
                                int jmlKursi = doc.getLong("jumlah_kursi").intValue();
                                loadRanksList(jmlKursi);

                            } else {
                                Log.d(TAG, "Gagal mendapatkan data:  ", task.getException());
                            }

                        }
                    });


    }




    private void loadRanksList(int jmlKursi) {
            CollectionReference rankRef = firestoreDB.collection("pembagian");
            rankRef.orderBy("suara_rank", Query.Direction.DESCENDING).limit(jmlKursi)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                int seatPKB = 0;
                                int seatBerkarya =0;
                                List<Rank> rankList = new ArrayList<>();
                                for (DocumentSnapshot doc : task.getResult()) {
                                    Rank rank = doc.toObject(Rank.class);
                                    rank.setId_partai(doc.getId());
                                    rankList.add(rank);
                                    String partai = doc.getString("nama_partai");
                                    if(partai.equalsIgnoreCase("PKB")){
                                        seatPKB++;
                                    }else if (partai.equalsIgnoreCase("Berkarya")){
                                        seatBerkarya++;
                                    }
                                }

                                mAdapterRank = new RankAdapter(rankList, getApplicationContext(), firestoreDB);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                                recyclerViewRank.setLayoutManager(mLayoutManager);
                                recyclerViewRank.setAdapter(mAdapterRank);
                                mProgress.dismiss();
                                uploadSeat(seatPKB,seatBerkarya);
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
        //}
    }

    public void uploadSeat(int seatPKB, int seatBerkarya){
        WriteBatch batch = firestoreDB.batch();
        DocumentReference pkbRef = firestoreDB.collection("suara").document("rekap").collection("rekapKursi").document("seatPKB");
        batch.update(pkbRef,"seat", seatPKB);
        DocumentReference bkryaRef = firestoreDB.collection("suara").document("rekap").collection("rekapKursi").document("seatBerkarya");
        batch.update(bkryaRef,"seat", seatBerkarya);
        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Seat Berhasil ditambahkan!");
            }
        });
    }







}
