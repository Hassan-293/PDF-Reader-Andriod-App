package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;


public class pdfView extends AppCompatActivity {


    PDFView pdfView;
    int position=-1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        pdfView=findViewById(R.id.pdfview);
        position=getIntent().getIntExtra("position",-1);


         display();



    }

    private void display() {

    pdfView.fromFile(MainActivity.mFiles.get(position))
            .enableSwipe(true)
            .enableAnnotationRendering(true)
            .scrollHandle(new DefaultScrollHandle(this))
            .load();


    }
}