package com.example.shiz.fetchimagerxretro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import java.util.List;
import android.content.Intent;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Observer;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;
import rx.Subscriber;
import rx.Observable;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    String responseString;
    List<Worldpopulation> List;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        loadJson();
    }

        public void loadJson() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.androidbegin.com/tutorial/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            APInterface apiService = retrofit.create(APInterface.class);
            apiService.getFile("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>()
                    {
                        @Override
                        public void onCompleted() {
                            Log.d("COMPLETE", "COMPLETED TRANSFER");

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("ERROR", e.getMessage());
                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            try {
                                responseString = responseBody.string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Log.d("SHIZ", responseString);


                            try {
                                jsonObject = new JSONObject(responseString);
                                Log.d("SHIZ", jsonObject.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            List = new ArrayList<>();
                            //Getting out the world population array
                            JSONArray jsonArray = jsonObject.optJSONArray("worldpopulation");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Worldpopulation world = new Worldpopulation();
                                JSONObject jsonobject = null;
                                try {
                                    jsonobject = jsonArray.getJSONObject(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    String rank = jsonobject.getString("rank");
                                    world.setRank(Integer.valueOf(rank));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    String country = jsonobject.getString("country");
                                    world.setCountry(country);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    String population = jsonobject.getString("population");
                                    Long l = Long.parseLong(population.replaceAll(",", ""));
                                    world.setPopulation(l);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    String flag = jsonobject.getString("flag");
                                    world.setFlag(flag);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //adding data to List
                                List.add(world);
                                Log.d("COUNTRY MODEL", List.toString());

                            }

                            Adapter adapter = new Adapter(List);
                            RecyclerView.LayoutManager layout = new LinearLayoutManager(MainActivity.this);
                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mRecyclerView.setLayoutManager(layout);
                            mRecyclerView.setAdapter(adapter);

                        }
                    });
        }

    }



