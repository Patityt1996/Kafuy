package com.example.josee.kafuy;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton sendButton;
    EditText editTextMsg;
    ImageView imageView;

    private ChatMessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        sendButton = findViewById(R.id.sendButton);
        editTextMsg = findViewById(R.id.editTextMsg);
        imageView = findViewById(R.id.imageView);

        adapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        listView.setAdapter(adapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMsg.getText().toString();

                if(TextUtils.isEmpty(message)){
                    Toast.makeText(MainActivity.this, "Please enter a query", Toast.LENGTH_SHORT).show();
                    return;
                }//end of if statement

                sendMessage(message);

                //to clear editText
                editTextMsg.setText("");
                listView.setSelection(adapter.getCount() - 1);
            }
        });
    }

    private void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(false, true, message);
        adapter.add(chatMessage);
    }//end of sendMessage
}
