package com.example.appdongian.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdongian.R;
import com.example.appdongian.model.Loaisp;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Loaispadapter extends BaseAdapter
{
    ArrayList<Loaisp> arrlistloaisp;

    public Loaispadapter(ArrayList<Loaisp> arrlistloaisp, Context context) {
        this.arrlistloaisp = arrlistloaisp;
        this.context = context;
    }

    Context context;

    @Override
    public int getCount() {
        return arrlistloaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrlistloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txtTenloaisp;
        ImageView imgLoaisp;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.donglistviewloaisp,null);
            viewHolder.txtTenloaisp=(TextView) view.findViewById(R.id.textviewloaisp);
            viewHolder.imgLoaisp=(ImageView) view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }else
        {
            viewHolder =(ViewHolder) view.getTag();
        }
        Loaisp loaisp=(Loaisp) getItem(i);
        viewHolder.txtTenloaisp.setText(loaisp.getTenloaisp());
        Picasso.get().load(loaisp.getHinhanhsp()).placeholder(R.drawable.menuicon).error(R.drawable.ic_launcher_foreground).into(viewHolder.imgLoaisp);
        return view;
    }

}
