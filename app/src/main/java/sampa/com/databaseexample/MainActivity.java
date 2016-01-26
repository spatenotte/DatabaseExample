package sampa.com.databaseexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText inputName = (EditText) findViewById(R.id.inputName);
        final EditText inputAge = (EditText) findViewById(R.id.inputAge);
        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button queryDBButton = (Button) findViewById(R.id.queryDBButton);

        final SQLiteHelper helper = new SQLiteHelper(getBaseContext(), "studentDB", null);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputName.getText().toString().trim().length() == 0 || inputAge.getText().toString().trim().length() == 0) {
                    Snackbar.make(findViewById(R.id.content_main), "Invalid input", Snackbar.LENGTH_LONG).show();
                }
                else {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    String name = inputName.getText().toString().trim();
                    int age = Integer.parseInt(inputAge.getText().toString());
                    helper.addData(name, age);
                }
            }
        });

        queryDBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Data> data = helper.getData();
                ListView listView = (ListView) findViewById(R.id.listView);
                DataAdapter adapter = new DataAdapter(getBaseContext(), data);
                listView.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
