package com.embeddedlapps.menupractica;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {
    TextView selection;
    ListView lista;
    String[] items = {"lorem", "ipsum", "dolor", "sit",
            "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue",
            "purus"};
    public static final int EIGHT_ID = Menu.FIRST + 1;
    public static final int SIXTEEN_ID = Menu.FIRST + 2;
    public static final int TWENTY_FOUR_ID =
            Menu.FIRST + 3;
    public static final int TWO_ID = Menu.FIRST + 4;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);//ghvgvyugyhj
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items));

        selection = (TextView) findViewById(R.id.selection);
        getListView().setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        populateMenu(menu);
        menu.setHeaderTitle("Divider Height");
    }
});

    }

    public void onListItemClick(ListView parent, View v, int
            position, long id) {
        selection.setText(items[position]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        populateMenu(menu);
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        applyMenuChoice(item);
        return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return (applyMenuChoice(item) ||
                super.onContextItemSelected(item));
    }

    private void populateMenu(Menu menu) {
        menu.add(0, TWO_ID,0, "2 Pixels");
        menu.add(0, EIGHT_ID,0, "8 Pixels");
        menu.add(0, SIXTEEN_ID, 0,"16 Pixels");
        menu.add(0, TWENTY_FOUR_ID, 0,"24 Pixels");
    }

    private boolean applyMenuChoice(MenuItem item) {
        switch (item.getItemId()) {
            case EIGHT_ID:
                getListView().setDividerHeight(8);
                return (true);
            case SIXTEEN_ID:
                getListView().setDividerHeight(16);
                return (true);
            case TWENTY_FOUR_ID:
                getListView().setDividerHeight(24);
                return (true);
            case TWO_ID:
                getListView().setDividerHeight(2);
                return (true);
        }
        return (true);
    }
}