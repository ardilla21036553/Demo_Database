package sg.edu.rp.c346.id21036553.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask, etDate;
    Button btnInsert, btnGetTasks;
    ListView lv;
    TextView tvResults;
    ArrayList<String> allTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etTask);
        etDate = findViewById(R.id.etDate);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);

        allTask = new ArrayList<>();
        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,allTask);
        lv.setAdapter(aaTask);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                //db.insertTask("Submit RJ", "25 Apr 2021");
                db.insertTask(etTask.getText().toString(), etDate.getText().toString());
            }
        });


        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                ArrayList<Task> datalist = db.getTasks();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                    allTask.add(datalist.get(i).getId() + " \n" + datalist.get(i).getDescription() + " \n" + datalist.get(i).getDate() + " ");
                    aaTask.notifyDataSetChanged();
                }
                tvResults.setText(txt);

            }
        });
    }

}