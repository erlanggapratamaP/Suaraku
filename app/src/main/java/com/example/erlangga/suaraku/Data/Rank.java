package com.example.erlangga.suaraku.Data;

/**
 * Created by Erlangga on 25/01/2018.
 */

public class Rank {


    Integer id_partai;
    String nama_partai;
    Integer jumlah_suara;


   public  Rank(){

    }
    public  Rank(Integer id_partai, String nama_partai, Integer jumlah_suara){
        this.id_partai = id_partai;
        this.nama_partai = nama_partai;
        this.jumlah_suara = jumlah_suara;
    }
    public Integer getId_partai() {
        return id_partai;
    }

    public void setId_partai(Integer id_partai) {
        this.id_partai = id_partai;
    }

    public String getNama_partai() {
        return nama_partai;
    }

    public void setNama_partai(String nama_partai) {
        this.nama_partai = nama_partai;
    }

    public Integer getJumlah_suara() {
        return jumlah_suara;
    }

    public void setJumlah_suara(Integer jumlah_suara) {
        this.jumlah_suara = jumlah_suara;
    }

}
