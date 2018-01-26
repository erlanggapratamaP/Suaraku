package com.example.erlangga.suaraku.Data;

/**
 * Created by Erlangga on 23/01/2018.
 */

public class Pembagian {

    Integer bagi1;
    Integer bagi3;
    Integer bagi5;
    Integer bagi7;

    public  Pembagian(){

    }
    public Pembagian(Integer bagi1, Integer bagi3, Integer bagi5, Integer bagi7){
        this.bagi1 =  bagi1;
        this.bagi3 = bagi3;
        this.bagi5 = bagi5;
        this.bagi7 = bagi7;
    }

    public Integer getBagi1() {
        return bagi1;
    }

    public void setBagi1(Integer bagi1) {
        this.bagi1 = bagi1;
    }

    public Integer getBagi3() {
        return bagi3;
    }

    public void setBagi3(Integer bagi3) {
        this.bagi3 = bagi3;
    }

    public Integer getBagi5() {
        return bagi5;
    }

    public void setBagi5(Integer bagi5) {
        this.bagi5 = bagi5;
    }

    public Integer getBagi7() {
        return bagi7;
    }

    public void setBagi7(Integer bagi7) {
        this.bagi7 = bagi7;
    }


}
