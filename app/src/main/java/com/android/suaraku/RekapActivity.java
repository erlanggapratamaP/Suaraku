package com.android.suaraku;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.suaraku.Data.Rank;
import com.android.suaraku.adapter.Bagi1Adapter;
import com.android.suaraku.adapter.RekapAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RekapActivity extends AppCompatActivity {
    private static final String TAG = "RekapActivity";
    RecyclerView recyclerViewRekap;
    private RekapAdapter mAdapterRekap;
    private FirebaseFirestore firestoreDB;
    private ProgressDialog mProgress;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap);
        recyclerViewRekap = findViewById(R.id.rekap_list);
        mProgress = new ProgressDialog(this);
        firestoreDB = FirebaseFirestore.getInstance();
        getSeat();
    }
    public void getSeat(){
        mProgress.setMessage("Loading Data");
        mProgress.show();
        CollectionReference seatRef = firestoreDB.collection("suara").document("rekap").collection("rekapKursi");
        seatRef.orderBy("seat", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<Rank> seatList = new ArrayList<>();
                            for (DocumentSnapshot doc : task.getResult()){
                                Rank seat = doc.toObject(Rank.class);
                                seat.setId_partai(doc.getId());
                                seatList.add(seat);
                            }
                            mAdapterRekap = new RekapAdapter(seatList, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerViewRekap.setLayoutManager(mLayoutManager);
                            recyclerViewRekap.setAdapter(mAdapterRekap);
                            mProgress.dismiss();
                        }else {
                            Log.d(TAG, "Error getting documents: ", task.getException());

                    }
                    }
                });

    }
}
