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
 * Created by Erlangga on 20/02/2018.
 */

public class RekapAdapter extends RecyclerView.Adapter<RekapAdapter.MyViewHolder> {
    private List<Rank> rekapList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public RekapAdapter(List<Rank> rekapList, Context context, FirebaseFirestore firestoreDB) {
        this.rekapList = rekapList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView no, nama, rekap;

        public MyViewHolder(View view) {
            super(view);
            no = (TextView) view.findViewById(R.id.rekap_no);
            nama = (TextView) view.findViewById(R.id.rekap_partai);
            rekap = (TextView) view.findViewById(R.id.rekap_kursi);

        }
    }
    public RekapAdapter(List<Rank> rekapList) {
        this.rekapList = rekapList;
    }

    @Override
    public RekapAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_rekap, parent, false);

        return new RekapAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RekapAdapter.MyViewHolder holder, int position) {
//        final int itemPosition = position;
        Rank bagi1 = rekapList.get(position);

        holder.no.setText(String.valueOf(bagi1.getNo_partai()));
        holder.nama.setText(bagi1.getNama_partai());
        holder.rekap.setText(String.valueOf(bagi1.getSeat()));
    }

    @Override
    public int getItemCount() {
        return rekapList.size();
    }
}
