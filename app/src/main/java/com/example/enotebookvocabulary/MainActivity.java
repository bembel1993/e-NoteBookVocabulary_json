package com.example.enotebookvocabulary;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Word> adapter;
    private EditText russ, eng, ger;
    private List<Word> words;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        russ = findViewById(R.id.russ);
        eng = findViewById(R.id.eng);
        ger = findViewById(R.id.ger);

        listView = findViewById(R.id.list);
        words = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        listView.setAdapter(adapter);
    }

   /* public void addWords(View view){
        String russian = russ.getText().toString();
        String english = eng.getText().toString();
        String germany = ger.getText().toString();
        Word words1 = new Word(russian, english, germany);
        words.add(words1);
        adapter.notifyDataSetChanged();
    }*/

    public void save(View view){

        String russian = russ.getText().toString();
        String english = eng.getText().toString();
        String germany = ger.getText().toString();

       Word words1 = new Word(russian, english, germany);

        words.add(words1);
        adapter.notifyDataSetChanged();

        boolean result = JSONHelper.exportToJSON(this, words);

        if(result){
            Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_LONG).show();
        }
    }

    public void open(View view){
        words = JSONHelper.importFromJSON(this);
        if(words!=null){
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
            listView.setAdapter(adapter);
            Toast.makeText(this, "Data is open", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Failed to open data", Toast.LENGTH_LONG).show();
        }
    }
}