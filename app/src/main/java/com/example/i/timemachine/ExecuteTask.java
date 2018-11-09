package com.example.i.timemachine;


import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Scanner;


class ExecuteTask extends AsyncTask<String, Void, String>{
    private final WeakReference<MainActivity> weakReference;

    // constant commands
    protected final String BACKUP_DATA_COMMAND = "tar -cvf %s %s";
    protected final String GET_OWNER_AND_GROUP = "stat -c \"%%u\" \"%%g\" %s";
    protected final String RESTORE_DATA_COMMAND = "tar -xvf %s";
    protected final String SET_OWNER_AND_GROUP = "chown %s:%s %s";

    ExecuteTask(MainActivity activity) {
        this.weakReference = new WeakReference<>(activity);
    }

    protected String doInBackground(String... commands) {
        return Execute.exc(commands);
    }

    @Override
    protected void onPostExecute(String s) {
        // TODO: get the standard output stream and display on log text
        showDialog(s);
    }

    private void showDialog(String string) {
        TextView logText = weakReference.get().findViewById(R.id.log_text);
        Scanner scanner = new Scanner(string);
        scanner.useDelimiter("\n");
        while (scanner.hasNext())
            logText.append(scanner.next() + "\n");
        //
    }
}
