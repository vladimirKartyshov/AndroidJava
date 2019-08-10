package com.example.appforhour.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.appforhour.entity.Word;


@Database(entities = Word.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WordDao getWordDao();




}
