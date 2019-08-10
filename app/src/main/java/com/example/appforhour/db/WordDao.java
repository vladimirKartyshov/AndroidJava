package com.example.appforhour.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appforhour.R;
import com.example.appforhour.entity.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("SELECT * FROM Word")
    List<Word> getWords();
}
