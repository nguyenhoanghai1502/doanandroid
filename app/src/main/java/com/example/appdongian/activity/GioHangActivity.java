package com.example.appdongian.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appdongian.R;
import com.example.appdongian.adapter.GiohangAdapter;
import com.example.appdongian.model.GioHang;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    ListView lstgiohang;
    TextView txtthongbao;
    TextView txttongtien;
    Button btnThanhtoan, btnTieptucmuahang;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AnhXa();
        ActionToolbar();
        Kiemtragiohang();
        EventListview();
    }

    private void EventListview() {
        long tongtien=0;
        for(int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien+=MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+"VND");
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

    private void Kiemtragiohang() {
        if(MainActivity.manggiohang.size()<=0){
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            txttongtien.setVisibility(View.INVISIBLE);
            lstgiohang.setVisibility(View.INVISIBLE);
        }else{
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            txttongtien.setVisibility(View.VISIBLE);
            lstgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        lstgiohang=(ListView) findViewById(R.id.lstviewgiohang);
        txtthongbao=(TextView) findViewById(R.id.txtthongbao);
        txttongtien=(TextView) findViewById(R.id.txttongtien);
        btnThanhtoan=(Button) findViewById(R.id.btnthanhtoangiohang);
        btnTieptucmuahang=(Button) findViewById(R.id.btnquayvetrangchinh);
        toolbargiohang=(Toolbar) findViewById(R.id.toolbargiohang);
        giohangAdapter=new GiohangAdapter(GioHangActivity.this,MainActivity.manggiohang);
        lstgiohang.setAdapter(giohangAdapter);
    }
}