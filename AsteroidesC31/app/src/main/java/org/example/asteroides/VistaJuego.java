package org.example.asteroides;

import java.util.Vector;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.view.View;

//Es lo que nos permite visualizar el videojuego
public class VistaJuego extends View {
// //// THREAD Y TIEMPO //////
	// Thread encargado de procesar el juego
	private ThreadJuego thread = new ThreadJuego();
	// Cada cuanto queremos procesar cambios (ms)
	private static int PERIODO_PROCESO = 50;
	// Cuando se realiz� el �ltimo proceso
	private long ultimoProceso = 0;

// //// ASTEROIDES //////
	private Vector<Grafico> Asteroides; // Vector con los Asteroides
	private int numAsteroides = 5; // N�mero inicial de asteroides
	private int numFragmentos = 3; // Fragmentos en que se divide

// //// NAVE //////
	// Gr�fico de la nave
	private Grafico nave;
	private int giroNave; // Incremento de direcci�n
	private float aceleracionNave; // aumento de velocidad
	// Incremento est�ndar de giro y aceleraci�n
	private static final int PASO_GIRO_NAVE = 5;
	private static final float PASO_ACELERACION_NAVE = 0.5f;

	//Nos el contrctor de la clase Vista Videojuego
	public VistaJuego(Context context, AttributeSet attrs) {
		super(context, attrs);
		Drawable drawableNave, drawableAsteroide, drawableMisil;
		SharedPreferences pref = context.getSharedPreferences(
				"org.example.asteroides_preferences", Context.MODE_PRIVATE);		
		if (pref.getString("graficos", "0").equals("0")) {
			//Trayectorias geométricas formadas por segmentos de línea rectos, curvas cuadráticas y curvas cúbicas para el asterodie
			Path pathAsteroide = new Path();
			pathAsteroide.moveTo((float) 0.3, (float) 0.0);
			pathAsteroide.lineTo((float) 0.6, (float) 0.0);
			pathAsteroide.lineTo((float) 0.6, (float) 0.3);
			pathAsteroide.lineTo((float) 0.8, (float) 0.2);
			pathAsteroide.lineTo((float) 1.0, (float) 0.4);
			pathAsteroide.lineTo((float) 0.8, (float) 0.6);
			pathAsteroide.lineTo((float) 0.9, (float) 0.9);
			pathAsteroide.lineTo((float) 0.8, (float) 1.0);
			pathAsteroide.lineTo((float) 0.4, (float) 1.0);
			pathAsteroide.lineTo((float) 0.0, (float) 0.6);
			pathAsteroide.lineTo((float) 0.0, (float) 0.2);
			pathAsteroide.lineTo((float)0.3, (float)0.0);
			ShapeDrawable dAsteroide = new ShapeDrawable( new PathShape(pathAsteroide ,1, 1) );
			dAsteroide.getPaint().setColor(Color.WHITE);
			dAsteroide.getPaint().setStyle(Style.STROKE);
			dAsteroide.setIntrinsicWidth(50);
			dAsteroide.setIntrinsicHeight(50);
			drawableAsteroide = dAsteroide;
			//Trayectorias geométricas formadas por segmentos de línea rectos, curvas cuadráticas y curvas cúbicas para la nave
			Path pathNave = new Path();
			pathNave.moveTo((float) 0.0, (float) 0.0);
			pathNave.lineTo((float) 1.0, (float) 0.5);
			pathNave.lineTo((float) 0.0, (float) 1.0);
			pathNave.lineTo((float) 0.0, (float) 0.0);
			ShapeDrawable dNave = new ShapeDrawable(new PathShape(pathNave, 1,1));
			dNave.getPaint().setColor(Color.WHITE);
			dNave.getPaint().setStyle(Style.STROKE);
			dNave.setIntrinsicWidth(20);
			dNave.setIntrinsicHeight(15);
			drawableNave = dNave;
			setBackgroundColor(Color.BLACK);
		} else {
			drawableAsteroide = context.getResources().getDrawable(
					R.drawable.asteroide1);
			drawableNave = context.getResources().getDrawable(R.drawable.nave);
		}
		Asteroides = new Vector<Grafico>();
		for (int i = 0; i < numAsteroides; i++) {
		//Nos permite que aparescan los asteroides de forma aleatoria en cualquier parte del mapa
			Grafico asteroide = new Grafico(this, drawableAsteroide);
			asteroide.setIncY(Math.random() * 4 - 2);
			asteroide.setIncX(Math.random() * 4 - 2);
			asteroide.setAngulo((int) (Math.random() * 360));
			asteroide.setRotacion((int) (Math.random() * 8 - 4));
			Asteroides.add(asteroide);
		}
		nave = new Grafico(this, drawableNave);
	}
//Se llama cuando se detecta que se ah cambiando el tamaño de la pantalla
	@Override
	protected void onSizeChanged(int ancho, int alto, int ancho_anter,
			int alto_anter) {
		super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);
		// Una vez que conocemos nuestro ancho y alto.
		nave.setPosX((ancho - nave.getAncho()) / 2);
		nave.setPosY((alto - nave.getAlto()) / 2);
		for (Grafico asteroide : Asteroides) {
			do {
				asteroide.setPosX(Math.random()
						* (ancho - asteroide.getAncho()));
				asteroide.setPosY(Math.random() * (alto - asteroide.getAlto()));
			} while (asteroide.distancia(nave) < (ancho + alto) / 5);
		}
		thread.start();
	}
// Nos permite aguegar o dibujar en pantallas a los asteroides y a la nave
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		nave.dibujaGrafico(canvas);
		for (Grafico asteroide : Asteroides) {
			asteroide.dibujaGrafico(canvas);
		}
	}
//Nos inicializa el hilo y nos
	class ThreadJuego extends Thread {
		@Override
		public void run() {
			while (true) {
				actualizaFisica();
			}
		}
	}
// Me parece que nos permite mover la nave y que se muevas los asteroides
	protected void actualizaFisica() {
		long ahora = System.currentTimeMillis();
		// No hagas nada si el per�odo de proceso no se ha cumplido.
		if (ultimoProceso + PERIODO_PROCESO > ahora) {
			return;
		}
		// Para una ejecuci�n en tiempo real calculamos retardo
		double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
		// Actualizamos posici�n nave
		nave.setAngulo((int) (nave.getAngulo() + giroNave * retardo));
		double nIncX = nave.getIncX() + aceleracionNave
				* Math.cos(Math.toRadians(nave.getAngulo())) * retardo;
		double nIncY = nave.getIncY() + aceleracionNave
				* Math.sin(Math.toRadians(nave.getAngulo())) * retardo;
		if (Grafico.distanciaE(0, 0, nIncX, nIncY) <= Grafico.getMaxVelocidad()) {
			nave.setIncX(nIncX);
			nave.setIncY(nIncY);
		}
		nave.incrementaPos();
		for (Grafico asteroide : Asteroides) {
			asteroide.incrementaPos();
		}
		ultimoProceso = ahora;
	}

}