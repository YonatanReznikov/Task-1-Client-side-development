package com.example.spongebob;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter adapter;
    private EditText filterText;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton playButton = findViewById(R.id.music);
        mediaPlayer = MediaPlayer.create(this, R.raw.theme);

        dataSet = new ArrayList<>();
        recyclerView =  findViewById(R.id.resView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        filterText = findViewById(R.id.editTextText);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < com.example.spongebob.myData.nameArray.length ; i++){
            dataSet.add(new DataModel(
                    com.example.spongebob.myData.nameArray[i],
                    com.example.spongebob.myData.descriptionArray[i],
                    com.example.spongebob.myData.drawableArray[i],
                    com.example.spongebob.myData.id_[i]
            ));
        }
        if (filterText != null) {
            filterText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.filter(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
        playButton.setOnClickListener(v -> {
            if (!isPlaying) {
                mediaPlayer.start();
                isPlaying = true;
            } else {
                mediaPlayer.pause();
                isPlaying = false;
            }
        });
        adapter = new CustomeAdapter(dataSet, this);
        recyclerView.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

}








