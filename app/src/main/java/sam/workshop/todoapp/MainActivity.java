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

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    LinearLayout listLayout;
    Button taskSaver;
    EditText task;
    int taskID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = findViewById(R.id.etTask);
        taskSaver = findViewById(R.id.bSave);
        listLayout = findViewById(R.id.llList);

        taskSaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskDetails = task.getText().toString().trim();

                if(taskDetails.length()>0) {
                    CheckBox item = new CheckBox(MainActivity.this);
                    item.setText(taskDetails);
                    //item.setId(++taskID);
                    item.setOnCheckedChangeListener(MainActivity.this);

                    listLayout.addView(item);

                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            buttonView.setPaintFlags(buttonView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else
            buttonView.setPaintFlags(0);
    }
}
