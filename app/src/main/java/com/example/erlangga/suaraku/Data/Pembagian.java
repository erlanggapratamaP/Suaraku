package com.example.erlangga.suaraku.Data;

/**
 * Created by Erlangga on 23/01/2018.
 */

public class Pembagian {




    String id;
    String nama_partai;
    Integer jumlah_bagi1;
    Integer jumlah_bagi3;
    Integer jumlah_bagi5;
    Integer jumlah_bagi7;

    public Pembagian(){

    }
    public Pembagian(String id, String nama_partai, Integer jumlah_bagi1, Integer jumlah_bagi3, Integer jumlah_bagi5, Integer jumlah_bagi7){
        this.id = id;
        this.nama_partai = nama_partai;
        this.jumlah_bagi1 = jumlah_bagi1;
        this.jumlah_bagi3 = jumlah_bagi3;
        this.jumlah_bagi5 = jumlah_bagi5;
        this.jumlah_bagi7 = jumlah_bagi7;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getNama_partai() {
        return nama_partai;
    }

    public void setNama_partai(String nama_partai) {
        this.nama_partai = nama_partai;
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



}
