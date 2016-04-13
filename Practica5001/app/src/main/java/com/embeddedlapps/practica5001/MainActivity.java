package com.embeddedlapps.practica5001;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MainActivity  extends Activity implements TextWatcher {
    TextView selection;
    AutoCompleteTextView edit;
    String[ ] items={"lorem", "ipsum", "dolor", "sit",
            "amet", "consectetuer", "adipiscing", "elit", "morbi",
            "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue",
            "purus"};
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        selection=(TextView)findViewById(R.id.selection);
        edit=(AutoCompleteTextView)findViewById(
                R.id.edit);
        edit.addTextChangedListener(this);
        edit.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items));
    }
    public void onTextChanged(CharSequence s, int start,
                              int before, int count) {
        selection.setText(edit.getText());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void beforeTextChanged(CharSequence s, int
            start,
                                  int count, int after) {
// needed for interface, but not used
    }
}