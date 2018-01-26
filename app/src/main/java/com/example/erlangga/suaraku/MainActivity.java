package com.example.erlangga.suaraku;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erlangga.suaraku.Data.Partai;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityMain";
    @BindView(R.id.nama_dapil)
    AutoCompleteTextView namaDapil;
    @BindView(R.id.partai_nasdem)
    EditText partaiNasdem;
    @BindView(R.id.partai_pkb)
    EditText partaiPKB;
    @BindView(R.id.partai_pks)
    EditText partaiPKS;
    @BindView(R.id.partai_pdip)
    EditText partaiPDIP;
    @BindView(R.id.partai_golkar)
    EditText partaiGolkar;
    @BindView(R.id.partai_gerinda)
    EditText partaiGerinda;
    @BindView(R.id.partai_demokrat)
    EditText partaiDemokrat;
    @BindView(R.id.partai_pan)
    EditText partaiPAN;
    @BindView(R.id.partai_ppp)
    EditText partaiPPP;
    @BindView(R.id.partai_hanura)
    EditText partaiHanura;
    @BindView(R.id.save_button)
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
        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("UpdateNoteId");

            partaiNasdem.setText(bundle.getString("UpdatePartaiJumlah"));

//            partaiPKB.setText(bundle.getString("UpdateNoteContent"));
//            partaiPKS.setText(bundle.getString("UpdateNoteContent"));
//            partaiPDIP.setText(bundle.getString("UpdateNoteContent"));
//            partaiGolkar.setText(bundle.getString("UpdateNoteContent"));
//            partaiGerinda.setText(bundle.getString("UpdateNoteContent"));
//            partaiDemokrat.setText(bundle.getString("UpdateNoteContent"));
//            partaiPAN.setText(bundle.getString("UpdateNoteContent"));
//            partaiPPP.setText(bundle.getString("UpdateNoteContent"));
//            partaiHanura.setText(bundle.getString("UpdateNoteContent"));
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jumlah_suara = partaiNasdem.getText().toString();
                addPartai(jumlah_suara);
                finish();
            }
        });
    }

    private void addPartai(String jumlah_suara) {
        Map<String, Object> note = new Partai(jumlah_suara).toMap();

        db.collection("partai")
                .add(note)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "Data berhasil dimasukan!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Note document", e);
                        Toast.makeText(getApplicationContext(), "Data tidak berhasil dimasukan!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
