package com.example.erlangga.suaraku.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erlangga on 23/01/2018.
 */

public class Partai {

    String name_partai;

    Integer jumlah_suara;

    public Partai(String jumlah_suara){

    }

    public Partai(String name_partai, Integer jumlah_suara){
        this.name_partai = name_partai;
        this.jumlah_suara = jumlah_suara;
    }

    public String getName_partai() {
        return name_partai;
    }

    public void setName_partai(String name_partai) {
        this.name_partai = name_partai;
    }

    public Integer getJumlah_suara() {
        return jumlah_suara;
    }

    public void setJumlah_suara(Integer jumlah_suara) {
        this.jumlah_suara = jumlah_suara;
    }


    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("jumlah_suara", this.jumlah_suara);
        return result;
    }
}
