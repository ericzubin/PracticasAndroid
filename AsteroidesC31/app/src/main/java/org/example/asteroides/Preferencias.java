package org.example.asteroides;

import android.os.Bundle;
import android.preference.PreferenceActivity;
//Nos permite crear aguegar prefrenecias y poderlas modificarlas, creamos la vista en R.xml.settings,
//a partir de un xml nos permite trabajar desde el xml y nos olvidamos un poco de programar en java
public class Preferencias extends PreferenceActivity{
@Override
protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.settings);
}
}
