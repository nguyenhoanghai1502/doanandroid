package com.example.appdongian.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdongian.R;
import com.example.appdongian.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter {


    Context context;
    ArrayList<sanpham> arraylaptop;
    public LaptopAdapter(Context context, ArrayList<sanpham> arraylaptop) {
        this.context = context;
        this.arraylaptop = arraylaptop;
    }


    @Override
    public int getCount() {
        return arraylaptop.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylaptop.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txttenlaptop,txtgialaptop, txtmotalaptop;
        ImageView imglaptop;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LaptopAdapter.ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new LaptopAdapter.ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_laptop,null);
            viewHolder.txttenlaptop=(TextView) view.findViewById(R.id.textviewtenlaptop);
            viewHolder.txtgialaptop=(TextView) view.findViewById(R.id.textviewgialaptop);
            viewHolder.txtmotalaptop=(TextView) view.findViewById(R.id.textviewmotalaptop);
            viewHolder.imglaptop=(ImageView) view.findViewById(R.id.imgviewanhlaptop);
            view.setTag(viewHolder);
        }
        else{
            viewHolder=(LaptopAdapter.ViewHolder) view.getTag();
        }
        sanpham Sanpham=(sanpham) getItem(i);
        viewHolder.txttenlaptop.setText(Sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgialaptop.setText("Gia: "+decimalFormat.format(Sanpham.getGiasanpham())+"VND");
        viewHolder.txtmotalaptop.setMaxLines(2);
        viewHolder.txtmotalaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotalaptop.setText(Sanpham.motasanpham);
        Picasso.get().load(Sanpham.getHinhanhsanpham()).placeholder(R.drawable.iconapp).into(viewHolder.imglaptop);
        return view;
    }
}
