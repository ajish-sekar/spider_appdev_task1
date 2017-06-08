package com.example.android.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.list_item, list);
        final ListView listView = (ListView) findViewById(R.id.list);

        Button button = (Button) findViewById(R.id.add_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.note);
                String note = editText.getText().toString();
                if (note.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please type something", Toast.LENGTH_SHORT).show();
                    return;
                }
                list.add(note);
                editText.setText("");

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String currentString = list.get(position);
                        Intent intent = new Intent(getApplicationContext(),TitleActivity.class);
                        intent.putExtra("title",currentString);
                        startActivity(intent);
                    }
                })
                    ;
            }
        });

        Button delBtn = (Button) findViewById(R.id.del_btn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.note_no);
                int number=0;
                try {
                    number = Integer.parseInt(editText.getText().toString());
                    number--;
                }catch (NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(), "Please type a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    String string = list.get(number);
                    list.remove(number);
                }catch (IndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext(),"Note does not exist!!",Toast.LENGTH_SHORT).show();
                }

                listView.setAdapter(adapter);

            }
        });


    }
}
