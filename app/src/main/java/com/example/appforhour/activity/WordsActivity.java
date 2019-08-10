package com.example.appforhour.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.LinearLayout;

import com.example.appforhour.App;
import com.example.appforhour.R;
import com.example.appforhour.adapter.WordAdapter;
import com.example.appforhour.db.AppDatabase;
import com.example.appforhour.entity.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class WordsActivity extends AppCompatActivity {

    private static final int ADD_WORD_REQUEST_CODE = 1001;

    private FloatingActionButton addButton;
    private RecyclerView recyclerView;
    private WordAdapter wordAdapter;

    private AppDatabase appDatabase = App.getAppDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent указывает, что хотим перейти на новый экран(откуда-куда
                Intent intent = new Intent(WordsActivity.this, AddWordActivity.class);
                startActivityForResult(intent, ADD_WORD_REQUEST_CODE);//const указывает с каким кодом
                                                   //запускается следующая activity(AddWordActivity)
            }
        });


        recyclerView = findViewById(R.id.word_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        wordAdapter = new WordAdapter();//создали адаптер
        recyclerView.setAdapter(wordAdapter);//связываем c recyclerView

        loadWords();

    }

    private void loadWords(){

        new AsyncTask<Void, Void, List<Word>>() {
            @Override
            protected List<Word> doInBackground(Void... voids) {
                return appDatabase.getWordDao().getWords();
            }

            @Override
            protected void onPostExecute(List<Word> words) {
                wordAdapter.setWords(words);
            }
        }.execute();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_WORD_REQUEST_CODE && resultCode == RESULT_OK){
            loadWords();
        }
    }
}
