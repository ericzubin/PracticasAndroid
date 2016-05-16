package org.example.asteroides;

import android.app.Activity;
import android.os.Bundle;
//Esta clase nos permite llamar el layaout acercade.xml que nos lanza un mensaje sobre el porque se hizo este juego
public class AcercaDe extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acercade);
    }
}

