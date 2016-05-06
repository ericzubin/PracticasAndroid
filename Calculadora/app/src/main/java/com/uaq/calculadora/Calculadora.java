package com.uaq.calculadora;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Calculadora extends Activity implements OnClickListener, android.view.View.OnClickListener {

    TextView myTextView, myTextView2;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        
        View v1=findViewById(R.id.myTextView);
        myTextView=(TextView) v1;
        myTextView.setText("");
        
        View v2=findViewById(R.id.myTextView2);
        myTextView2=(TextView) v2;
        myTextView2.setText("");
        
        View boton1=findViewById(R.id.button1);
        boton1.setOnClickListener(this);
        View boton2=findViewById(R.id.button2);
        boton2.setOnClickListener(this);
        View boton3=findViewById(R.id.button3);
        boton3.setOnClickListener(this);
        View boton4=findViewById(R.id.button4);
        boton4.setOnClickListener(this);
        View boton5=findViewById(R.id.button5);
        boton5.setOnClickListener(this);
        View boton6=findViewById(R.id.button6);
		boton6.setOnClickListener(this);
        View boton7=findViewById(R.id.button7);
        boton7.setOnClickListener(this);
        View boton8=findViewById(R.id.button8);
        boton8.setOnClickListener(this);
        View boton9=findViewById(R.id.button9);
        boton9.setOnClickListener(this);
        View boton0=findViewById(R.id.button0);
        boton0.setOnClickListener(this);
        View botonSuma=findViewById(R.id.buttonSuma);
        botonSuma.setOnClickListener(this);
        View botonResta=findViewById(R.id.buttonResta);
        botonResta.setOnClickListener(this);
        View botonMultiplica=findViewById(R.id.buttonMultiplica);
        botonMultiplica.setOnClickListener(this);
        View botonDivide=findViewById(R.id.buttonDivide);
        botonDivide.setOnClickListener(this);
        View botonPunto=findViewById(R.id.buttonPunto);
        botonPunto.setOnClickListener(this);
        View botonIgual=findViewById(R.id.buttonIgual);
        botonIgual.setOnClickListener(this);
        View botonBorra=findViewById(R.id.buttonBorra);
        botonBorra.setOnClickListener(this);
		}//OnCreate Method
	
		double result, m1=0, m2=0;
		char op1='+';
		
		public void onClick(View v){
			if(v.getId()==R.id.button1)
				myTextView.append("1");
			else if(v.getId()==R.id.button2)
				myTextView.append("2");
			else if(v.getId()==R.id.button3)
				myTextView.append("3");
			else if(v.getId()==R.id.button4)
				myTextView.append("4");
			else if(v.getId()==R.id.button5)
				myTextView.append("5");
			else if(v.getId()==R.id.button6)
				myTextView.append("6");
			else if(v.getId()==R.id.button7)
				myTextView.append("7");
			else if(v.getId()==R.id.button8)
				myTextView.append("8");
			else if(v.getId()==R.id.button9)
				myTextView.append("9");
			else if(v.getId()==R.id.button0)
				myTextView.append("0");
			else if(v.getId()==R.id.buttonPunto)
				myTextView.append(".");
			else if(v.getId()==R.id.buttonBorra){
				myTextView.setText("");
				myTextView2.setText("");
				m1=0;
				op1='+';
				
			}//else if borra
			
			else if(v.getId()==R.id.buttonSuma) calcula('+');
			else if(v.getId()==R.id.buttonResta) calcula('-');
			else if(v.getId()==R.id.buttonMultiplica) calcula('*');
			else if(v.getId()==R.id.buttonDivide) calcula('/');
			else if(v.getId()==R.id.buttonIgual) calcula('=');
		
		}//onClick
		
		public void calcula(char op){
			double result=m1;
			String cadena=myTextView.getText().toString();
			try{
				m2=Double.parseDouble(cadena);
				if(op1=='+') result=m1+m2;
				else if(op1=='-') result=m1-m2;
				else if(op1=='*') result=m1*m2;
				else if(op1=='/') result=m1/m2;
				m1=result;
				op1=op;
				if(op== '='){
					myTextView.setText(""+m1);
					myTextView2.setText(""+m1);
					
				}else{
					myTextView.setText("");
					myTextView2.setText(""+m1+op1);
				}//else
				
				
				
			}catch(NumberFormatException nfe){
				Toast.makeText(this, "numero incorrecto", Toast.LENGTH_SHORT).show();
			}//catch
			
		}//calcula

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
	
	


}//Class Activity
        
        