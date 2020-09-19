package com.example.ejemplo2_sqlite;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private EditText codigo,descripcion,precio;
    String cod;
    String des;
    String pre;
    public static Database db;
    public static SQLiteDatabase DB;
    private TextView ini;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codigo=(EditText)findViewById(R.id.codigo);
        descripcion=(EditText)findViewById(R.id.descripcion);
        precio=(EditText)findViewById(R.id.precio);
        db=new Database(this,"Admin",null,1);
        DB=db.getWritableDatabase();
        ini=(TextView)findViewById(R.id.ini);
        ini.requestFocus();
    }
    public void Insert(View view){
        if(VerificarVacios(0,2)){
        ContentValues cv=new ContentValues();
        cv.put("codigo",Double.parseDouble(cod));
        cv.put("descripcion",des);
        cv.put("pecio",Double.parseDouble(pre));
        DB.insert("articulos",null,cv);
        Toast.makeText(this,"Operación exitosa",Toast.LENGTH_LONG).show();
        descripcion.setText("");
        precio.setText("");
        codigo.setText("");
        }
    }
    public void SelectCodigo(View view){
        try{
        if(VerificarVacios(0,0)){
            Cursor query=DB.rawQuery("select descripcion,pecio from articulos where codigo="+Integer.parseInt(cod),null);
            if(query.moveToFirst()){
                descripcion.setText(query.getString(0));
                precio.setText(query.getString(1));
            }else{
                Toast.makeText(this,"No hay articulos con el código",Toast.LENGTH_LONG).show();
            }
         }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ConsultaDescripcio(View view){
        if(VerificarVacios(1,1)){
            Cursor query=DB.rawQuery("select codigo, pecio from articulos where descripcion="+"'"+des+"'",null);
            if(query.moveToFirst()){
                codigo.setText(query.getString(0));
                precio.setText(query.getString(1));
            }else{
                Toast.makeText(this,"No hay articulos con la descripción",Toast.LENGTH_LONG).show();
            }
        }

    }
    public void Delete(View view){
        startActivity(new Intent(this,Delete.class));
    }
    public void Update(View view){

    }

    public boolean VerificarVacios(int desde,int hasta){
        cod=codigo.getText().toString();
        des=descripcion.getText().toString();
        pre=precio.getText().toString();
        EditText[]ets={codigo,descripcion,precio};
        for(int contador=desde; contador<=hasta; contador++){
            if (ets[contador].getText().toString().isEmpty()) {
                ets[contador].requestFocus();
                ets[contador].setError("Rellene campo");
                return false;
            }
        }
        return true;
    }

}