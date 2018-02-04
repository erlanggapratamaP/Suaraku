package com.example.erlangga.suaraku;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erlangga.suaraku.Data.Partai;
import com.example.erlangga.suaraku.Data.Rank;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;

////import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    String[] dapilAuto = {"Jateng","Jatim","Jabar"};
    private static final String TAG = "ActivityMain";
    EditText textDapil;
    EditText textNasdem;
    EditText textPKB;
    EditText textPKS;
    EditText textPDIP;
    EditText textGolkar;
    EditText textGerinda;
    EditText textDemokrat;
    EditText textPAN;
    EditText textPPP;
    EditText textHanura;
    Button mSaveButton;
//    @BindView(R.id.nama_dapil)
//    AutoCompleteTextView namaDapil;
//    @BindView(R.id.partai_nasdem)
//    EditText partaiNasdem;
//    @BindView(R.id.partai_pkb)
//    EditText partaiPKB;
//    @BindView(R.id.partai_pks)
//    EditText partaiPKS;
//    @BindView(R.id.partai_pdip)
//    EditText partaiPDIP;
//    @BindView(R.id.partai_golkar)
//    EditText partaiGolkar;
//    @BindView(R.id.partai_gerinda)
//    EditText partaiGerinda;
//    @BindView(R.id.partai_demokrat)
//    EditText partaiDemokrat;
//    @BindView(R.id.partai_pan)
//    EditText partaiPAN;
//    @BindView(R.id.partai_ppp)
//    EditText partaiPPP;
//    @BindView(R.id.partai_hanura)
//    EditText partaiHanura;
//    @BindView(R.id.save_button)
    Button saveButton;
    private static final String ID_PARTAI = "id_partai";
    private static final String NAMA_PARTAI = "nama_partai";
    private static final String JUMLAH_SUARA = "jumlah_suara";
    private FirebaseFirestore db;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();

        textDapil = findViewById(R.id.nama_dapil);
        textNasdem = findViewById(R.id.partai_nasdem);
        textPKB = findViewById(R.id.partai_pkb);
        textPKS = findViewById(R.id.partai_pks);
        textPDIP = findViewById(R.id.partai_pdip);
        textGolkar = findViewById(R.id.partai_golkar);
        textGerinda = findViewById(R.id.partai_gerinda);
        textDemokrat = findViewById(R.id.partai_demokrat);
        textPAN = findViewById(R.id.partai_pan);
        textPPP = findViewById(R.id.partai_ppp);
        textHanura = findViewById(R.id.partai_hanura);
        mSaveButton = findViewById(R.id.save_button);

        db = FirebaseFirestore.getInstance();

        //autocomplete
        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,dapilAuto);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.nama_dapil);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String jumlah_suara1 = textNasdem.getText().toString();
               String jumlah_suara2 = textPKB.getText().toString();
               String jumlah_suara3 = textPKS.getText().toString();
               String jumlah_suara4 = textPDIP.getText().toString();
               String jumlah_suara5 = textGolkar.getText().toString();
               String jumlah_suara6 = textGerinda.getText().toString();
               String jumlah_suara7 = textDemokrat.getText().toString();
               String jumlah_suara8 = textPAN.getText().toString();
               String jumlah_suara9 = textPPP.getText().toString();
               String jumlah_suara10 = textHanura.getText().toString();

                int jmlSuara1 = Integer.parseInt(jumlah_suara1);
                int jmlSuara2 = Integer.parseInt(jumlah_suara2);
                int jmlSuara3 = Integer.parseInt(jumlah_suara3);
                int jmlSuara4 = Integer.parseInt(jumlah_suara4);
                int jmlSuara5 = Integer.parseInt(jumlah_suara5);
                int jmlSuara6 = Integer.parseInt(jumlah_suara6);
                int jmlSuara7 = Integer.parseInt(jumlah_suara7);
                int jmlSuara8 = Integer.parseInt(jumlah_suara8);
                int jmlSuara9 = Integer.parseInt(jumlah_suara9);
                int jmlSuara10 = Integer.parseInt(jumlah_suara10);

                //bagi1
                int jmlSuaraBagi11 = jmlSuara1/1;
                //String jmlSuaraBagi11str = String.valueOf(jmlSuaraBagi11);
                int jmlSuaraBagi12 = jmlSuara2/1;
               // String jmlSuaraBagi12str = String.valueOf(jmlSuaraBagi12);
                int jmlSuaraBagi13 = jmlSuara3/1;
               // String jmlSuaraBagi13str = String.valueOf(jmlSuaraBagi13);
                int jmlSuaraBagi14 = jmlSuara4/1;
               // String jmlSuaraBagi14str = String.valueOf(jmlSuaraBagi14);
                int jmlSuaraBagi15 = jmlSuara5/1;
               // String jmlSuaraBagi15str = String.valueOf(jmlSuaraBagi15);
                int jmlSuaraBagi16 = jmlSuara6/1;
                //String jmlSuaraBagi16str = String.valueOf(jmlSuaraBagi16);
                int jmlSuaraBagi17 = jmlSuara7/1;
                //String jmlSuaraBagi17str = String.valueOf(jmlSuaraBagi17);
                int jmlSuaraBagi18 = jmlSuara8/1;
                //String jmlSuaraBagi18str = String.valueOf(jmlSuaraBagi18);
                int jmlSuaraBagi19 = jmlSuara9/1;
                //String jmlSuaraBagi19str = String.valueOf(jmlSuaraBagi19);
                int jmlSuaraBagi110 = jmlSuara10/1;
                //String jmlSuaraBagi110str = String.valueOf(jmlSuaraBagi110);

                //bagi3
                int jmlSuaraBagi31 = jmlSuara1/3;
               // String jmlSuaraBagi31str = String.valueOf(jmlSuaraBagi31);
                int jmlSuaraBagi32 = jmlSuara2/3;
               // String jmlSuaraBagi32str = String.valueOf(jmlSuaraBagi32);
                int jmlSuaraBagi33 = jmlSuara3/3;
              // String jmlSuaraBagi33str = String.valueOf(jmlSuaraBagi33);
                int jmlSuaraBagi34 = jmlSuara4/3;
               // String jmlSuaraBagi34str = String.valueOf(jmlSuaraBagi34);
                int jmlSuaraBagi35 = jmlSuara5/3;
               // String jmlSuaraBagi35str = String.valueOf(jmlSuaraBagi35);
                int jmlSuaraBagi36 = jmlSuara6/3;
               // String jmlSuaraBagi36str = String.valueOf(jmlSuaraBagi36);
                int jmlSuaraBagi37 = jmlSuara7/3;
               // String jmlSuaraBagi37str = String.valueOf(jmlSuaraBagi37);
                int jmlSuaraBagi38 = jmlSuara8/3;
                //String jmlSuaraBagi38str = String.valueOf(jmlSuaraBagi38);
                int jmlSuaraBagi39 = jmlSuara9/3;
               // String jmlSuaraBagi39str = String.valueOf(jmlSuaraBagi39);
                int jmlSuaraBagi310 = jmlSuara10/3;
                //String jmlSuaraBagi310str = String.valueOf(jmlSuaraBagi310);


                //bagi5
                int jmlSuaraBagi51 = jmlSuara1/5;
                //String jmlSuaraBagi51str = String.valueOf(jmlSuaraBagi51);
                int jmlSuaraBagi52 = jmlSuara2/5;
                //String jmlSuaraBagi52str = String.valueOf(jmlSuaraBagi52);
                int jmlSuaraBagi53 = jmlSuara3/5;
                //String jmlSuaraBagi53str = String.valueOf(jmlSuaraBagi53);
                int jmlSuaraBagi54 = jmlSuara4/5;
               // String jmlSuaraBagi54str = String.valueOf(jmlSuaraBagi54);
                int jmlSuaraBagi55 = jmlSuara5/5;
                //String jmlSuaraBagi55str = String.valueOf(jmlSuaraBagi55);
                int jmlSuaraBagi56 = jmlSuara6/5;
               // String jmlSuaraBagi56str = String.valueOf(jmlSuaraBagi56);
                int jmlSuaraBagi57 = jmlSuara7/5;
                //String jmlSuaraBagi57str = String.valueOf(jmlSuaraBagi57);
                int jmlSuaraBagi58 = jmlSuara8/5;
               // String jmlSuaraBagi58str = String.valueOf(jmlSuaraBagi58);
                int jmlSuaraBagi59 = jmlSuara9/5;
                //String jmlSuaraBagi59str = String.valueOf(jmlSuaraBagi59);
                int jmlSuaraBagi510 = jmlSuara10/5;
                //String jmlSuaraBagi510str = String.valueOf(jmlSuaraBagi510);

                //bagi7
                int jmlSuaraBagi71 = jmlSuara1/7;
                //String jmlSuaraBagi71str = String.valueOf(jmlSuaraBagi71);
                int jmlSuaraBagi72 = jmlSuara2/7;
                //String jmlSuaraBagi72str = String.valueOf(jmlSuaraBagi72);
                int jmlSuaraBagi73 = jmlSuara3/7;
               // String jmlSuaraBagi73str = String.valueOf(jmlSuaraBagi73);
                int jmlSuaraBagi74 = jmlSuara4/7;
               // String jmlSuaraBagi74str = String.valueOf(jmlSuaraBagi74);
                int jmlSuaraBagi75 = jmlSuara5/7;
               // String jmlSuaraBagi75str = String.valueOf(jmlSuaraBagi75);
                int jmlSuaraBagi76 = jmlSuara6/7;
               // String jmlSuaraBagi76str = String.valueOf(jmlSuaraBagi76);
                int jmlSuaraBagi77 = jmlSuara7/7;
                //String jmlSuaraBagi77str = String.valueOf(jmlSuaraBagi77);
                int jmlSuaraBagi78 = jmlSuara8/7;
               // String jmlSuaraBagi78str = String.valueOf(jmlSuaraBagi78);
                int jmlSuaraBagi79 = jmlSuara9/7;
               // String jmlSuaraBagi79str = String.valueOf(jmlSuaraBagi79);
                int jmlSuaraBagi710 = jmlSuara10/7;
                //String jmlSuaraBagi710str = String.valueOf(jmlSuaraBagi710);


                addPartai(jmlSuara1,jmlSuara2,jmlSuara3,jmlSuara4,jmlSuara5,jmlSuara6,jmlSuara7,jmlSuara8,jmlSuara9,jmlSuara10);
               addBagi1(jmlSuaraBagi11,jmlSuaraBagi12,jmlSuaraBagi13,jmlSuaraBagi14,jmlSuaraBagi15,jmlSuaraBagi16,jmlSuaraBagi17,jmlSuaraBagi18,jmlSuaraBagi19,jmlSuaraBagi110);
                addBagi3(jmlSuaraBagi31,jmlSuaraBagi32,jmlSuaraBagi33,jmlSuaraBagi34,jmlSuaraBagi35,jmlSuaraBagi36,jmlSuaraBagi37,jmlSuaraBagi38,jmlSuaraBagi39,jmlSuaraBagi310);
                addBagi5(jmlSuaraBagi51,jmlSuaraBagi52,jmlSuaraBagi53,jmlSuaraBagi54,jmlSuaraBagi55,jmlSuaraBagi56,jmlSuaraBagi57,jmlSuaraBagi58,jmlSuaraBagi59,jmlSuaraBagi510);
                addBagi7(jmlSuaraBagi71,jmlSuaraBagi72,jmlSuaraBagi73,jmlSuaraBagi74,jmlSuaraBagi75,jmlSuaraBagi76,jmlSuaraBagi77,jmlSuaraBagi78,jmlSuaraBagi79,jmlSuaraBagi710);
                Intent intent = new Intent(MainActivity.this, HasilActivity.class);
               MainActivity.this.startActivity(intent);
               finish();
            }
        });

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            id = bundle.getString("UpdateNoteId");
//
//            textNasdem.setText(bundle.getString("UpdatePartaiJumlah"));
//
////            partaiPKB.setText(bundle.getString("UpdateNoteContent"));
////            partaiPKS.setText(bundle.getString("UpdateNoteContent"));
////            partaiPDIP.setText(bundle.getString("UpdateNoteContent"));
////            partaiGolkar.setText(bundle.getString("UpdateNoteContent"));
////            partaiGerinda.setText(bundle.getString("UpdateNoteContent"));
////            partaiDemokrat.setText(bundle.getString("UpdateNoteContent"));
////            partaiPAN.setText(bundle.getString("UpdateNoteContent"));
////            partaiPPP.setText(bundle.getString("UpdateNoteContent"));
////            partaiHanura.setText(bundle.getString("UpdateNoteContent"));
//        }

    }

    private void addPartai(int jumlah_suara1, int jumlah_suara2, int jumlah_suara3, int jumlah_suara4, int jumlah_suara5, int jumlah_suara6, int jumlah_suara7, int jumlah_suara8, int jumlah_suara9, int jumlah_suara10) {
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference nsdmRef = db.collection("suara").document("pt_nasdem");
        batch.update(nsdmRef,"jumlah_suara", jumlah_suara1);
        DocumentReference pkbRef = db.collection("suara").document("pt_pkb");
        batch.update(pkbRef,"jumlah_suara", jumlah_suara2);
        DocumentReference pksRef = db.collection("suara").document("pt_pks");
        batch.update(pksRef,"jumlah_suara", jumlah_suara3);
        DocumentReference pdipRef = db.collection("suara").document("pt_pdip");
        batch.update(pdipRef,"jumlah_suara", jumlah_suara4);
        DocumentReference glkrRef = db.collection("suara").document("pt_golkar");
        batch.update(glkrRef,"jumlah_suara", jumlah_suara5);
        DocumentReference grndRef = db.collection("suara").document("pt_gerinda");
        batch.update(grndRef,"jumlah_suara", jumlah_suara6);
        DocumentReference dmkrtRef = db.collection("suara").document("pt_demokrat");
        batch.update(dmkrtRef,"jumlah_suara", jumlah_suara7);
        DocumentReference panRef = db.collection("suara").document("pt_pan");
        batch.update(panRef,"jumlah_suara", jumlah_suara8);
        DocumentReference pppRef = db.collection("suara").document("pt_ppp");
        batch.update(pppRef,"jumlah_suara", jumlah_suara9);
        DocumentReference hnraRef = db.collection("suara").document("pt_hanura");
        batch.update(hnraRef,"jumlah_suara", jumlah_suara10);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Berhasil!");
            }
        });


