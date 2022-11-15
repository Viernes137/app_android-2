package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mysplash.json.MyInfo;

import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {

    private ListView listView;
    private List<String> list;;

    String aux = null;
    public static MyInfo info = null;
    TextView textView;
    Object object = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = findViewById(R.id.PrincipalText);
        Intent intent = getIntent();
        if( intent != null)
        {
            aux = intent.getStringExtra("Usuario" );
            if( aux != null && aux.length()> 0 )
            {
                textView.setText(aux);
            }
            if( intent.getExtras() != null ) {
                object = intent.getExtras().get("Info");
                if (object != null) {
                    if (object instanceof MyInfo) {
                        info = (MyInfo) object;
                        textView.setText( info.getUsuario());
                    }
                }
            }
        }
        listView = (ListView) findViewById(R.id.listViewId);
        list = new ArrayList<String>();
        for( int i = 0; i < 100; i++)
        {
            list.add( String.format( "Contraseña %d" , i + 1 ) );
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_listas,R.id.EditTextContra, list );
        listView.setAdapter(arrayAdapter);
    }
}