package sam.workshop.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    LinearLayout listLayout;
    Button taskSaver;
    EditText task;
    SaveList taskList;
    TextView display;

    int taskCount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskList = new SaveList(this);
        taskCount = taskList.getTotalTask();

        setContentView(R.layout.activity_main);

        task = findViewById(R.id.etTask);
        taskSaver = findViewById(R.id.bSave);
        listLayout = findViewById(R.id.llList);
        display = findViewById(R.id.tvMsg);

        loadTasks();

        taskSaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskDetails = task.getText().toString().trim();

                task.setText("");

                if(taskDetails.length() > 0) {
                    display.setVisibility(View.GONE);
                    addItem(taskDetails);
                    taskList.saveTask(taskDetails,taskCount);
                    taskCount++;
                    showMsg("Task Added!");
                }
            }
        });
    }

    private void addItem(String data) {
        CheckBox item = new CheckBox(MainActivity.this);
        item.setText(data);
        item.setOnCheckedChangeListener(MainActivity.this);

        listLayout.addView(item);
    }

    private void loadTasks() {
        String list[] = taskList.getList();

        if(list != null)
        {
            for(int i =0 ;i<list.length;i++)
                addItem(list[i]);
        }
        else
        {
            display.setVisibility(View.VISIBLE);
           showMsg("List empty. Add task");
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            buttonView.setPaintFlags(buttonView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        else
            buttonView.setPaintFlags(0);
    }

    private void showMsg(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }
}
