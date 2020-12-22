package com.example.appdongian.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appdongian.R;
import com.example.appdongian.model.sanpham;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Chitietsanpham extends AppCompatActivity {
    Toolbar toolbarchitietsanpham;
    ImageView imgchitiet;
    TextView txtten, txtgia, txtmota;
    Spinner spinner;
    Button btndatmua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        AnhXa();
        ActionToolBar();
        getDuLieu();
        SuKienSpinner();
    }

    private void SuKienSpinner() {
        Integer[] soluong=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void getDuLieu() {
        int id=0;
        String tenchitiet="";
        int giachitiet=0;
        String hinhanhchitiet="";
        String motachitiet="";
        int idsanpham=0;

        sanpham sanpham= (com.example.appdongian.model.sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        tenchitiet=sanpham.getTensanpham();
        giachitiet=sanpham.getGiasanpham();
        hinhanhchitiet=sanpham.getHinhanhsanpham();
        motachitiet=sanpham.getMotasanpham();
        idsanpham=sanpham.getIdsanpham();
        txtten.setText(tenchitiet);
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txtgia.setText("Giá: "+decimalFormat.format(giachitiet)+"VNĐ");
        txtmota.setText(motachitiet);
        Picasso.get().load(hinhanhchitiet).placeholder(R.drawable.iconapp).error(R.drawable.iconapp).into(imgchitiet);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarchitietsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitietsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarchitietsanpham=(Toolbar) findViewById(R.id.toolbarchitietsanpham);
        imgchitiet=(ImageView) findViewById(R.id.imgsanpham);
        txtten=(TextView) findViewById(R.id.tensanpham);
        txtgia=(TextView) findViewById(R.id.giasanpham);
        txtmota=(TextView) findViewById(R.id.txtmotasanpham);
        spinner=(Spinner) findViewById(R.id.spiner);
        btndatmua=(Button) findViewById(R.id.btnmuahang);
    }
}