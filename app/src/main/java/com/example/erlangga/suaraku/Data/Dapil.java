package com.example.erlangga.suaraku.Data;

/**
 * Created by Erlangga on 23/01/2018.
 */

public class Dapil {


    String name_dapil;
    String jumlah_kursi;


    public Dapil(){

    }

    public  Dapil(String name_dapil, String jumlah_kursi){
        this.name_dapil = name_dapil;
        this.jumlah_kursi = jumlah_kursi;
    }

    public String getName_dapil() {
        return name_dapil;
    }

    public void setName_dapil(String name_dapil) {
        this.name_dapil = name_dapil;
    }

    public String getJumlah_kursi() {
        return jumlah_kursi;
    }

    public void setJumlah_kursi(String jumlah_kursi) {
        this.jumlah_kursi = jumlah_kursi;
    }


}
