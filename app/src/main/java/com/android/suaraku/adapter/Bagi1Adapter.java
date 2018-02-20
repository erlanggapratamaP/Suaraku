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
 * Created by Erlangga on 02/02/2018.
 */

public class Bagi1Adapter extends RecyclerView.Adapter<Bagi1Adapter.MyViewHolder> {

    private List<Rank> bagi1List;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public Bagi1Adapter(List<Rank> bagi1List, Context context, FirebaseFirestore firestoreDB) {
        this.bagi1List = bagi1List;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView no,nama,jumlahbagi1;

        public MyViewHolder(View view) {
            super(view);
            no = (TextView) view.findViewById(R.id.no_rank_1);
            nama = (TextView) view.findViewById(R.id.parpol_rank_1);
            jumlahbagi1 = (TextView) view.findViewById(R.id.total_suara_rank_1);

        }
    }

    public Bagi1Adapter(List<Rank> bagi1List) {
        this.bagi1List = bagi1List;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_bagi1, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        final int itemPosition = position;
        Rank bagi1 = bagi1List.get(position);

        holder.no.setText(String.valueOf(bagi1.getNo_partai()));
        holder.nama.setText(bagi1.getNama_partai());
        holder.jumlahbagi1.setText(String.valueOf(bagi1.getJumlah_bagi1()));
    }

    @Override
    public int getItemCount() {
        return bagi1List.size();
    }

}
