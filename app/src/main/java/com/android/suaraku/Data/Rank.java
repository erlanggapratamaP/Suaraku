package com.android.suaraku.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erlangga on 25/01/2018.
 */

public class Rank {

    String id;
    Integer no_partai;



    Integer jumlah_kursi;
    String nama_partai;
    Integer suara_rank;
    Integer jumlah_suara;
    Integer jumlah_bagi1;
    Integer jumlah_bagi3;
    Integer jumlah_bagi5;
    Integer jumlah_bagi7;


    Integer seat;


   public  Rank(){

    }
    public  Rank(String id,Integer no_partai, Integer jumlah_kursi, String nama_partai,Integer suara_rank, Integer  jumlah_suara, Integer jumlah_bagi1, Integer jumlah_bagi3, Integer jumlah_bagi5, Integer jumlah_bagi7, Integer seat){
       this.id = id;
        this.nama_partai = nama_partai;
        this.jumlah_suara = jumlah_suara;
    }
    public  Rank (Integer no_partai, Integer jumlah_kursi, String nama_partai, Integer suara_rank, Integer jumlah_suara, Integer jumlah_bagi1, Integer jumlah_bagi3, Integer jumlah_bagi5, Integer jumlah_bagi7, Integer seat){
        this.nama_partai = nama_partai;
        this.jumlah_suara = jumlah_suara;
    }

    public String getId() {
        return id;
    }

    public void setId_partai(String id) {
        this.id = id;
    }


    public Integer getNo_partai() {
        return no_partai;
    }

    public void setNo_partai(Integer no_partai) {
        this.no_partai = no_partai;
    }

    public Integer getJumlah_kursi() {
        return jumlah_kursi;
    }

    public void setJumlah_kursi(Integer jumlah_kursi) {
        this.jumlah_kursi = jumlah_kursi;
    }




    public String getNama_partai() {
        return nama_partai;
    }

    public void setNama_partai(String nama_partai) {
        this.nama_partai = nama_partai;
    }



    public Integer getSuara_rank() {
        return suara_rank;
    }

    public void setSuara_rank(Integer suara_rank) {
        this.suara_rank = suara_rank;
    }



    public Integer getJumlah_suara() {
        return jumlah_suara;
    }

    public void setJumlah_suara(Integer jumlah_suara) {
        this.jumlah_suara = jumlah_suara;
    }

    public Integer getJumlah_bagi1() {
        return jumlah_bagi1;
    }

    public void setJumlah_bagi1(Integer jumlah_bagi1) {
        this.jumlah_bagi1 = jumlah_bagi1;
    }

    public Integer getJumlah_bagi3() {
        return jumlah_bagi3;
    }

    public void setJumlah_bagi3(Integer jumlah_bagi3) {
        this.jumlah_bagi3 = jumlah_bagi3;
    }

    public Integer getJumlah_bagi5() {
        return jumlah_bagi5;
    }

    public void setJumlah_bagi5(Integer jumlah_bagi5) {
        this.jumlah_bagi5 = jumlah_bagi5;
    }

    public Integer getJumlah_bagi7() {
        return jumlah_bagi7;
    }

    public void setJumlah_bagi7(Integer jumlah_bagi7) {
        this.jumlah_bagi7 = jumlah_bagi7;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }


    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();

        result.put("no_partai", this.no_partai);
        result.put("suara_rank", this.suara_rank);
        result.put("jumlah_kursi", this.jumlah_kursi);
        result.put("jumlah_suara", this.jumlah_suara);
        result.put("jumlah_bagi1", this.jumlah_bagi1);
        result.put("jumlah_bagi3", this.jumlah_bagi3);
        result.put("jumlah_bagi5", this.jumlah_bagi5);
        result.put("jumlah_bagi7", this.jumlah_bagi7);
        result.put("seat", this.seat);



        return result;
    }

}
