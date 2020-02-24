package com.og.prj.ui.readrest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.og.prj.R;
import com.og.prj.adapter.RecyclerViewAdapter;
import com.og.prj.product.Product;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ReadRest extends AppCompatActivity {
    //private static ArrayList<ArrayList<String>> productInfoWrapper;
    private static ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_rest);

        Button button = findViewById(R.id.restButton);
       // final TextView textView = findViewById(R.id.restTextview);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTask asyncTask = new AsyncTask() {
                    ArrayList<ArrayList<String>> productInfoWrapperAsync = new ArrayList<>();
                    ArrayList<Product> productArrayListAsync = new ArrayList<>();
                    String dataJson;
                    ArrayList<String> productNameList = new ArrayList<>();
                    ArrayList<String> productPictureUrlList = new ArrayList<>();

                    @Override
                    protected Object doInBackground(Object[] objects) {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://10.0.2.2:5000/getjson").build();
                        Response response = null;
                        try {
                              response = client.newCall(request).execute();

                          /*  URL url = new URL("http://10.0.2.2:5000/getjson/336");
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            String line = "";
                            while (line != null) {
                                line = bufferedReader.readLine();
                                dataJson = dataJson + line;
                            }
*/
                            JSONArray jsonArray = new JSONArray(response.body().string());
                            for(int i =0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                                productArrayListAsync.add(new Product(jsonObject.getLong("productId"),
                                        jsonObject.getString("productName"),
                                        jsonObject.getDouble("price"),
                                        "http://10.0.2.2:5000/getimageforjson/" + jsonObject.getLong("productId")));

                                System.out.println(jsonObject.toString());
                            //    productNameList.add(jsonObject.getString("productName"));
                             //   productPictureUrlList.add(  "http://10.0.2.2:5000/getimageforjson/" + jsonObject.getLong("productId"));
                            }

                           // productInfoWrapperAsync.add(0,productNameList);
                          //  productInfoWrapperAsync.add(1,productPictureUrlList);
                            return  "success";

                           // return response.body().string();

                        } catch (MalformedURLException e) {

                            System.out.println(e);
                        } catch (IOException e) {

                            System.out.println(e);
                        } catch (JSONException e) {
                            System.out.println(e);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        if(o != null) {
                         //   textView.setText(o.toString());
                           // initRecyclerView(productNameList,productPictureUrlList);
                            System.out.println(o.toString());
                           // productInfoWrapper = productInfoWrapperAsync;
                            productArrayList = productArrayListAsync;
                            initRecyclerView();
                        } else {
                           // textView.setText("can't connect");
                            System.out.println("Error!!");
                        }
                    }
                }.execute();

            }
        });


    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(productArrayList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