//        db.collection("partai").document("pt_nasdem" + "pt_pkb" + "pt_pks" + )
//                .set(rank)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "DocumentSnapshot successfully written!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error writing document", e);
//                    }
//                });
    }
    private void addBagi1(int jmlSuaraBagi11str, int jmlSuaraBagi12str, int jmlSuaraBagi13str, int jmlSuaraBagi14str, int jmlSuaraBagi15str, int jmlSuaraBagi16str, int jmlSuaraBagi17str, int jmlSuaraBagi18str, int jmlSuaraBagi19str, int jmlSuaraBagi110str){
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference nsdmRef = db.collection("suara").document("pt_nasdem");
        batch.update(nsdmRef,"jumlah_bagi1", jmlSuaraBagi11str);
        DocumentReference pkbRef = db.collection("suara").document("pt_pkb");
        batch.update(pkbRef,"jumlah_bagi1", jmlSuaraBagi12str);
        DocumentReference pksRef = db.collection("suara").document("pt_pks");
        batch.update(pksRef,"jumlah_bagi1", jmlSuaraBagi13str);
        DocumentReference pdipRef = db.collection("suara").document("pt_pdip");
        batch.update(pdipRef,"jumlah_bagi1", jmlSuaraBagi14str);
        DocumentReference glkrRef = db.collection("suara").document("pt_golkar");
        batch.update(glkrRef,"jumlah_bagi1", jmlSuaraBagi15str);
        DocumentReference grndRef = db.collection("suara").document("pt_gerinda");
        batch.update(grndRef,"jumlah_bagi1", jmlSuaraBagi16str);
        DocumentReference dmkrtRef = db.collection("suara").document("pt_demokrat");
        batch.update(dmkrtRef,"jumlah_bagi1", jmlSuaraBagi17str);
        DocumentReference panRef = db.collection("suara").document("pt_pan");
        batch.update(panRef,"jumlah_bagi1", jmlSuaraBagi18str);
        DocumentReference pppRef = db.collection("suara").document("pt_ppp");
        batch.update(pppRef,"jumlah_bagi1", jmlSuaraBagi19str);
        DocumentReference hnraRef = db.collection("suara").document("pt_hanura");
        batch.update(hnraRef,"jumlah_bagi1", jmlSuaraBagi110str);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Bagi 1 Berhasil!");
            }
        });
    }

    private void addBagi3(int jmlSuaraBagi31, int jmlSuaraBagi32, int jmlSuaraBagi33, int jmlSuaraBagi34, int jmlSuaraBagi35, int jmlSuaraBagi36, int jmlSuaraBagi37, int jmlSuaraBagi38, int jmlSuaraBagi39, int jmlSuaraBagi310){
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference nsdmRef = db.collection("suara").document("pt_nasdem");
        batch.update(nsdmRef,"jumlah_bagi3", jmlSuaraBagi31);
        DocumentReference pkbRef = db.collection("suara").document("pt_pkb");
        batch.update(pkbRef,"jumlah_bagi3", jmlSuaraBagi32);
        DocumentReference pksRef = db.collection("suara").document("pt_pks");
        batch.update(pksRef,"jumlah_bagi3", jmlSuaraBagi33);
        DocumentReference pdipRef = db.collection("suara").document("pt_pdip");
        batch.update(pdipRef,"jumlah_bagi3", jmlSuaraBagi34);
        DocumentReference glkrRef = db.collection("suara").document("pt_golkar");
        batch.update(glkrRef,"jumlah_bagi3", jmlSuaraBagi35);
        DocumentReference grndRef = db.collection("suara").document("pt_gerinda");
        batch.update(grndRef,"jumlah_bagi3", jmlSuaraBagi36);
        DocumentReference dmkrtRef = db.collection("suara").document("pt_demokrat");
        batch.update(dmkrtRef,"jumlah_bagi3", jmlSuaraBagi37);
        DocumentReference panRef = db.collection("suara").document("pt_pan");
        batch.update(panRef,"jumlah_bagi3", jmlSuaraBagi38);
        DocumentReference pppRef = db.collection("suara").document("pt_ppp");
        batch.update(pppRef,"jumlah_bagi3", jmlSuaraBagi39);
        DocumentReference hnraRef = db.collection("suara").document("pt_hanura");
        batch.update(hnraRef,"jumlah_bagi3", jmlSuaraBagi310);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Bagi 3 Berhasil!");
            }
        });
    }

    private void addBagi5(int jmlSuaraBagi51, int jmlSuaraBagi52, int jmlSuaraBagi53, int jmlSuaraBagi54, int jmlSuaraBagi55, int jmlSuaraBagi56, int jmlSuaraBagi57, int jmlSuaraBagi58, int jmlSuaraBagi59, int jmlSuaraBagi510){
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference nsdmRef = db.collection("suara").document("pt_nasdem");
        batch.update(nsdmRef,"jumlah_bagi5", jmlSuaraBagi51);
        DocumentReference pkbRef = db.collection("suara").document("pt_pkb");
        batch.update(pkbRef,"jumlah_bagi5", jmlSuaraBagi52);
        DocumentReference pksRef = db.collection("suara").document("pt_pks");
        batch.update(pksRef,"jumlah_bagi5", jmlSuaraBagi53);
        DocumentReference pdipRef = db.collection("suara").document("pt_pdip");
        batch.update(pdipRef,"jumlah_bagi5", jmlSuaraBagi54);
        DocumentReference glkrRef = db.collection("suara").document("pt_golkar");
        batch.update(glkrRef,"jumlah_bagi5", jmlSuaraBagi55);
        DocumentReference grndRef = db.collection("suara").document("pt_gerinda");
        batch.update(grndRef,"jumlah_bagi5", jmlSuaraBagi56);
        DocumentReference dmkrtRef = db.collection("suara").document("pt_demokrat");
        batch.update(dmkrtRef,"jumlah_bagi5", jmlSuaraBagi57);
        DocumentReference panRef = db.collection("suara").document("pt_pan");
        batch.update(panRef,"jumlah_bagi5", jmlSuaraBagi58);
        DocumentReference pppRef = db.collection("suara").document("pt_ppp");
        batch.update(pppRef,"jumlah_bagi5", jmlSuaraBagi59);
        DocumentReference hnraRef = db.collection("suara").document("pt_hanura");
        batch.update(hnraRef,"jumlah_bagi5", jmlSuaraBagi510);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Bagi 5 Berhasil!");
            }
        });
    }

    private void addBagi7(int jmlSuaraBagi71, int jmlSuaraBagi72, int jmlSuaraBagi73, int jmlSuaraBagi74, int jmlSuaraBagi75, int jmlSuaraBagi76, int jmlSuaraBagi77, int jmlSuaraBagi78, int jmlSuaraBagi79, int jmlSuaraBagi710){
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference nsdmRef = db.collection("suara").document("pt_nasdem");
        batch.update(nsdmRef,"jumlah_bagi7", jmlSuaraBagi71);
        DocumentReference pkbRef = db.collection("suara").document("pt_pkb");
        batch.update(pkbRef,"jumlah_bagi7", jmlSuaraBagi72);
        DocumentReference pksRef = db.collection("suara").document("pt_pks");
        batch.update(pksRef,"jumlah_bagi7", jmlSuaraBagi73);
        DocumentReference pdipRef = db.collection("suara").document("pt_pdip");
        batch.update(pdipRef,"jumlah_bagi7", jmlSuaraBagi74);
        DocumentReference glkrRef = db.collection("suara").document("pt_golkar");
        batch.update(glkrRef,"jumlah_bagi7", jmlSuaraBagi75);
        DocumentReference grndRef = db.collection("suara").document("pt_gerinda");
        batch.update(grndRef,"jumlah_bagi7", jmlSuaraBagi76);
        DocumentReference dmkrtRef = db.collection("suara").document("pt_demokrat");
        batch.update(dmkrtRef,"jumlah_bagi7", jmlSuaraBagi77);
        DocumentReference panRef = db.collection("suara").document("pt_pan");
        batch.update(panRef,"jumlah_bagi7", jmlSuaraBagi78);
        DocumentReference pppRef = db.collection("suara").document("pt_ppp");
        batch.update(pppRef,"jumlah_bagi7", jmlSuaraBagi79);
        DocumentReference hnraRef = db.collection("suara").document("pt_hanura");
        batch.update(hnraRef,"jumlah_bagi7", jmlSuaraBagi710);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Bagi 7 Berhasil!");
            }
        });
    }
}
