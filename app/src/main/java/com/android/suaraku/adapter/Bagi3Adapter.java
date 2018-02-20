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

public class Bagi3Adapter extends RecyclerView.Adapter<Bagi3Adapter.MyViewHolder> {
    private List<Rank> bagi3List;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public Bagi3Adapter(List<Rank> bagi3List, Context context, FirebaseFirestore firestoreDB) {
        this.bagi3List = bagi3List;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView no,nama,jumlahbagi3;

        public MyViewHolder(View view) {
            super(view);
            no = (TextView) view.findViewById(R.id.no_rank_3);
            nama = (TextView) view.findViewById(R.id.parpol_rank_3);
            jumlahbagi3 = (TextView) view.findViewById(R.id.total_suara_rank_3);

        }
    }

    public Bagi3Adapter(List<Rank> bagi3List) {
        this.bagi3List = bagi3List;
    }

    @Override
    public Bagi3Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_bagi3, parent, false);

        return new Bagi3Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Bagi3Adapter.MyViewHolder holder, int position) {
//        final int itemPosition = position;
        Rank bagi3 = bagi3List.get(position);

        holder.no.setText(String.valueOf(bagi3.getNo_partai()));
        holder.nama.setText(bagi3.getNama_partai());
        holder.jumlahbagi3.setText(String.valueOf(bagi3.getJumlah_bagi3()));
    }

    @Override
    public int getItemCount() {
        return bagi3List.size();
    }

}
