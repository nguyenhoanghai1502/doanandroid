package com.example.appdongian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdongian.R;
import com.example.appdongian.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arraygiohang;
    public GiohangAdapter(Context context, ArrayList<GioHang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }
    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txtgiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btngiamsoluong, btntangsoluong, btnhienthi;
    }
    @Override
    public View getView(int position, View View, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(View==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View=inflater.inflate(R.layout.dong_giohang, null);
            viewHolder.txtgiohang=View.findViewById(R.id.txttenmonhang);
            viewHolder.txtgiagiohang=View.findViewById(R.id.txtgiamonhang);
            viewHolder.btngiamsoluong=View.findViewById(R.id.btngiamsoluong);
            viewHolder.imggiohang=View.findViewById(R.id.imghinhanhsp);
            viewHolder.btnhienthi=View.findViewById(R.id.btnhienthi);
            viewHolder.btntangsoluong=View.findViewById(R.id.btntangsoluong);
            View.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) View.getTag();
        }
        GioHang gioHang=(GioHang) getItem(position);
        viewHolder.txtgiohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(gioHang.getGiasp())+"VND");
        Picasso.get().load(gioHang.getHinhanhsp()).placeholder(R.drawable.iconapp).into(viewHolder.imggiohang);
        viewHolder.btnhienthi.setText(gioHang.getSoluongsp()+"");
        return View;
    }
}
