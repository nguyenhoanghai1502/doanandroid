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

public class DienthoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<sanpham> arraydienthoai;
    public DienthoaiAdapter(Context context, ArrayList<sanpham> arraydienthoai) {
        this.context = context;
        this.arraydienthoai = arraydienthoai;
    }


    @Override
    public int getCount() {
        return arraydienthoai.size();
    }

    @Override
    public Object getItem(int i) {
        return arraydienthoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txttendienthoai,txtgiadienthoai, txtmotadienthoai;
        ImageView imgDienthoai;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_dienthoai,null);
            viewHolder.txttendienthoai=(TextView) view.findViewById(R.id.textviewtensanpham);
            viewHolder.txtgiadienthoai=(TextView) view.findViewById(R.id.textviewgiadienthoai);
            viewHolder.txtmotadienthoai=(TextView) view.findViewById(R.id.textviewmotasanpham);
            viewHolder.imgDienthoai=(ImageView) view.findViewById(R.id.imgviewanhsanpham);
            view.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) view.getTag();
        }
        sanpham Sanpham=(sanpham) getItem(i);
        viewHolder.txttendienthoai.setText(Sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiadienthoai.setText("Gia: "+decimalFormat.format(Sanpham.getGiasanpham())+"VND");
        viewHolder.txtmotadienthoai.setMaxLines(2);
        viewHolder.txtmotadienthoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotadienthoai.setText(Sanpham.motasanpham);
        Picasso.get().load(Sanpham.getHinhanhsanpham()).placeholder(R.drawable.iconapp).into(viewHolder.imgDienthoai);
        return view;
    }
}
