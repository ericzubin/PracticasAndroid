package com.uaq.globalcomunication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements TextWatcher {
    AutoCompleteTextView text;
    TextView selection;
    TextView significado;

    String[ ] Español_Ingles={"Técnica: Technique.", "Ciencia: Science.", "Arte: Art."};
    String[ ] English_Spanish={"Technique: Técnica.", "Science: Ciencia.", "Art: Arte."};
    String[ ] English_French={"Technique: technique.", "Science. science.", "Art. art"};
    String[ ] English_Spanish_significado={" Que conoce muy bien los\n" +
            "procedimientos de una ciencia, un arte o un\n" +
            "oficio y los lleva a la práctica", "Conocimiento ordenado y\n" +
            "generalmente experimentado de las cosas.", " Virtud para realizar una actividad."};
    String[ ] Español_Ingles_significado={"Someone who knows\n" +
            "very well the procedures of a science, an art or\n" +
            "a craft and implements.", "Generally orderly and\n" +
            "experienced knowledge of things.", "Virtue for an activity."};
    String[ ] English_French_significado={"Quelqu'un qui connaît\n" +
            "très bien les procédures d'une science, un art ou\n" +
            "un métier et des outils.", "Connaissances\n" +
            "généralement structuré et expérimenté des\n" +
            "choses.", "Vertu d'une activité."};
    Integer Idioma=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selection=(TextView)findViewById(R.id.textView);
        significado=(TextView)findViewById(R.id.txtSignificado);
        Idioma=1;
        text=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        text.addTextChangedListener(this);
        text.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Español_Ingles));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.EF) {
            text.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, English_French));
            Idioma=0;
            return true;
        }else if(id== R.id.EI)
        {
            text.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, Español_Ingles));
            Idioma=1;
            return true;
        }else if (id == R.id.IE)
        {
            text.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, English_Spanish));
            Idioma=2;

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        selection.setText(text.getText());
        if(Idioma ==0)
        {
            try
{
    int Posicion= busquedaSecuencial(English_French,selection.getText().toString());
    significado.setText(English_French_significado[Posicion]);

}catch (Exception e)
{

}
        }else if(Idioma == 1)
        {

            try
            {
                int Posicion= busquedaSecuencial(Español_Ingles,selection.getText().toString());

                significado.setText(Español_Ingles_significado[Posicion]);

            }catch (Exception e)
            {

            }

        }else if(Idioma == 2)
        {

            try
            {
                int Posicion= busquedaSecuencial(English_Spanish,selection.getText().toString());
                significado.setText(English_Spanish_significado[Posicion]);

            }catch (Exception e)
            {

            }

        }

    }

    public int busquedaSecuencial(String []arreglo,String dato) {
        int posicion = -1;
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i].equals(dato)) {
                posicion = i;
                break;
            }
        }
        return posicion;

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
