package com.example.projectvideomateri12;

import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Struct;
import java.util.ArrayList;

public class TampilSemuaData extends AppCompatActivity {
    ListView listView;
    ArrayAdapter arrayAdapter;

    ArrayList<String> arrayTampil = new ArrayList<>();
    ArrayList<String> arrayEdit = new ArrayList<>();
    ArrayList<String> arrayHapus = new ArrayList<>();

    DatabaseReference databaseReference;
    public String data1, data2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_data);

        listView=findViewById(R.id.listdataku);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Siswa");
        arrayAdapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,arrayTampil);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TampilSemuaData.this,UpdateData.class);
                startActivity(intent);
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String hasil = snapshot.getValue(Mahasiswa.class).toPrint();
                arrayTampil.add(hasil);
                String key = snapshot.getKey();
                arrayEdit.add(key);
                arrayHapus.add(key);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String hasil = snapshot.getValue(Mahasiswa.class).toPrint();
                String key = snapshot.getKey();
                int indek = arrayEdit.indexOf(key);
                arrayTampil.set(indek,hasil);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String hasil = snapshot.getValue(Mahasiswa.class).toPrint();
                String key = snapshot.getKey();
                int indek = arrayHapus.indexOf(key);
                arrayTampil.remove(indek);
                arrayHapus.remove(indek);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}