package com.android.suaraku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.suaraku.Data.Rank;

import com.android.suaraku.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * Created by Erlangga on 04/02/2018.
 */

public class Bagi7Adapter extends  RecyclerView.Adapter<Bagi7Adapter.MyViewHolder>{
    private List<Rank> bagi7List;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public Bagi7Adapter(List<Rank> bagi7List, Context context, FirebaseFirestore firestoreDB) {
        this.bagi7List = bagi7List;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView no,nama,jumlahbagi7;

        public MyViewHolder(View view) {
            super(view);
            no = (TextView) view.findViewById(R.id.no_rank_7);
            nama = (TextView) view.findViewById(R.id.parpol_rank_7);
            jumlahbagi7 = (TextView) view.findViewById(R.id.total_suara_rank_7);

        }
    }

    public Bagi7Adapter(List<Rank> bagi7List) {
        this.bagi7List = bagi7List;
    }

    @Override
    public Bagi7Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_bagi7, parent, false);

        return new Bagi7Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Bagi7Adapter.MyViewHolder holder, int position) {
//        final int itemPosition = position;
        Rank bagi7 = bagi7List.get(position);

        holder.no.setText(String.valueOf(bagi7.getNo_partai()));
        holder.nama.setText(bagi7.getNama_partai());
        holder.jumlahbagi7.setText(String.valueOf(bagi7.getJumlah_bagi7()));
    }

    @Override
    public int getItemCount() {
        return bagi7List.size();
    }
}
