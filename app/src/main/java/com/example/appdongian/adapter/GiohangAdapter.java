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
import com.example.appdongian.activity.MainActivity;
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
    public View getView(final int position, View View, ViewGroup parent) {
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
        int sl = Integer.parseInt(viewHolder.btnhienthi.getText().toString());
        if (sl >= 10) {
            viewHolder.btntangsoluong.setVisibility(View.INVISIBLE);
            viewHolder.btngiamsoluong.setVisibility(View.VISIBLE);
        }else if (sl <= 1){
            viewHolder.btngiamsoluong.setVisibility(View.INVISIBLE);
        }else if (sl >= 1){
            viewHolder.btntangsoluong.setVisibility(View.VISIBLE);
            viewHolder.btngiamsoluong.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btntangsoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi = Integer.parseInt(finalViewHolder.btnhienthi.getText().toString()) +1;
                int slht = MainActivity.manggiohang.get(position).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoi);
                long giamoi = (giaht * slmoi) / slht;
                MainActivity.manggiohang.get(position).setGiasp((int)giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText("Giá: " + decimalFormat.format(giamoi) + " vnđ");
                com.example.appdongian.activity.GioHangActivity.EventListview();
                if (slmoi > 9){
                    finalViewHolder.btntangsoluong.setVisibility(android.view.View.INVISIBLE);
                    finalViewHolder.btngiamsoluong.setVisibility(android.view.View.VISIBLE);
                    finalViewHolder.btnhienthi.setText(String.valueOf(slmoi));
                }else
                {
                    finalViewHolder.btntangsoluong.setVisibility(android.view.View.VISIBLE);
                    finalViewHolder.btngiamsoluong.setVisibility(android.view.View.VISIBLE);
                    finalViewHolder.btnhienthi.setText(String.valueOf(slmoi));
                }
            }
        });
        viewHolder.btngiamsoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi = Integer.parseInt(finalViewHolder.btnhienthi.getText().toString()) -1;
                int slht = MainActivity.manggiohang.get(position).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoi);
                long giamoi = (giaht * slmoi) / slht;
                MainActivity.manggiohang.get(position).setGiasp((int)giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText("Giá: " + decimalFormat.format(giamoi) + " vnđ");
                com.example.appdongian.activity.GioHangActivity.EventListview();
                if (slmoi < 2 ){
                    finalViewHolder.btntangsoluong.setVisibility(android.view.View.VISIBLE);
                    finalViewHolder.btngiamsoluong.setVisibility(android.view.View.INVISIBLE);
                    finalViewHolder.btnhienthi.setText(String.valueOf(slmoi));
                }else
                {
                    finalViewHolder.btntangsoluong.setVisibility(android.view.View.VISIBLE);
                    finalViewHolder.btngiamsoluong.setVisibility(android.view.View.VISIBLE);
                    finalViewHolder.btnhienthi.setText(String.valueOf(slmoi));
                }
            }
        });
        return View;
    }
}
