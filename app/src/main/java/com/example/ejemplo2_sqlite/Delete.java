package com.example.ejemplo2_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Delete extends AppCompatActivity{

    private ListView lv;
    private int b=0;
    private int[] ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        lv=(ListView)findViewById(R.id.consulta);

        //EJECUTANDO CONSULTA SQL (SELECCIONAR TODOS LOS ARTICULOS)
        Cursor cursor=MainActivity.DB.rawQuery("select codigo, descripcion, pecio from articulos",null);

        //CREANDO ARREGLOS PARA GUARDAR
        ArrayList<String> names=new ArrayList<String>();
      //  ids=new int[cursor.getCount()];

        //VERIFICAR SI HAY DATOS EN LA BD
        if(cursor.getCount()!=0){
            while(cursor.moveToNext()){
              //  datos[b]=(ids[b]=cursor.getInt(0))+" - "+cursor.getString(1)+" - "+cursor.getDouble(2);
                names.add(cursor.getString(1));
            }
        }else{
           Toast.makeText(this,"No hay registros",Toast.LENGTH_LONG).show();
        }
        List_items adapter=new List_items(this,R.layout.activity_list_items,names);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);

        //colocando evento a la lista
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.DB.delete("articulos","codigo="+ids[i],null);
            }
        });

    }
}
