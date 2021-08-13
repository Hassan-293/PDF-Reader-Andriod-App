package com.example.pdfreader;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<File> mFiles;
            String[] items;



    public PDFAdapter(Context mContext, ArrayList<File> mFiles, String[] items){

        this.mContext=mContext;
        this.mFiles=mFiles;
        this.items=items;

    }






    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(mContext).inflate(R.layout.items,parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


    holder.file_name.setText(items[position]);
    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, pdfView.class);

            intent.putExtra("position",position);
            mContext.startActivity(intent);


        }
    });


    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView  file_name;
        ImageView img_icon;
        CardView  cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            file_name=itemView.findViewById(R.id.pdf_files);
            img_icon=itemView.findViewById(R.id.image_pdf);
            cardView= itemView.findViewById(R.id.item);

        }
    }




}
