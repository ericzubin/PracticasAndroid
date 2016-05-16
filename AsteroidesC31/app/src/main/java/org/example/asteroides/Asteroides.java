package org.example.asteroides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//Es un menu que nos permite varias opciones como empezar a jugar,cambiar las preferencias, salir de la aplicacion o saber mas acerca de la aplicación
public class Asteroides extends Activity {
	private Button bAcercaDe;
	private Button bJugar;
	private Button bPreferencias;
	private Button bSalir;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//Nos permite porner losdiferentes listener
		bAcercaDe = (Button) findViewById(R.id.button3);
		bAcercaDe.setOnClickListener(
		new OnClickListener() {
			public void onClick(View view) {
				lanzarAcercaDe();
			}
		});

		bJugar = (Button) findViewById(R.id.button1);
		bJugar.setOnClickListener(
		new OnClickListener() {
			public void onClick(View view) {
				lanzarJugar();
			}
		});

		bPreferencias = (Button) findViewById(R.id.button2);
		bPreferencias.setOnClickListener(
		new OnClickListener() {
			public void onClick(View view) {
				lanzarPreferencias();
			}
		});

		bSalir = (Button) findViewById(R.id.button4);
		bSalir.setOnClickListener(
		new OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});

	}


//Aqui lanzamos los diferentes actividades
	public void lanzarAcercaDe() {
		Intent i = new Intent(this, AcercaDe.class);
		startActivity(i);
	}
	
	public void lanzarJugar() {
		Intent i = new Intent(this, Juego.class);
		startActivity(i);
	}

	public void lanzarPreferencias() {
		Intent i = new Intent(this, Preferencias.class);
		startActivity(i);
	}
//Nos permite crear el menu de opciones
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
//Nos permite seleccionar las opciones de acarca de y de configuraciones,
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.acercaDe:
			lanzarAcercaDe();
			break;
		case R.id.confg:
			lanzarPreferencias();
			break;
		}
		return false;
	}

	
	
}