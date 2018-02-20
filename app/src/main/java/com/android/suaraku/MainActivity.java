package com.android.suaraku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

////import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final
    String[] dapilAuto = {"Aceh I","Aceh II","Sumut I","Sumut II","Sumut III","Sumbar I","Sumbar II","Riau I","Riau II",
            "Jambi","Sumsel I","Sumsel II","Kepulauan Bangka Belitung","Bengkulu","Lampung I","Lampung II","Kepulauan Riau",
            "DKI Jakarta I","DKI Jakarta II","DKI Jakarta III","Jabar I","Jabar II","Jabar III","Jabar IV","Jabar V","Jabar VI","Jabar VII",
            "Jabar VIII","Jabar IX","Jabar X","Jabar XI","Jateng I","Jateng II","Jateng III","Jateng IV","Jateng V","Jateng VI","Jateng VII",
            "Jateng VIII","Jateng IX","Jateng X","Jatim I","Jatim II","Jatim III","Jatim IV","Jatim V","Jatim VI","Jatim VII","Jatim VIII",
            "Jatim IX","Jatim X","Jatim XI","DIY","Banten I","Banten II","Banten III","Bali","NTB I","NTB II","NTT I","NTT II","Kalbar I",
            "Kalbar II","Kalteng","Kalsel I","Kalsel II","Kaltim","Kaltara","Sulut","Sulteng","Sulsel I","Sulsel II","Sulsel III","Sulbar",
            "Sultra","Gorontalo","Maluku","Malut","Papua I","Papua II"};

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
    EditText textGaruda;
    EditText textBerkarya;
    EditText textPSI;
    EditText textPerindo;
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
        textGaruda = findViewById(R.id.partai_garuda);
        textBerkarya = findViewById(R.id.partai_berkarya);
        textPSI = findViewById(R.id.partai_psi);
        textPerindo = findViewById(R.id.partai_perindo);
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
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,dapilAuto);
        //Getting the instance of AutoCompleteTextView
        final AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.nama_dapil);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);
        //ketika di pilih autocompletenya
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(MainActivity.this, HasilActivity.class);
                String dapil = (String)parent.getItemAtPosition(position);
                Log.i("myTag", "SELECTED TEXT WAS["+dapil+"]");
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("string_id", dapil);
                editor.commit();
