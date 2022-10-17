  package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysplash.json.MyInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

  public class Olvide extends AppCompatActivity {
      public static List<MyInfo> list;
      public static String json = null;
      public static String TAG = "mensaje";
      public static String cadena= null;
      TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide);
        textView1 = findViewById(R.id.textView16);
        Read();
        json2List(json);
        encuentra();
    }
      private File getFile( )
      {
          return new File( getDataDir() , Registro.archivo);
      }
      private boolean isFileExits( )
      {
          File file = getFile( );
          if( file == null )
          {
              return false;
          }
          return file.isFile() && file.exists();
      }
      public void json2List( String json )
      {
          Gson gson = null;
          String mensaje = null;
          if (json == null || json.length() == 0)
          {

              Toast.makeText(getApplicationContext(), "Error, no hay informacion aun", Toast.LENGTH_LONG).show();
              return;
          }
          gson = new Gson();
          Type listType = new TypeToken<ArrayList<MyInfo>>(){}.getType();
          list = gson.fromJson(json, listType);
          if (list == null || list.size() == 0 )
          {
              Toast.makeText(getApplicationContext(), "Error, aun no hay datos guardados", Toast.LENGTH_LONG).show();
              return;
          }
      }
      public boolean Read(){
          if(!isFileExits()){
              return false;
          }
          File file = getFile();
          FileInputStream fileInputStream = null;
          byte[] bytes = null;
          bytes = new byte[(int)file.length()];
          try {
              fileInputStream = new FileInputStream(file);
              fileInputStream.read(bytes);
              json=new String(bytes);
              Log.d(TAG,json);
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
          return false;
      }
      public void encuentra(){
        for(MyInfo info: list){
            if(activity_login.usr.equals(info.getUsuario())){
                textView1.setText("El usuario si esta registrado, intente recordar su contraseña");
            }else{
                textView1.setText("El usuario no existe");
            }
        }
      }
}