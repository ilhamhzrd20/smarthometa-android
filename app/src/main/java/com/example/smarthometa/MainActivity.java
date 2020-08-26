package com.example.smarthometa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import at.markushi.ui.CircleButton;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private Socket mSocket;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private static final int REQ_CODE_SPEECH_INPUT1 = 200;
    private TextView mVoiceInputTv;
    private TextView hint;
    private ImageButton mSpeakBtn;
    public String data = "";
    public String data1 = "lu_on";
    public String data2 = "lg_on";
    public String data3 = "ka_on";
    public String data4 = "kr_on";
    CircleButton lampugarasi;
    private ProgressDialog dialog;
    public String status;
    public String status1;
    public String status2;
    public String status3;
    public String status4;
    public String message;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private ArrayList<History> historyArrayList;

    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
    String date = df.format(Calendar.getInstance().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startVoiceInput1();
    }

    private void initializeRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.5:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private void getData1(){
        UserAPIService apiService = retrofit.create(UserAPIService.class);
        Call<Device> device = apiService.getStatus1();
        device.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                dialog.dismiss();
                try {
                    status1 = response.body().getStatus();
                    final Button button = (Button) findViewById(R.id.cb_finish_watering);
                    if (status1.equals("true")) {
                        button.setBackgroundResource(R.drawable.power_button);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hint.setText("Lampu Utama Dinyalakan");
                            }
                        });
                    }
                    else if (status1.equals("false")){
                        button.setBackgroundResource(R.drawable.power_button2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hint.setText("Lampu Utama Dimatikan");
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void getData2(){
        UserAPIService apiService = retrofit.create(UserAPIService.class);
        Call<Device> device = apiService.getStatus2();
        device.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                dialog.dismiss();
                try {
                    status2 = response.body().getStatus();
                    final Button button = (Button) findViewById(R.id.cb_finish_watering2);
                    if (status2.equals("true")) {
                        button.setBackgroundResource(R.drawable.power_button);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hint.setText("Lampu Garasi Dinyalakan");
                            }
                        });
                    }
                    else if (status2.equals("false")){
                        button.setBackgroundResource(R.drawable.power_button2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hint.setText("Lampu Garasi Dimatikan");
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void getData3(){
        UserAPIService apiService = retrofit.create(UserAPIService.class);
        Call<Device> device = apiService.getStatus3();
        device.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                dialog.dismiss();
                try {
                    status3 = response.body().getStatus();
                    final Button button = (Button) findViewById(R.id.cb_finish_watering3);
                    if (status3.equals("true")) {
                        button.setBackgroundResource(R.drawable.power_button);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hint.setText("Kipas Angin Dinyalakan");
                            }
                        });
                    }
                    else if (status3.equals("false")){
                        button.setBackgroundResource(R.drawable.power_button2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hint.setText("Kipas Angin Dimatikan");
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void changeText(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hint.setText("Perintah salah!");
            }
        });
    }

    private void getData4(){
        UserAPIService apiService = retrofit.create(UserAPIService.class);
        Call<Device> device = apiService.getStatus4();
        device.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                dialog.dismiss();
                try {
                    status4 = response.body().getStatus();
                    final Button button = (Button) findViewById(R.id.cb_finish_watering4);
                    if (status4.equals("true")) {
                        button.setBackgroundResource(R.drawable.power_button);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hint.setText("Kunci Pintu Ditutup");
                            }
                        });
                    }
                    else if (status4.equals("false")){
                        button.setBackgroundResource(R.drawable.power_button2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hint.setText("Kunci Pintu Dibuka");
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void getDataAsJSON(){
        UserAPIService apiService = retrofit.create(UserAPIService.class);
        Call<ResponseBody> result = apiService.getResultAsJSON();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                try {
                    Toast.makeText(MainActivity.this," response version"+response.body().string(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    protected Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            data = (String) args[0];
            if (data.equals("tidak_cocok")) {
                changeText();
            }
        }
    };

    protected Emitter.Listener onNewMessage1 = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            data1 = (String) args[0];
            if (data1.equals("lu_off")) {
                getData1();
                String date = df.format(Calendar.getInstance().getTime());
            }
            else if (data1.equals("lu_on")){
                getData1();
                String date = df.format(Calendar.getInstance().getTime());
            }
        }
    };

    protected Emitter.Listener onNewMessage2 = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            data2 = (String) args[0];
            if (data2.equals("lg_off")) {
                getData2();
                String date = df.format(Calendar.getInstance().getTime());
            }
            else if (data2.equals("lg_on")){
                getData2();
                String date = df.format(Calendar.getInstance().getTime());
            }
        }
    };

    protected Emitter.Listener onNewMessage3 = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            data3 = (String) args[0];
            if (data3.equals("ka_off")) {
                getData3();
                String date = df.format(Calendar.getInstance().getTime());
            }
            else if (data3.equals("ka_on")){
                getData3();
                String date = df.format(Calendar.getInstance().getTime());
            }
        }
    };

    protected Emitter.Listener onNewMessage4 = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            data4 = (String) args[0];
            if (data4.equals("kr_off")) {
                getData4();
                String date = df.format(Calendar.getInstance().getTime());
            }
            else if (data4.equals("kr_on")){
                getData4();
                String date = df.format(Calendar.getInstance().getTime());
            }
        }
    };

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    private void startVoiceInput1() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT1);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                    String message = result.get(0);
                    mSocket.emit("from_mobile", message);
                    historyArrayList = new ArrayList<>();
                }
                break;
            }
            case REQ_CODE_SPEECH_INPUT1: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String message = result.get(0);
                    if (message.equals("Halo Ilham")) {
                        setContentView(R.layout.activity_main2);
                        SocketApp app = new SocketApp();
                        mSocket = app.getSocket();
                        mSocket.connect();
                        mSocket.on("to_mobile", onNewMessage);
                        mSocket.on("lu_mobile", onNewMessage1);
                        mSocket.on("lg_mobile", onNewMessage2);
                        mSocket.on("ka_mobile", onNewMessage3);
                        mSocket.on("kr_mobile", onNewMessage4);

                        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
                        hint = (TextView) findViewById(R.id.hint);
                        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
                        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                startVoiceInput();
                            }
                        });

                        dialog = new ProgressDialog(this);
                        dialog.setIndeterminate(true);
                        dialog.setMessage("Loading");

                        initializeRetrofit();
                        getData1();
                        getData2();
                        getData3();
                        getData4();
                    }
                    else {
                        super.onDestroy();
                    }
                }
                break;
            }

        }
    }

    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
    }

