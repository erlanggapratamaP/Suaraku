package com.android.suaraku.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Erlangga on 23/01/2018.
 */

public class Dapil implements Serializable {

   // public static final String EXTRA = "package com.android.suaraku.suaraku.android.suaraku.DAPIL_EXTRA";
    private String name_dapil;



    private HashMap<String, Integer> jumlah_kursi;


    public Dapil(){

    }

    public Dapil(String name_dapil){
        this.name_dapil = name_dapil;

    }

    public String getName_dapil() {
        return name_dapil;
    }
    public void setName_dapil(String name_dapil) {
        this.name_dapil = name_dapil;
    }

    public HashMap<String, Integer> getJumlah_kursi() {
        return jumlah_kursi;
    }

    public void setJumlah_kursi(HashMap<String, Integer> jumlah_kursi) {
        this.jumlah_kursi = jumlah_kursi;
    }




}
