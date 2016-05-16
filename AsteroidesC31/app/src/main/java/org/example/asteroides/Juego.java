package org.example.asteroides;

import android.app.Activity;
import android.os.Bundle;
//Esta clase nos permite llamar el layaout juego.xml que hace que inicie el juego
public class Juego extends Activity {
	@Override
	//Nos permite accer al layaut de juego que inica a su ver el java VistaJuego
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juego);
	}

}