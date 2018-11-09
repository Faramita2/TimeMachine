package com.example.i.timemachine;

public class BackupTask extends ExecuteTask {
    BackupTask(MainActivity activity) {
        super(activity);
    }

    @Override
    protected String doInBackground(String... commands) {
        // TODO: this command will replace same name tar achieve, maybe
        // TODO: back up with data&time will be a better way
        return super.doInBackground(commands);
    }

    @Override
    protected void onPostExecute(String s) {
        // TODO: get the standard output stream and display on log text
        super.onPostExecute(s);
    }
}
