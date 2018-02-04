package com.example.erlangga.suaraku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erlangga.suaraku.Data.Rank;
import com.example.erlangga.suaraku.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * Created by Erlangga on 04/02/2018.
 */

public class Bagi5Adapter extends RecyclerView.Adapter<Bagi5Adapter.MyViewHolder>{
    private List<Rank> bagi5List;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public Bagi5Adapter(List<Rank> bagi5List, Context context, FirebaseFirestore firestoreDB) {
        this.bagi5List = bagi5List;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama,jumlahbagi5;

        public MyViewHolder(View view) {
            super(view);
            nama = (TextView) view.findViewById(R.id.parpol_rank_5);
            jumlahbagi5 = (TextView) view.findViewById(R.id.total_suara_rank_5);

        }
    }

    public Bagi5Adapter(List<Rank> bagi5List) {
        this.bagi5List = bagi5List;
    }

    @Override
    public Bagi5Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_bagi5, parent, false);

        return new Bagi5Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Bagi5Adapter.MyViewHolder holder, int position) {
//        final int itemPosition = position;
        Rank bagi5 = bagi5List.get(position);

        // holder.noPartai.setText(rank.getNo_partai());
        holder.nama.setText(bagi5.getNama_partai());
        holder.jumlahbagi5.setText(String.valueOf(bagi5.getJumlah_bagi5()));
    }

    @Override
    public int getItemCount() {
        return bagi5List.size();
    }
}
