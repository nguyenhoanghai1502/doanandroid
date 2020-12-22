package com.example.appdongian.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdongian.R;
import com.example.appdongian.adapter.Loaispadapter;
import com.example.appdongian.adapter.Sanphamadapter;
import com.example.appdongian.model.Loaisp;
import com.example.appdongian.ultil.server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.appdongian.model.sanpham;
import java.util.ArrayList;

import kotlin.text.UStringsKt;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    ArrayList<sanpham> mangsanpham;
    Sanphamadapter sanphamadapter;
    Loaispadapter loaispadapter;
    int id=0;
    String TenLoaiSanPham="";
    String HinhAnhLoaiDuLieu="";
    int ID = 0;
    String tensanpham = "";
    int giasanpham = 0;
    String hinhanhsanpham = "";
    String motasanpham = "";
    int idsanpham = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        ActionBar();
        //vẽ slide show
        ActionViewFlipper();
        //vẽ dữ liệu trong thanh menu
        Getdulieusanpham();
        //vẽ dữ liệu trong bảng recycleview
        Getdulieumoinhat();
        //sự kiện khi click vào một loại sản phẩm trong menu
        SukienItemlistview();
    }

    private void SukienItemlistview() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0: {
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 1: {
                        Intent intent = new Intent(MainActivity.this, DienthoaiActivity.class);
                        intent.putExtra("idloaisp", mangloaisp.get(i-1).getId());
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 2: {
                        Intent intent = new Intent(MainActivity.this, LaptopActivity.class);
                        intent.putExtra("idloaisp", mangloaisp.get(i-1).getId());
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 3: {
                        Intent intent = new Intent(MainActivity.this, LienheActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 4: {
                        Intent intent = new Intent(MainActivity.this, ThongtinActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                }
            }
        });
    }

    // lam slide show
    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao=new ArrayList<>();
        mangquangcao.add("https://cdn.tgdd.vn/2020/12/banner/800-300-800x300-10.png");
        mangquangcao.add("https://cdn.tgdd.vn/2020/12/banner/800-300-800x300-11.png");
        mangquangcao.add("https://cdn.tgdd.vn/2020/12/banner/800-300(1)-800x300.png");
        mangquangcao.add("https://cdn.tgdd.vn/2020/12/banner/800-300-800x300-8.png");
        mangquangcao.add("https://cdn.tgdd.vn/2020/12/banner/800-300-800x300-13.png");
        for(int i=0;i<mangquangcao.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    //bang ve san pham moi nhat
    private void Getdulieumoinhat(){
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(server.duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            tensanpham = jsonObject.getString("tensanpham");
                            giasanpham = jsonObject.getInt("giasanpham");
                            hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                            motasanpham = jsonObject.getString("motasanpham");
                            idsanpham = jsonObject.getInt("idsanpham");
                            mangsanpham.add(new sanpham(id, tensanpham, giasanpham, hinhanhsanpham, motasanpham, idsanpham));
                            sanphamadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menuicon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void Anhxa() {
        toolbar= (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper=(ViewFlipper) findViewById(R.id.viewflipper);
        recyclerViewmanhinhchinh=(RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        listViewmanhinhchinh=(ListView) findViewById(R.id.listview);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawerlayout);
        mangloaisp=new ArrayList<>();
//        bang ve menu
        loaispadapter=new Loaispadapter(mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispadapter);

        //bang ve recycleview
        mangsanpham=new ArrayList<>();
        sanphamadapter=new Sanphamadapter(getApplicationContext(),mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(sanphamadapter);
    }
    //Lay du lieu tu json cho thanh menu
    private void Getdulieusanpham(){
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(server.duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    for(int i=0;i<response.length();i++)
                    {
                        JSONObject jsonObject= null;
                        try {
                            jsonObject = response.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            TenLoaiSanPham=jsonObject.getString("TenLoaiSanPham");
                            HinhAnhLoaiDuLieu=jsonObject.getString("HinhAnhLoaiDuLieu");
                            mangloaisp.add(new Loaisp(id,TenLoaiSanPham,HinhAnhLoaiDuLieu));
                            loaispadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}