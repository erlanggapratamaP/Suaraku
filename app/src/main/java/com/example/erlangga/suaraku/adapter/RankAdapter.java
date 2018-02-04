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
 * Created by Erlangga on 01/02/2018.
 */

public class RankAdapter extends  RecyclerView.Adapter<RankAdapter.MyViewHolder>{

    private List<Rank> rankList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public RankAdapter(List<Rank> rankList, Context context, FirebaseFirestore firestoreDB) {
        this.rankList = rankList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama,jumlah;

        public MyViewHolder(View view) {
            super(view);
            nama = (TextView) view.findViewById(R.id.parpol_rank);
            jumlah = (TextView) view.findViewById(R.id.total_suara_rank);

        }
    }




    public RankAdapter(List<Rank> rankList) {
        this.rankList = rankList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RankAdapter.MyViewHolder holder, int position) {
        final int itemPosition = position;
        final Rank rank = rankList.get(itemPosition);

       // holder.noPartai.setText(rank.getNo_partai());
        holder.nama.setText(rank.getNama_partai());
         holder.jumlah.setText(String.valueOf(rank.getJumlah_suara()));
        //holder.jumlah.setText(String.valueOf(rank.getJumlah_bagi1()));

    }

    @Override
    public int getItemCount() {
        return rankList.size();
    }




//    @Override
//    public RankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hasil, parent, false);
//
//        return new RankAdapter.ViewHolder(view);
//    }
//
//
//    @Override
//    public void onBindViewHolder(RankAdapter.ViewHolder holder, int position) {
//        final int itemPosition = position;
//        final Rank rank = rankList.get(itemPosition);
//
//       // holder.noPartai.setText(rank.getNo_partai());
//        holder.namaPartai.setText(rank.getNama_partai());
//        holder.jumlahSuara.setText(rank.getJumlah_suara());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return rankList.size();
//    }
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        //TextView noPartai;
//        TextView namaPartai;
//        TextView jumlahSuara;
//
//
//        ViewHolder(View view) {
//            super(view);
//           // noPartai = view.findViewById(R.id.no_rank);
//            namaPartai = view.findViewById(R.id.parpol_rank);
//            jumlahSuara = view.findViewById(R.id.total_suara_rank);
//
//        }
//    }

//    private void updateNote(Rank rank) {
//        Intent intent = new Intent(context, HasilActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("UpdateNoteId", rank.getId());
//        intent.putExtra("UpdateNoteContent", rank.getJumlah_suara());
//        context.startActivity(intent);
//    }
}
