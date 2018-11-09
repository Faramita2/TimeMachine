package com.example.i.timemachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // if we can change back up APP ?
    private String appName = "testApp";
//    private String packageName = "com.example.i." + appName;
    private String packageName = "com.lgzzzz.wifi";
    String dataCurrentPath = "/data/data/" + packageName;
    String dataBackupPath = "/data/data/" + packageName + ".tar";

    private View.OnClickListener executeClickedListener  = v -> onExecuteClicked();
    private View.OnClickListener backupClickedListener = v -> onBackupClicked();
    private View.OnClickListener restoreClickedListener = v -> onRestoreClicked();

    private TextView logText;

    // test Execute class
    private void onExecuteClicked() {
        new ExecuteTask(this).execute("test command");
    }

    private void onBackupClicked() {
        // supposed we back up data for an app named "testApp"
        new BackupTask(this).execute();

    }

    private void onRestoreClicked() {
        // TODO: ask user is really wanna restore data
        new RestoreTask(this).execute();

    }

    private void initSettings() {
        Button executeBtn = findViewById(R.id.execute_button);
        executeBtn.setOnClickListener(executeClickedListener);

        Button backupBtn = findViewById(R.id.backup_button);
        backupBtn.setOnClickListener(backupClickedListener);

        Button restoreBtn = findViewById(R.id.restore_button);
        restoreBtn.setOnClickListener(restoreClickedListener);

        logText = findViewById(R.id.log_text);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSettings();
    }

}
