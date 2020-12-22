package com.example.appdongian.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdongian.R;
import com.example.appdongian.adapter.DienthoaiAdapter;
import com.example.appdongian.model.sanpham;
import com.example.appdongian.ultil.server;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class  DienthoaiActivity extends AppCompatActivity {
    Toolbar toolbardt;
    ListView lstviewdt;
    DienthoaiAdapter dienthoaiAdapter;
    ArrayList<sanpham> mangdt;
    int iddt=0;
    int page=1;
    boolean isloading=false;
    boolean limitdata=false;
    View footerview;
    MyHandler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dienthoai);
        Anhxa();
        getidloaisanpham();
        ActionToolbar();
        //hien thi san pham
        getDulieu(page);
        //hien thi them san pham khi keo xuong
        Themdulieu();
    }

    private void Themdulieu() {
        lstviewdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(), Chitietsanpham.class);
                intent.putExtra("thongtinsanpham", mangdt.get(i));
                startActivity(intent);
            }
        });
        lstviewdt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem + VisibleItem ==TotalItem && TotalItem!=0 && isloading==false && limitdata==false)
                {
                    isloading=true;
                    ThreadData threadData=new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void getDulieu(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.duongdandienthoai+String.valueOf(Page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String tendt="";
                int giadt=0;
                String hinhanhdt="";
                String motadt="";
                int idspdt=0;
                if(response!=null)
                {
                    lstviewdt.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            tendt=jsonObject.getString("tensanpham");
                            giadt=jsonObject.getInt("giasanpham");
                            hinhanhdt=jsonObject.getString("hinhanhsanpham");
                            motadt=jsonObject.getString("motasanpham");
                            idspdt=jsonObject.getInt("idsanpham");
                            mangdt.add(new sanpham(id,tendt,giadt,hinhanhdt,motadt,idspdt));
                            dienthoaiAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    limitdata=true;
                    lstviewdt.removeFooterView(footerview);
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
                param.put("idsanpham",String.valueOf(iddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbardt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getidloaisanpham() {
        iddt=getIntent().getIntExtra("idloaisp",-1);
        Log.d("giatriloaisanpham",iddt+"");
    }

    private void Anhxa() {
        toolbardt=(Toolbar) findViewById(R.id.toolbardienthoai);
        lstviewdt=(ListView) findViewById(R.id.listviewdienthoai);
        mangdt=new ArrayList<>();
        dienthoaiAdapter=new DienthoaiAdapter(getApplicationContext(),mangdt);
        lstviewdt.setAdapter(dienthoaiAdapter);
        LayoutInflater inflater=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview=inflater.inflate(R.layout.progressbar,null);
        myHandler=new MyHandler();

    }
      public class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0: {
                    lstviewdt.addFooterView(footerview);
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