package com.example.appforhour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appforhour.R;
import com.example.appforhour.entity.Word;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    //создадим список элементов, который адаптор будет отрисовывать
    private List<Word> words;

    public WordAdapter(){
        words = new ArrayList<>();
    }
    //создаем метод, который устанавливает те значения , которые мы передаем из actyvity
    public void setWords(List<Word>words){

        //очищаем данные в адаптаре перед вызовом метода
        if (!this.words.isEmpty()) this.words.clear();

        this.words.addAll(words);
        notifyDataSetChanged();//указываем адаптеру , что данные изменились, необ-мо перерис-ь сод-е

    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // связываем эл-т который создали word_item_view.xml и эл-т в адаптаре WordViewHolder
        Context context = viewGroup.getContext();
        //связали ниже xml эл-т word_item_view и java объект view
        View view = LayoutInflater.from(context).inflate(R.layout.word_item_view, viewGroup, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int i) {
        //связываем объекты с представлениями в этом методе

        Word word = words.get(i);
        WordViewHolder.bind(word);

    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    // создадим внутренний class который хранит данные для отрисовки пользовательского интерфейса
    static class WordViewHolder extends RecyclerView.ViewHolder {

        //создадим текстовые поля которые описывали в xml коде
        private TextView englishVersionTextView;
        private TextView russianVersionTextView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);

            englishVersionTextView = itemView.findViewById(R.id.english_version_text_view);
            russianVersionTextView = itemView.findViewById(R.id.russian_version_text_view);
        }

        public void bind(Word word) {
            englishVersionTextView.setText(word.getEnglishVersion());
            russianVersionTextView.setText(word.getRussianVersion());
        }
    }
}
