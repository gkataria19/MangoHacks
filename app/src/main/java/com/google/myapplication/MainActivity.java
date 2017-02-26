package com.google.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
//import com.ibm.watson.developer_cloud.android.library.audio.CameraHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

public class MainActivity extends AppCompatActivity {

    private Button convertButton;
    private EditText inputText;
    private TextView textView;
    private ImageView image1;

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
            VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
            service.setApiKey("9de1fa73a80de0509a16fddd22573505dca53a36");


                final ClarifaiClient client = new ClarifaiBuilder("5CYU-aQvcpz1g52Wk22W_cegQMpHLrumGxZ5Jogr", "wqMmIYY4xi1gMygi3FOOd4Hy66oyTCDv4EHpxe4d").buildSync();
                final List<ClarifaiOutput<Concept>> predictionResults =
                        client.getDefaultModels().generalModel() // You can also do Clarifai.getModelByID("id") to get custom models
                                .predict()
                                .withInputs(


                                        ClarifaiInput.forImage(ClarifaiImage.of("https://samples.clarifai.com/metro-north.jpg"))
                                )
                                .executeSync().get();
            for(ClarifaiOutput<Concept> conceptClarifaiOutput: predictionResults) {
                System.out.print(conceptClarifaiOutput.data());
//
            }
//
              //  return predictionResults;
            //}
//
//
//
//
//
//
//            runOnUiThread(new Runnable() {
//                @Override
//
//                public void run() {
//                    textView.setText("running the Watson thread");
//                }
//            });
//
//
//            TextToSpeech textToSpeech = initTextToSpeechService();
//            streamPlayer = new StreamPlayer();
//            streamPlayer.playStream(textToSpeech.synthesize(String.valueOf(inputText.getText()),Voice.EN_MICHAEL).execute());

            return "helkeode";//predictionResults.toString();
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
                      // System.out.println("the text to speech is: "+inputText.getText());
                       textView.setText("TTY: "+inputText.getText());

                    WatsonTask task=new WatsonTask();
                    task.execute(new String[]{});
            }
        });
    }
}
