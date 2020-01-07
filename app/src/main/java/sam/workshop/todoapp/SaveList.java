package sam.workshop.todoapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveList {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    public SaveList(Context context){
        preferences = context.getSharedPreferences("sam.workshop.todoapp.TASK_LIST",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public String[] getList(){

        int taskCount = preferences.getInt("taskCount",0);

        if(taskCount>0)
        {
            String[] result = new String[taskCount];

            for(int i = 0; i < taskCount; i++ )
            {
                result[i] = preferences.getString("task"+i,"");
            }
            return result;
        }
        else
            return null;

    }

    public void saveTask(String task, int taskCount)
    {
        editor.putString("task"+taskCount,task);
        taskCount++;
        editor.putInt("taskCount",taskCount);
        editor.commit();
    }

    public void close(){
        preferences = null;
        editor = null;
    }

    public int getTotalTask() {
        return preferences.getInt("taskCount",0);
    }
}
