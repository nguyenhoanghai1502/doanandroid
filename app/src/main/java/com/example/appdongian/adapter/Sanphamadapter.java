package com.example.appdongian.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.appdongian.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdongian.activity.Chitietsanpham;
import com.example.appdongian.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sanphamadapter extends RecyclerView.Adapter<Sanphamadapter.ItemHolder> {
    public Sanphamadapter(Context context, ArrayList<sanpham> arrsanpham) {
        this.context = context;
        this.arrsanpham = arrsanpham;
    }

    Context context;
    ArrayList<sanpham> arrsanpham;


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        ItemHolder itemHolder=new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        sanpham sanpham=arrsanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Gia: "+decimalFormat.format(sanpham.getGiasanpham())+"VND");
        Picasso.get().load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.iconapp).error(R.drawable.ic_launcher_foreground).into(holder.imghinhanhsanpham);
    }

    @Override
    public int getItemCount() {
        return arrsanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhanhsanpham;
        public TextView txttensanpham, txtgiasanpham;
        public ItemHolder(View itemView){
            super(itemView);
            imghinhanhsanpham=(ImageView) itemView.findViewById(R.id.imgsanpham);
            txttensanpham=(TextView) itemView.findViewById(R.id.textviewtensanpham);
            txtgiasanpham=(TextView) itemView.findViewById(R.id.textviewgiasanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, Chitietsanpham.class);
                    intent.putExtra("thongtinsanpham", arrsanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
