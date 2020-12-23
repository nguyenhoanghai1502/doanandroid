package com.example.appdongian.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdongian.R;
import com.example.appdongian.adapter.DienthoaiAdapter;
import com.example.appdongian.adapter.LaptopAdapter;
import com.example.appdongian.model.sanpham;
import com.example.appdongian.ultil.server;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaptopActivity extends AppCompatActivity {
    Toolbar toolbarlaptop;
    ListView lvlaptop;
    LaptopAdapter laptopAdapter;
    ArrayList<sanpham> manglaptop;
    int idlaptop=0;
    int page=1;
    View footerview;
    boolean limitdata=false;
    boolean isloading=false;
    MyHandler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        AnhXa();
        getidloaisanpham();
        ActionToolbar();
        getDulieu(page);
        Themdulieu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent=new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarlaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getidloaisanpham() {
        idlaptop=getIntent().getIntExtra("idloaisp",-1);
        Log.d("giatriloaisanpham",idlaptop+"");
    }

    private void getDulieu(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.duongdandienthoai+String.valueOf(Page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String tenlaptop="";
                int gialaptop=0;
                String hinhanhlaptop="";
                String motalaptop="";
                int idsplaptop=0;
                if(response!=null)
                {
                    lvlaptop.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            tenlaptop=jsonObject.getString("tensanpham");
                            gialaptop=jsonObject.getInt("giasanpham");
                            hinhanhlaptop=jsonObject.getString("hinhanhsanpham");
                            motalaptop=jsonObject.getString("motasanpham");
                            idsplaptop=jsonObject.getInt("idsanpham");
                            manglaptop.add(new sanpham(id,tenlaptop,gialaptop,hinhanhlaptop,motalaptop,idsplaptop));
                            laptopAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    limitdata=true;
                    lvlaptop.removeFooterView(footerview);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param=new HashMap<String, String>();
                param.put("idsanpham",String.valueOf(idlaptop));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Themdulieu() {
        lvlaptop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(), Chitietsanpham.class);
                intent.putExtra("thongtinsanpham", manglaptop.get(i));
                startActivity(intent);
            }
        });
        lvlaptop.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem + VisibleItem ==TotalItem && TotalItem!=0 && isloading==false && limitdata==false)
                {
                    isloading=true;
                    LaptopActivity.ThreadData threadData=new LaptopActivity.ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void AnhXa() {
        toolbarlaptop=(Toolbar) findViewById(R.id.toolbarlaptop);
        lvlaptop=(ListView) findViewById(R.id.listviewlaptop);
        manglaptop=new ArrayList<>();
        laptopAdapter=new LaptopAdapter(getApplicationContext(),manglaptop);
        lvlaptop.setAdapter(laptopAdapter);
        LayoutInflater inflater=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview=inflater.inflate(R.layout.progressbar,null);
        myHandler=new LaptopActivity.MyHandler();
    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0: {
                    lvlaptop.addFooterView(footerview);
                    break;
                }
                case 1: {
                    page++;
                    getDulieu(page);
                    isloading = false;
                    break;
                }
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread{
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Message message=myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();
        }
    }
}