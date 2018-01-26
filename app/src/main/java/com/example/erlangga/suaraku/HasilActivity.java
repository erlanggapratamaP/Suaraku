package com.example.erlangga.suaraku;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erlangga.suaraku.Data.Rank;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HasilActivity extends AppCompatActivity {

    @BindView(R.id.rank_list)
    RecyclerView rankList;
    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        ButterKnife.bind(this);
        init();
        getRankList();
    }

    private void init(){
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rankList.setLayoutManager(linearLayoutManager);
        db = FirebaseFirestore.getInstance();
    }

    private void getRankList(){
        Query query = db.collection("perhitungansuara");

        FirestoreRecyclerOptions<Rank> response = new FirestoreRecyclerOptions.Builder<Rank>()
                .setQuery(query, Rank.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Rank, RankHolder>(response) {
            @Override
            public void onBindViewHolder(RankHolder holder, int position, Rank model) {

                holder.textID.setText(model.getId_partai());
                holder.textNamaPartai.setText(model.getNama_partai());
                holder.textJumlahSuara.setText(model.getJumlah_suara());

                holder.itemView.setOnClickListener(v -> {
                    Snackbar.make(rankList, model.getId_partai()+", "+model.getNama_partai()+" at "+model.getJumlah_suara(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                });
            }

            @Override
            public RankHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.list_item, group, false);

                return new RankHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };

        adapter.notifyDataSetChanged();
        rankList.setAdapter(adapter);
    }

    public class RankHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.no_rank)
        TextView textID;
        @BindView(R.id.parpol_rank)
        TextView textNamaPartai;
        @BindView(R.id.total_suara_rank)
        TextView textJumlahSuara;

        public RankHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