//                i.putExtra("KEY_DAPIL",dapil);
            }
        });
        //intent for set data nama partai


        //savebutton
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String jumlah_suara1 = textPKB.getText().toString();
               String jumlah_suara2 = textGerinda.getText().toString();
               String jumlah_suara3 = textPDIP.getText().toString();
               String jumlah_suara4 = textGolkar.getText().toString();
               String jumlah_suara5 = textNasdem.getText().toString();
               String jumlah_suara6 = textGaruda.getText().toString();
               String jumlah_suara7 = textBerkarya.getText().toString();
               String jumlah_suara8 = textPKS.getText().toString();
               String jumlah_suara9 = textPerindo.getText().toString();
               String jumlah_suara10 = textPPP.getText().toString();
               String jumlah_suara11 = textPSI.getText().toString();
               String jumlah_suara12 = textPAN.getText().toString();
               String jumlah_suara13 = textHanura.getText().toString();
               String jumlah_suara14 = textDemokrat.getText().toString();

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
                int jmlSuara11 = Integer.parseInt(jumlah_suara11);
                int jmlSuara12 = Integer.parseInt(jumlah_suara12);
                int jmlSuara13 = Integer.parseInt(jumlah_suara13);
                int jmlSuara14 = Integer.parseInt(jumlah_suara14);


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
                int jmlSuaraBagi111 = jmlSuara11/1;
                int jmlSuaraBagi112 = jmlSuara12/1;
                int jmlSuaraBagi113 = jmlSuara13/1;
                int jmlSuaraBagi114 = jmlSuara14/1;


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
                int jmlSuaraBagi311 = jmlSuara11/3;
                int jmlSuaraBagi312 = jmlSuara12/3;
                int jmlSuaraBagi313 = jmlSuara13/3;
                int jmlSuaraBagi314 = jmlSuara14/3;

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
                int jmlSuaraBagi511 = jmlSuara11/5;
                int jmlSuaraBagi512 = jmlSuara12/5;
                int jmlSuaraBagi513 = jmlSuara13/5;
                int jmlSuaraBagi514 = jmlSuara14/5;

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
                int jmlSuaraBagi711 = jmlSuara11/7;
                int jmlSuaraBagi712 = jmlSuara12/7;
                int jmlSuaraBagi713 = jmlSuara13/7;
                int jmlSuaraBagi714 = jmlSuara14/7;

                addPartai(jmlSuaraBagi11,jmlSuaraBagi12,jmlSuaraBagi13,jmlSuaraBagi14,jmlSuaraBagi15,jmlSuaraBagi16,jmlSuaraBagi17,jmlSuaraBagi18,jmlSuaraBagi19,jmlSuaraBagi110,jmlSuaraBagi111,jmlSuaraBagi112,jmlSuaraBagi113,jmlSuaraBagi114,
                        jmlSuaraBagi31,jmlSuaraBagi32,jmlSuaraBagi33,jmlSuaraBagi34,jmlSuaraBagi35,jmlSuaraBagi36,jmlSuaraBagi37,jmlSuaraBagi38,jmlSuaraBagi39,jmlSuaraBagi310,jmlSuaraBagi311,jmlSuaraBagi312,jmlSuaraBagi313,jmlSuaraBagi314,
                        jmlSuaraBagi51,jmlSuaraBagi52,jmlSuaraBagi53,jmlSuaraBagi54,jmlSuaraBagi55,jmlSuaraBagi56,jmlSuaraBagi57,jmlSuaraBagi58,jmlSuaraBagi59,jmlSuaraBagi510,jmlSuaraBagi511, jmlSuaraBagi512, jmlSuaraBagi513, jmlSuaraBagi514,
                        jmlSuaraBagi71,jmlSuaraBagi72,jmlSuaraBagi73,jmlSuaraBagi74,jmlSuaraBagi75,jmlSuaraBagi76,jmlSuaraBagi77,jmlSuaraBagi78,jmlSuaraBagi79,jmlSuaraBagi710,jmlSuaraBagi711, jmlSuaraBagi712, jmlSuaraBagi713, jmlSuaraBagi714);
               addBagi1(jmlSuaraBagi11,jmlSuaraBagi12,jmlSuaraBagi13,jmlSuaraBagi14,jmlSuaraBagi15,jmlSuaraBagi16,jmlSuaraBagi17,jmlSuaraBagi18,jmlSuaraBagi19,jmlSuaraBagi110,jmlSuaraBagi111,jmlSuaraBagi112,jmlSuaraBagi113,jmlSuaraBagi114);
                addBagi3(jmlSuaraBagi31,jmlSuaraBagi32,jmlSuaraBagi33,jmlSuaraBagi34,jmlSuaraBagi35,jmlSuaraBagi36,jmlSuaraBagi37,jmlSuaraBagi38,jmlSuaraBagi39,jmlSuaraBagi310,jmlSuaraBagi311,jmlSuaraBagi312,jmlSuaraBagi313,jmlSuaraBagi314);
                addBagi5(jmlSuaraBagi51,jmlSuaraBagi52,jmlSuaraBagi53,jmlSuaraBagi54,jmlSuaraBagi55,jmlSuaraBagi56,jmlSuaraBagi57,jmlSuaraBagi58,jmlSuaraBagi59,jmlSuaraBagi510,jmlSuaraBagi511, jmlSuaraBagi512, jmlSuaraBagi513, jmlSuaraBagi514);
                addBagi7(jmlSuaraBagi71,jmlSuaraBagi72,jmlSuaraBagi73,jmlSuaraBagi74,jmlSuaraBagi75,jmlSuaraBagi76,jmlSuaraBagi77,jmlSuaraBagi78,jmlSuaraBagi79,jmlSuaraBagi710,jmlSuaraBagi711, jmlSuaraBagi712, jmlSuaraBagi713, jmlSuaraBagi714);
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


    private void addPartai( int jmlSuaraBagi11, int jmlSuaraBagi12, int jmlSuaraBagi13, int jmlSuaraBagi14, int jmlSuaraBagi15, int jmlSuaraBagi16, int jmlSuaraBagi17, int jmlSuaraBagi18, int jmlSuaraBagi19, int jmlSuaraBagi110,int jmlSuaraBagi111, int jmlSuaraBagi112, int jmlSuaraBagi113, int jmlSuaraBagi114, int jmlSuaraBagi31, int jmlSuaraBagi32, int jmlSuaraBagi33, int jmlSuaraBagi34, int jmlSuaraBagi35, int jmlSuaraBagi36, int jmlSuaraBagi37, int jmlSuaraBagi38, int jmlSuaraBagi39, int jmlSuaraBagi310, int jmlSuaraBagi311, int jmlSuaraBagi312, int jmlSuaraBagi313, int jmlSuaraBagi314, int jmlSuaraBagi51, int jmlSuaraBagi52, int jmlSuaraBagi53, int jmlSuaraBagi54, int jmlSuaraBagi55, int jmlSuaraBagi56, int jmlSuaraBagi57, int jmlSuaraBagi58, int jmlSuaraBagi59, int jmlSuaraBagi510, int jmlSuaraBagi511, int jmlSuaraBagi512, int jmlSuaraBagi513, int jmlSuaraBagi514
            , int jmlSuaraBagi71, int jmlSuaraBagi72, int jmlSuaraBagi73, int jmlSuaraBagi74, int jmlSuaraBagi75, int jmlSuaraBagi76, int jmlSuaraBagi77, int jmlSuaraBagi78, int jmlSuaraBagi79, int jmlSuaraBagi710, int jmlSuaraBagi711, int jmlSuaraBagi712, int jmlSuaraBagi713, int jmlSuaraBagi714) {
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference pkb1Ref = db.collection("pembagian").document("pt_pkb_bagi1");
        batch.update(pkb1Ref,"suara_rank", jmlSuaraBagi11);
        DocumentReference grnd1Ref = db.collection("pembagian").document("pt_gerinda_bagi1");
        batch.update(grnd1Ref,"suara_rank", jmlSuaraBagi12);
        DocumentReference pdip1Ref = db.collection("pembagian").document("pt_pdip_bagi1");
        batch.update(pdip1Ref,"suara_rank", jmlSuaraBagi13);
        DocumentReference glkr1Ref = db.collection("pembagian").document("pt_golkar_bagi1");
        batch.update(glkr1Ref,"suara_rank", jmlSuaraBagi14);
        DocumentReference nsdm1Ref = db.collection("pembagian").document("pt_nasdem_bagi1");
        batch.update(nsdm1Ref,"suara_rank", jmlSuaraBagi15);
        DocumentReference grda1Ref = db.collection("pembagian").document("pt_garuda_bagi1");
        batch.update(grda1Ref,"suara_rank", jmlSuaraBagi16);
        DocumentReference bkrya1Ref = db.collection("pembagian").document("pt_berkarya_bagi1");
        batch.update(bkrya1Ref,"suara_rank", jmlSuaraBagi17);
        DocumentReference pks1Ref = db.collection("pembagian").document("pt_pks_bagi1");
        batch.update(pks1Ref,"suara_rank", jmlSuaraBagi18);
        DocumentReference prndo1Ref = db.collection("pembagian").document("pt_perindo_bagi1");
        batch.update(prndo1Ref,"suara_rank", jmlSuaraBagi19);
        DocumentReference ppp1Ref = db.collection("pembagian").document("pt_ppp_bagi1");
        batch.update(ppp1Ref,"suara_rank", jmlSuaraBagi110);
        DocumentReference psi1Ref = db.collection("pembagian").document("pt_psi_bagi1");
        batch.update(psi1Ref,"suara_rank", jmlSuaraBagi111);
        DocumentReference pan1Ref = db.collection("pembagian").document("pt_pan_bagi1");
        batch.update(pan1Ref,"suara_rank", jmlSuaraBagi112);
        DocumentReference hnra1Ref = db.collection("pembagian").document("pt_hanura_bagi1");
        batch.update(hnra1Ref,"suara_rank", jmlSuaraBagi113);
        DocumentReference dmkrt1Ref = db.collection("pembagian").document("pt_demokrat_bagi1");
        batch.update(dmkrt1Ref,"suara_rank", jmlSuaraBagi114);


        DocumentReference pkb3Ref = db.collection("pembagian").document("pt_pkb_bagi3");
        batch.update(pkb3Ref,"suara_rank", jmlSuaraBagi31);
        DocumentReference grnd3Ref = db.collection("pembagian").document("pt_gerinda_bagi3");
        batch.update(grnd3Ref,"suara_rank", jmlSuaraBagi32);
        DocumentReference pdip3Ref = db.collection("pembagian").document("pt_pdip_bagi3");
        batch.update(pdip3Ref,"suara_rank", jmlSuaraBagi33);
        DocumentReference glkr3Ref = db.collection("pembagian").document("pt_golkar_bagi3");
        batch.update(glkr3Ref,"suara_rank", jmlSuaraBagi34);
        DocumentReference nsdm3Ref = db.collection("pembagian").document("pt_nasdem_bagi3");
        batch.update(nsdm3Ref,"suara_rank", jmlSuaraBagi35);
        DocumentReference grda3Ref = db.collection("pembagian").document("pt_garuda_bagi3");
        batch.update(grda3Ref,"suara_rank", jmlSuaraBagi36);
        DocumentReference bkrya3Ref = db.collection("pembagian").document("pt_berkarya_bagi3");
        batch.update(bkrya3Ref,"suara_rank", jmlSuaraBagi37);
        DocumentReference pks3Ref = db.collection("pembagian").document("pt_pks_bagi3");
        batch.update(pks3Ref,"suara_rank", jmlSuaraBagi38);
        DocumentReference prndo3Ref = db.collection("pembagian").document("pt_perindo_bagi3");
        batch.update(prndo3Ref,"suara_rank", jmlSuaraBagi39);
        DocumentReference ppp3Ref = db.collection("pembagian").document("pt_ppp_bagi3");
        batch.update(ppp3Ref,"suara_rank", jmlSuaraBagi310);
        DocumentReference psi3Ref = db.collection("pembagian").document("pt_psi_bagi3");
        batch.update(psi3Ref,"suara_rank", jmlSuaraBagi311);
        DocumentReference pan3Ref = db.collection("pembagian").document("pt_pan_bagi3");
        batch.update(pan3Ref,"suara_rank", jmlSuaraBagi312);
        DocumentReference hnra3Ref = db.collection("pembagian").document("pt_hanura_bagi3");
        batch.update(hnra3Ref,"suara_rank", jmlSuaraBagi313);
        DocumentReference dmkrt3Ref = db.collection("pembagian").document("pt_demokrat_bagi3");
        batch.update(dmkrt3Ref,"suara_rank", jmlSuaraBagi314);

        DocumentReference pkb5Ref = db.collection("pembagian").document("pt_pkb_bagi5");
        batch.update(pkb5Ref,"suara_rank", jmlSuaraBagi51);
        DocumentReference grnd5Ref = db.collection("pembagian").document("pt_gerinda_bagi5");
        batch.update(grnd5Ref,"suara_rank", jmlSuaraBagi52);
        DocumentReference pdip5Ref = db.collection("pembagian").document("pt_pdip_bagi5");
        batch.update(pdip5Ref,"suara_rank", jmlSuaraBagi53);
        DocumentReference glkr5Ref = db.collection("pembagian").document("pt_golkar_bagi5");
        batch.update(glkr5Ref,"suara_rank", jmlSuaraBagi54);
        DocumentReference nsdm5Ref = db.collection("pembagian").document("pt_nasdem_bagi5");
        batch.update(nsdm5Ref,"suara_rank", jmlSuaraBagi55);
        DocumentReference grda5Ref = db.collection("pembagian").document("pt_garuda_bagi5");
        batch.update(grda5Ref,"suara_rank", jmlSuaraBagi56);
        DocumentReference bkrya5Ref = db.collection("pembagian").document("pt_berkarya_bagi5");
        batch.update(bkrya5Ref,"suara_rank", jmlSuaraBagi57);
        DocumentReference pks5Ref = db.collection("pembagian").document("pt_pks_bagi5");
        batch.update(pks5Ref,"suara_rank", jmlSuaraBagi58);
        DocumentReference prndo5Ref = db.collection("pembagian").document("pt_perindo_bagi5");
        batch.update(prndo5Ref,"suara_rank", jmlSuaraBagi59);
        DocumentReference ppp5Ref = db.collection("pembagian").document("pt_ppp_bagi5");
        batch.update(ppp5Ref,"suara_rank", jmlSuaraBagi510);
        DocumentReference psi5Ref = db.collection("pembagian").document("pt_psi_bagi5");
        batch.update(psi5Ref,"suara_rank", jmlSuaraBagi511);
        DocumentReference pan5Ref = db.collection("pembagian").document("pt_pan_bagi5");
        batch.update(pan5Ref,"suara_rank", jmlSuaraBagi512);
        DocumentReference hnra5Ref = db.collection("pembagian").document("pt_hanura_bagi5");
        batch.update(hnra5Ref,"suara_rank", jmlSuaraBagi513);
        DocumentReference dmkrt5Ref = db.collection("pembagian").document("pt_demokrat_bagi5");
        batch.update(dmkrt5Ref,"suara_rank", jmlSuaraBagi514);

        DocumentReference pkb7Ref = db.collection("pembagian").document("pt_pkb_bagi7");
        batch.update(pkb7Ref,"suara_rank", jmlSuaraBagi71);
        DocumentReference grnd7Ref = db.collection("pembagian").document("pt_gerinda_bagi7");
        batch.update(grnd7Ref,"suara_rank", jmlSuaraBagi72);
        DocumentReference pdip7Ref = db.collection("pembagian").document("pt_pdip_bagi7");
        batch.update(pdip7Ref,"suara_rank", jmlSuaraBagi73);
        DocumentReference glkr7Ref = db.collection("pembagian").document("pt_golkar_bagi7");
        batch.update(glkr7Ref,"suara_rank", jmlSuaraBagi74);
        DocumentReference nsdm7Ref = db.collection("pembagian").document("pt_nasdem_bagi7");
        batch.update(nsdm7Ref,"suara_rank", jmlSuaraBagi75);
        DocumentReference grda7Ref = db.collection("pembagian").document("pt_garuda_bagi7");
        batch.update(grda7Ref,"suara_rank", jmlSuaraBagi76);
        DocumentReference bkrya7Ref = db.collection("pembagian").document("pt_berkarya_bagi7");
        batch.update(bkrya7Ref,"suara_rank", jmlSuaraBagi77);
        DocumentReference pks7Ref = db.collection("pembagian").document("pt_pks_bagi7");
        batch.update(pks7Ref,"suara_rank", jmlSuaraBagi78);
        DocumentReference prndo7Ref = db.collection("pembagian").document("pt_perindo_bagi7");
        batch.update(prndo7Ref,"suara_rank", jmlSuaraBagi79);
        DocumentReference ppp7Ref = db.collection("pembagian").document("pt_ppp_bagi7");
        batch.update(ppp7Ref,"suara_rank", jmlSuaraBagi710);
        DocumentReference psi7Ref = db.collection("pembagian").document("pt_psi_bagi7");
        batch.update(psi7Ref,"suara_rank", jmlSuaraBagi711);
        DocumentReference pan7Ref = db.collection("pembagian").document("pt_pan_bagi7");
        batch.update(pan7Ref,"suara_rank", jmlSuaraBagi712);
        DocumentReference hnra7Ref = db.collection("pembagian").document("pt_hanura_bagi7");
        batch.update(hnra7Ref,"suara_rank", jmlSuaraBagi713);
        DocumentReference dmkrt7Ref = db.collection("pembagian").document("pt_demokrat_bagi7");
        batch.update(dmkrt7Ref,"suara_rank", jmlSuaraBagi714);


        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Berhasil nih!");
            }
        });

    }
    private void addBagi1(int jmlSuaraBagi111, int jmlSuaraBagi112, int jmlSuaraBagi113, int jmlSuaraBagi114, int jmlSuaraBagi11, int jmlSuaraBagi12, int jmlSuaraBagi13, int jmlSuaraBagi14, int jmlSuaraBagi15, int jmlSuaraBagi16, int jmlSuaraBagi17, int jmlSuaraBagi18, int jmlSuaraBagi19, int jmlSuaraBagi110){
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference pkb1Ref = db.collection("suara").document("pt_pkb");
        batch.update(pkb1Ref,"jumlah_bagi1", jmlSuaraBagi11);
        DocumentReference grnd1Ref = db.collection("suara").document("pt_gerinda");
        batch.update(grnd1Ref,"jumlah_bagi1", jmlSuaraBagi12);
        DocumentReference pdip1Ref = db.collection("suara").document("pt_pdip");
        batch.update(pdip1Ref,"jumlah_bagi1", jmlSuaraBagi13);
        DocumentReference glkr1Ref = db.collection("suara").document("pt_golkar");
        batch.update(glkr1Ref,"jumlah_bagi1", jmlSuaraBagi14);
        DocumentReference nsdm1Ref = db.collection("suara").document("pt_nasdem");
        batch.update(nsdm1Ref,"jumlah_bagi1", jmlSuaraBagi15);
        DocumentReference grda1Ref = db.collection("suara").document("pt_garuda");
        batch.update(grda1Ref,"jumlah_bagi1", jmlSuaraBagi16);
        DocumentReference bkrya1Ref = db.collection("suara").document("pt_berkarya");
        batch.update(bkrya1Ref,"jumlah_bagi1", jmlSuaraBagi17);
        DocumentReference pks1Ref = db.collection("suara").document("pt_pks");
        batch.update(pks1Ref,"jumlah_bagi1", jmlSuaraBagi18);
        DocumentReference prndo1Ref = db.collection("suara").document("pt_perindo");
        batch.update(prndo1Ref,"jumlah_bagi1", jmlSuaraBagi19);
        DocumentReference ppp1Ref = db.collection("suara").document("pt_ppp");
        batch.update(ppp1Ref,"jumlah_bagi1", jmlSuaraBagi110);
        DocumentReference psi1Ref = db.collection("suara").document("pt_psi");
        batch.update(psi1Ref,"jumlah_bagi1", jmlSuaraBagi111);
        DocumentReference pan1Ref = db.collection("suara").document("pt_pan");
        batch.update(pan1Ref,"jumlah_bagi1", jmlSuaraBagi112);
        DocumentReference hnra1Ref = db.collection("suara").document("pt_hanura");
        batch.update(hnra1Ref,"jumlah_bagi1", jmlSuaraBagi113);
        DocumentReference dmkrt1Ref = db.collection("suara").document("pt_demokrat");
        batch.update(dmkrt1Ref,"jumlah_bagi1", jmlSuaraBagi114);


        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Bagi 1 Berhasil!");
            }
        });
    }

    private void addBagi3(int jmlSuaraBagi311, int jmlSuaraBagi312, int jmlSuaraBagi313, int jmlSuaraBagi314, int jmlSuaraBagi31, int jmlSuaraBagi32, int jmlSuaraBagi33, int jmlSuaraBagi34, int jmlSuaraBagi35, int jmlSuaraBagi36, int jmlSuaraBagi37, int jmlSuaraBagi38, int jmlSuaraBagi39, int jmlSuaraBagi310){
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference pkb1Ref = db.collection("suara").document("pt_pkb");
        batch.update(pkb1Ref,"jumlah_bagi3", jmlSuaraBagi31);
        DocumentReference grnd1Ref = db.collection("suara").document("pt_gerinda");
        batch.update(grnd1Ref,"jumlah_bagi3", jmlSuaraBagi32);
        DocumentReference pdip1Ref = db.collection("suara").document("pt_pdip");
        batch.update(pdip1Ref,"jumlah_bagi3", jmlSuaraBagi33);
        DocumentReference glkr1Ref = db.collection("suara").document("pt_golkar");
        batch.update(glkr1Ref,"jumlah_bagi3", jmlSuaraBagi34);
        DocumentReference nsdm1Ref = db.collection("suara").document("pt_nasdem");
        batch.update(nsdm1Ref,"jumlah_bagi3", jmlSuaraBagi35);
        DocumentReference grda1Ref = db.collection("suara").document("pt_garuda");
        batch.update(grda1Ref,"jumlah_bagi3", jmlSuaraBagi36);
        DocumentReference bkrya1Ref = db.collection("suara").document("pt_berkarya");
        batch.update(bkrya1Ref,"jumlah_bagi3", jmlSuaraBagi37);
        DocumentReference pks1Ref = db.collection("suara").document("pt_pks");
        batch.update(pks1Ref,"jumlah_bagi3", jmlSuaraBagi38);
        DocumentReference prndo1Ref = db.collection("suara").document("pt_perindo");
        batch.update(prndo1Ref,"jumlah_bagi3", jmlSuaraBagi39);
        DocumentReference ppp1Ref = db.collection("suara").document("pt_ppp");
        batch.update(ppp1Ref,"jumlah_bagi3", jmlSuaraBagi310);
        DocumentReference psi1Ref = db.collection("suara").document("pt_psi");
        batch.update(psi1Ref,"jumlah_bagi3", jmlSuaraBagi311);
        DocumentReference pan1Ref = db.collection("suara").document("pt_pan");
        batch.update(pan1Ref,"jumlah_bagi3", jmlSuaraBagi312);
        DocumentReference hnra1Ref = db.collection("suara").document("pt_hanura");
        batch.update(hnra1Ref,"jumlah_bagi3", jmlSuaraBagi313);
        DocumentReference dmkrt1Ref = db.collection("suara").document("pt_demokrat");
        batch.update(dmkrt1Ref,"jumlah_bagi3", jmlSuaraBagi314);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Bagi 3 Berhasil!");
            }
        });
    }

    private void addBagi5(int jmlSuaraBagi511, int  jmlSuaraBagi512, int  jmlSuaraBagi513, int  jmlSuaraBagi514, int jmlSuaraBagi51, int jmlSuaraBagi52, int jmlSuaraBagi53, int jmlSuaraBagi54, int jmlSuaraBagi55, int jmlSuaraBagi56, int jmlSuaraBagi57, int jmlSuaraBagi58, int jmlSuaraBagi59, int jmlSuaraBagi510){
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference pkb1Ref = db.collection("suara").document("pt_pkb");
        batch.update(pkb1Ref,"jumlah_bagi5", jmlSuaraBagi51);
        DocumentReference grnd1Ref = db.collection("suara").document("pt_gerinda");
        batch.update(grnd1Ref,"jumlah_bagi5", jmlSuaraBagi52);
        DocumentReference pdip1Ref = db.collection("suara").document("pt_pdip");
        batch.update(pdip1Ref,"jumlah_bagi5", jmlSuaraBagi53);
        DocumentReference glkr1Ref = db.collection("suara").document("pt_golkar");
        batch.update(glkr1Ref,"jumlah_bagi5", jmlSuaraBagi54);
        DocumentReference nsdm1Ref = db.collection("suara").document("pt_nasdem");
        batch.update(nsdm1Ref,"jumlah_bagi5", jmlSuaraBagi55);
        DocumentReference grda1Ref = db.collection("suara").document("pt_garuda");
        batch.update(grda1Ref,"jumlah_bagi5", jmlSuaraBagi56);
        DocumentReference bkrya1Ref = db.collection("suara").document("pt_berkarya");
        batch.update(bkrya1Ref,"jumlah_bagi5", jmlSuaraBagi57);
        DocumentReference pks1Ref = db.collection("suara").document("pt_pks");
        batch.update(pks1Ref,"jumlah_bagi5", jmlSuaraBagi58);
        DocumentReference prndo1Ref = db.collection("suara").document("pt_perindo");
        batch.update(prndo1Ref,"jumlah_bagi5", jmlSuaraBagi59);
        DocumentReference ppp1Ref = db.collection("suara").document("pt_ppp");
        batch.update(ppp1Ref,"jumlah_bagi5", jmlSuaraBagi510);
        DocumentReference psi1Ref = db.collection("suara").document("pt_psi");
        batch.update(psi1Ref,"jumlah_bagi5", jmlSuaraBagi511);
        DocumentReference pan1Ref = db.collection("suara").document("pt_pan");
        batch.update(pan1Ref,"jumlah_bagi5", jmlSuaraBagi512);
        DocumentReference hnra1Ref = db.collection("suara").document("pt_hanura");
        batch.update(hnra1Ref,"jumlah_bagi5", jmlSuaraBagi513);
        DocumentReference dmkrt1Ref = db.collection("suara").document("pt_demokrat");
        batch.update(dmkrt1Ref,"jumlah_bagi5", jmlSuaraBagi514);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Bagi 5 Berhasil!");
            }
        });
    }

    private void addBagi7(int jmlSuaraBagi711, int jmlSuaraBagi712, int jmlSuaraBagi713, int jmlSuaraBagi714, int jmlSuaraBagi71, int jmlSuaraBagi72, int jmlSuaraBagi73, int jmlSuaraBagi74, int jmlSuaraBagi75, int jmlSuaraBagi76, int jmlSuaraBagi77, int jmlSuaraBagi78, int jmlSuaraBagi79, int jmlSuaraBagi710){
        WriteBatch batch = db.batch();
        //setJumlah_SUARA
        DocumentReference pkb1Ref = db.collection("suara").document("pt_pkb");
        batch.update(pkb1Ref,"jumlah_bagi7", jmlSuaraBagi71);
        DocumentReference grnd1Ref = db.collection("suara").document("pt_gerinda");
        batch.update(grnd1Ref,"jumlah_bagi7", jmlSuaraBagi72);
        DocumentReference pdip1Ref = db.collection("suara").document("pt_pdip");
        batch.update(pdip1Ref,"jumlah_bagi7", jmlSuaraBagi73);
        DocumentReference glkr1Ref = db.collection("suara").document("pt_golkar");
        batch.update(glkr1Ref,"jumlah_bagi7", jmlSuaraBagi74);
        DocumentReference nsdm1Ref = db.collection("suara").document("pt_nasdem");
        batch.update(nsdm1Ref,"jumlah_bagi7", jmlSuaraBagi75);
        DocumentReference grda1Ref = db.collection("suara").document("pt_garuda");
        batch.update(grda1Ref,"jumlah_bagi7", jmlSuaraBagi76);
        DocumentReference bkrya1Ref = db.collection("suara").document("pt_berkarya");
        batch.update(bkrya1Ref,"jumlah_bagi7", jmlSuaraBagi77);
        DocumentReference pks1Ref = db.collection("suara").document("pt_pks");
        batch.update(pks1Ref,"jumlah_bagi7", jmlSuaraBagi78);
        DocumentReference prndo1Ref = db.collection("suara").document("pt_perindo");
        batch.update(prndo1Ref,"jumlah_bagi7", jmlSuaraBagi79);
        DocumentReference ppp1Ref = db.collection("suara").document("pt_ppp");
        batch.update(ppp1Ref,"jumlah_bagi7", jmlSuaraBagi710);
        DocumentReference psi1Ref = db.collection("suara").document("pt_psi");
        batch.update(psi1Ref,"jumlah_bagi7", jmlSuaraBagi711);
        DocumentReference pan1Ref = db.collection("suara").document("pt_pan");
        batch.update(pan1Ref,"jumlah_bagi7", jmlSuaraBagi712);
        DocumentReference hnra1Ref = db.collection("suara").document("pt_hanura");
        batch.update(hnra1Ref,"jumlah_bagi7", jmlSuaraBagi713);
        DocumentReference dmkrt1Ref = db.collection("suara").document("pt_demokrat");
        batch.update(dmkrt1Ref,"jumlah_bagi7", jmlSuaraBagi714);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Bagi 7 Berhasil!");
            }
        });
    }
}
