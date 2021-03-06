package com.example.pdfreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {


    public static ArrayList<File> mFiles= new ArrayList<>();
     RecyclerView recyclerView;
     public static final int REQUEST_PERMISSION=1;


     String[] items;
     File folder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        permission();


    }

    private void permission() {
    if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {




    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
        Toast.makeText(this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
    }



    else{
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION);

    }
    }

    else {
        Toast.makeText(this, "Permission Granted",Toast.LENGTH_SHORT).show();


         initView();


        }


    }

    private void initView() {
    folder= new File(Environment.getExternalStorageDirectory().getAbsolutePath());
    mFiles=getPdfFiles(folder);
    ArrayList<File>  mypdf = getPdfFiles(Environment.getExternalStorageDirectory());
    items = new String[mypdf.size()];
    for (int i=0 ;i<items.length; i++){

        items[i]= mypdf.get(i).getName().replace(".pdf","");
    }


        PDFAdapter pdfAdapter= new PDFAdapter(this,mFiles,items);
        recyclerView.setAdapter(pdfAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));




    }

    private ArrayList<File> getPdfFiles(File folder){
   ArrayList<File> arrayList =new ArrayList<>();



   File[] files =folder.listFiles();




   if (files != null) {

   for ( File singleFile : files){

       if (singleFile.isDirectory() && !singleFile.isHidden())

       {
           arrayList.addAll(getPdfFiles(singleFile));
       }
       else
       {


           if(singleFile.getName().contains(".pdf"))

           {
               Log.d("TAG", "getPdfFiles: "+singleFile.getName());
               arrayList.add(singleFile);
                Collections.sort(arrayList);
           }
       }



   }


   }

   return arrayList;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_PERMISSION){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show();
                initView();
            }
            else{
                    Toast.makeText(this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
            }
        }



    }
}