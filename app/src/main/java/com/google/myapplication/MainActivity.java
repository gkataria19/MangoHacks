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

import org.json.JSONArray;
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

        ArrayList<String> listOfNames=new ArrayList<String>();
        @Override
        protected String doInBackground(String... texttoSpeak) {
            //VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
            //service.setApiKey("9de1fa73a80de0509a16fddd22573505dca53a36");

//           +

//          System.out.println("Classify an image");
//            Drawable resImg = MainActivity.this.getApplicationContext().getResources().getDrawable(R.drawable.tiger);
//            InputStream ins = getResources().openRawResource(R.raw.tiger);
//            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
//            StringBuffer sb = new StringBuffer();
//            String line;
//            try {
//                while((line = br.readLine()) != null){
//                    sb.append(line);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if(new File("res/raw/tiger.png").exists()) {
//                System.out.println("tiger file exist");
//            }
//            try {
//                JSONObject obj = new JSONObject("{}");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            File f = new File(sb.toString());
//            ClassifyImagesOptions.Builder options = new ClassifyImagesOptions.Builder()
//                    //.images(new File("/DCIM/Camera/20170218_004124.jpg"))
//                    .images(new File("res/raw/tiger.png"));
//             VisualClassification result = service.classify(options.build()).execute();
//            System.out.println(result);
//            SpeechToText service = new SpeechToText();
//           String username = "018fa0dc-8935-424e-ae2f-612e03dee71d-bluemix";
//            String password ="93423400bb21081c0c45cb459109d5ba7193635bc1339f88f955a0b8ef2fde7d";
//            service.setUsernameAndPassword(username, password);
//            service.setEndPoint("https://stream.watsonplatform.net/speech-to-text/api");
//               File audio = new File("DCIM/Camera");
//               System.out.print(audio.toString());
//            System.out.print(audio.toString());
//            System.out.print(audio.toString());
//            System.out.print(audio.toString());

//
//            SpeechResults transcript = service.recognize(audio).execute();
//            System.out.println(transcript);
             // public List<ClarifaiOutput<Concept>> test() {//doInBackground(Void... params) {
            System.out.println("testing ");
                final ClarifaiClient client = new ClarifaiBuilder("5CYU-aQvcpz1g52Wk22W_cegQMpHLrumGxZ5Jogr", "wqMmIYY4xi1gMygi3FOOd4Hy66oyTCDv4EHpxe4d").buildSync();
                final List<ClarifaiOutput<Concept>> predictionResults =
                        client.getDefaultModels().generalModel() // You can also do Clarifai.getModelByID("id") to get custom models
                                .predict()
                                .withInputs(


                                        ClarifaiInput.forImage(ClarifaiImage.of("https://fsmedia.imgix.net/a2/51/c6/b0/7b6a/47f9/bbba/d4ec202a71c8/will-nightwing-replace-ben-afflecks-batman-in-the-dceu.jpeg?rect=0,0,2000,1000&w=1200&fm=png&w=1200&fm=png&q=75"))
                                )
                                .executeSync().get();

            for(ClarifaiOutput<Concept> c : predictionResults){

                List<Concept> test=c.data();
                for(int i=0;i<test.size();i++){
                    System.out.println(test.get(i).name().toString());
                    listOfNames.add(test.get(i).name().toString());

                }
            }




//
              //  return predictionResults;
            //}

//            runOnUiThread(new Runnable() {
//                @Override
//
//                public void run() {
//                    textView.setText("running the Watson thread");
//                }
//            });

           // WatsonTask obj=new WatsonTask();
            TextToSpeech textToSpeech = initTextToSpeechService();
            streamPlayer = new StreamPlayer();
            for(int j=0;j<listOfNames.size();j++){
            streamPlayer.playStream(textToSpeech.synthesize(listOfNames.get(j),Voice.EN_MICHAEL).execute());}

            return "hello";//predictionResults.toString();
        }
//        @Override
//        protected void onPostExecute(String result){
//
//            textView.setText(("TTS status: "+result));
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convertButton= (Button) findViewById(R.id.button);
//        inputText= (EditText) findViewById(R.id.editText);
//        textView=(TextView) findViewById(R.id.textView2);


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v){
                      // System.out.println("the text to speech is: "+inputText.getText());
                      // textView.setText("TTY: "+inputText.getText());

                    WatsonTask task=new WatsonTask();
                    task.execute(new String[]{});
            }
        });
    }
}
