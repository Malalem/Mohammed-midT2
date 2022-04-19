package com.example.mohammed_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    // we"ll make HTTP request to this URL to retrieve weather conditions
    ImageView weatherBackground;
    // Textview to show temperature and description
    TextView temperature, description, humidity, pressuree, sunRise, sunSet;
    //Spinner
    Spinner city;
    //Button
    Button confirm ,bttnSecondAct;

    // JSON object that contains weather information
    JSONObject jsonObj;

    String url ="http://api.openweathermap.org/data/2.5/weather?q=London&appid=a21f3cf1db6a937fea438e2feccb64a6&units=metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(MainActivity.this,"Welcome to the project of MohammedAlAlem,200230",Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);
        temperature = (TextView) findViewById(R.id.temperature);
        description = (TextView) findViewById(R.id.description);
        humidity = (TextView) findViewById(R.id.humidity);
        pressuree = (TextView) findViewById(R.id.pressure);
        sunRise = findViewById(R.id.Sunrise);
        sunSet = findViewById(R.id.sunset);
        city = findViewById(R.id.cities);
        confirm = findViewById(R.id.button);
        bttnSecondAct = findViewById(R.id.bttnSecondAct);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String press;
                press = city.getSelectedItem().toString();

                switch(press){
                    case "Riyadh":
                        url = "http://api.openweathermap.org/data/2.5/weather?q=Riyadh&appid=a21f3cf1db6a937fea438e2feccb64a6&units=metric";
                        weather(url);
                        break;
                    case "Jeddah":
                        url = "http://api.openweathermap.org/data/2.5/weather?q=Jeddah&appid=a21f3cf1db6a937fea438e2feccb64a6&units=metric";
                        weather(url);
                        break;
                    case "London":
                        url = "http://api.openweathermap.org/data/2.5/weather?q=London&appid=a21f3cf1db6a937fea438e2feccb64a6&units=metric";
                        weather(url);
                        break;
                    case "Texas":
                        url = "http://api.openweathermap.org/data/2.5/weather?q=Texas&appid=a21f3cf1db6a937fea438e2feccb64a6&units=metric";
                        weather(url);
                        break;
                }

            }
        });
        bttnSecondAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

        weatherBackground = (ImageView) findViewById(R.id.weatherbackground);
        weather(url);



    }

    public void weather(String url){
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Mohammed","Response received");
                        Log.d("Mohammed",response.toString());
                        try {
                            JSONObject jsonMain = response.getJSONObject("main");
                            JSONObject jsonSys = response.getJSONObject("sys");

                            double temp = jsonMain.getDouble("temp");
                            Log.d("Mohammed","temp=" + String.valueOf(temp));
                            temperature.setText(String.valueOf(temp));

                            double humid = jsonMain.getDouble("humidity");
                            Log.d("Mohammed","humidity="+String.valueOf(humid));
                            humidity.setText(String.valueOf(humid));

                            double pressure = jsonMain.getDouble("pressure");
                            Log.d("Mohammed","pressure="+String.valueOf(pressure));
                            pressuree.setText(String.valueOf(pressure));

                            String townResponse = response.getString("name");
                            description.setText(townResponse);


                            long sunrise = jsonSys.getLong("sunrise");
                            long sunset = jsonSys.getLong("sunset");

                            Date sunriseDate = new Date(1000*sunrise);
                            String sunriseTime = new SimpleDateFormat("H:mm").format(sunriseDate);
                            Log.d("Mohammed","rise= "+sunrise);
                            sunRise.setText(String.valueOf(sunrise));
                            Date sunsetDate = new Date(1000*sunset);
                            String sunsetTime = new SimpleDateFormat("H:mm").format(sunsetDate);
                            Log.d("Mohammed","set= "+sunset);
                            sunSet.setText(String.valueOf(sunset));

                            JSONArray jasonArray = response.getJSONArray("weather");
                            for (int i=0; i<jasonArray.length();i++){
                                JSONObject oneObject = jasonArray.getJSONObject(i);
                                String weather = oneObject.getString("main");
                                Log.d("Mohammed-w",weather);
                                if (weather.equals("Clear")){
                                    Glide.with(MainActivity.this).load("https://i.picsum.photos/id/866/536/354.jpg?hmac=tGofDTV7tl2rprappPzKFiZ9vDh5MKj39oa2D--gqhA").into(weatherBackground);
                                }
                                else if (weather.equals("Cloudy")){
                                    Glide.with(MainActivity.this).load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fphotos%2Fcloudy&psig=AOvVaw2xPgAQsVtH80Yoifi5_bFw&ust=1649199090961000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCIDqq8K_-_YCFQAAAAAdAAAAABAD").into(weatherBackground);
                                }
                                else if (weather.equals("Rainy")){
                                    Glide.with(MainActivity.this).load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.123rf.com%2Fphoto_59820038_blurred-rain-drop-on-the-car-glass-background.html&psig=AOvVaw1FsCRThbxjM7wxB8VFsP03&ust=1649199112391000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCMDam9C_-_YCFQAAAAAdAAAAABAY").into(weatherBackground);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("JSON Error",e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Mohammed","Error retrieving URL");

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);


    }
}