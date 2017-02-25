package com.google.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

public class MainActivity extends AppCompatActivity {

    private Button convertButton;
    private EditText inputText;
    private TextView textView;

    StreamPlayer streamPlayer;


    private TextToSpeech initTextToSpeechService(){
        TextToSpeech service = new TextToSpeech();
        String username = "42e9bf50-41ab-4711-b85b-a87ba1b6ad8f";
        String password = "SUKtaggrTwcO";
        service.setUsernameAndPassword(username, password);
        return service;
    }

    /*  Watson service credentials

         "url": "https://stream.watsonplatform.net/text-to-speech/api",
        "username": "42e9bf50-41ab-4711-b85b-a87ba1b6ad8f",
        "password": "SUKtaggrTwcO"

    */

    private class WatsonTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... texttoSpeak) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText("running the Watson thread");
                }
            });

            TextToSpeech textToSpeech = initTextToSpeechService();
            streamPlayer = new StreamPlayer();
            streamPlayer.playStream(textToSpeech.synthesize(String.valueOf(inputText.getText()),Voice.EN_MICHAEL).execute());
            return "text to speech done";
        }
        @Override
        protected void onPostExecute(String result){
            textView.setText(("TTS status: "+result));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        convertButton= (Button) findViewById(R.id.button);
        inputText= (EditText) findViewById(R.id.editText);
        textView=(TextView) findViewById(R.id.textView2);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v){
                        System.out.println("the text to speech is: "+inputText.getText());
                        textView.setText("TTY: "+inputText.getText());

                    WatsonTask task=new WatsonTask();
                    task.execute(new String[]{});
            }
        });
    }
}