//    public void onBackPressed() {
//        setContentView(R.layout.activity_main);
//
//        SocketApp app = new SocketApp();
//        mSocket = app.getSocket();
//        mSocket.connect();
//        mSocket.on("to_mobile", onNewMessage);
//        mSocket.on("lu_mobile", onNewMessage1);
//        mSocket.on("lg_mobile", onNewMessage2);
//        mSocket.on("ka_mobile", onNewMessage3);
//        mSocket.on("kr_mobile", onNewMessage4);
//
//        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
//        hint = (TextView) findViewById(R.id.hint);
//        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
//        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startVoiceInput();
//            }
//        });
//
//        dialog = new ProgressDialog(this);
//        dialog.setIndeterminate(true);
//        dialog.setMessage("Loading");
//
//        initializeRetrofit();
//        getData1();
//        getData2();
//        getData3();
//        getData4();
//    }

    public void lampuUtama(View view) {
        final Button button = (Button) findViewById(R.id.cb_finish_watering);
        if (status1.equals("false")) {
            String message = "nyala lampu utama";
            mSocket.emit("from_mobile", message);
            button.setBackgroundResource(R.drawable.power_button);
        }
        else if (status1.equals("true")){
            String message = "mati lampu utama";
            mSocket.emit("from_mobile", message);
            button.setBackgroundResource(R.drawable.power_button2);
        }
    }

    public void lampuGarasi(View view) {
        final Button button = (Button) findViewById(R.id.cb_finish_watering2);
        if (status2.equals("false")) {
            String message = "nyala lampu garasi";
            mSocket.emit("from_mobile", message);
            button.setBackgroundResource(R.drawable.power_button);
        }
        else if (status2.equals("true")){
            String message = "mati lampu garasi";
            mSocket.emit("from_mobile", message);
            button.setBackgroundResource(R.drawable.power_button2);
        }
    }

    public void kipasAngin(View view) {
        final Button button = (Button) findViewById(R.id.cb_finish_watering3);
        if (status3.equals("false")) {
            String message = "nyala kipas angin";
            mSocket.emit("from_mobile", message);
            button.setBackgroundResource(R.drawable.power_button);
        }
        else if (status3.equals("true")){
            String message = "mati kipas angin";
            mSocket.emit("from_mobile", message);
            button.setBackgroundResource(R.drawable.power_button2);
        }
    }

    public void kunciPintu(View view) {
        final Button button = (Button) findViewById(R.id.cb_finish_watering4);
        if (status4.equals("false")) {
            String message = "tutup kunci pintu";
            mSocket.emit("from_mobile", message);
            button.setBackgroundResource(R.drawable.power_button);
        }
        else if (status4.equals("true")){
            String message = "buka kunci pintu";
            mSocket.emit("from_mobile", message);
            button.setBackgroundResource(R.drawable.power_button2);
        }
    }

    public void see_history(View view) {
        addMessage();
        setContentView(R.layout.history_log);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new HistoryAdapter(historyArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void addMessage() {
        UserAPIService apiService = retrofit.create(UserAPIService.class);
        final Call<List<History>> history = apiService.addMessage();
        history.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
//                historyArrayList = new ArrayList<History>();
                historyArrayList.clear();
                try {
                    List<History> responseBody = response.body();
                    historyArrayList.addAll(responseBody);
                    System.out.println("tes:"+response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }
}
