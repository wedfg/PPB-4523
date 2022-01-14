package com.example.aplikasimenumakanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[], s3[], s4[];
    int images[] = {R.drawable.pecellele,R.drawable.esoyen,R.drawable.lumpia,R.drawable.soto,R.drawable.ketoprak,R.drawable.saladbuah};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        s1=getResources().getStringArray(R.array.Menu);
        s2=getResources().getStringArray(R.array.Description);
        s3=getResources().getStringArray(R.array.Harga);
        s4=getResources().getStringArray(R.array.Hrg);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, s4, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}