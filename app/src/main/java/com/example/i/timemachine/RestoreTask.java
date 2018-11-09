package com.example.i.timemachine;

import java.lang.ref.WeakReference;

public class RestoreTask extends ExecuteTask {
    private WeakReference<MainActivity> weakReference;

    RestoreTask(MainActivity activity) {
        super(activity);
    }

    @Override
    protected String doInBackground(String... commands) {
        String s = super.doInBackground(commands);
        String command;
        StringBuilder stringBuilder = new StringBuilder();

        String dataCurrentPath = weakReference.get().dataCurrentPath;
        String dataBackupPath = weakReference.get().dataBackupPath;
        String[] ownInfo = s.split("\\s+");
        // if error, return
        if (ownInfo[0].substring(0, 6).equals("[Error]"))
            return null;

        // extract data files
        command = String.format("tar -xvf %s", dataBackupPath);
        stringBuilder.append(Execute.exc(command));
        // give the back up data permission
        // get the original directory owner and group
        command = String.format("chown %s:%s %s", ownInfo[0], ownInfo[1], dataCurrentPath);
        stringBuilder.append(Execute.exc(command));

        return null;
    }

    @Override
    protected void onPostExecute(String s) {

    }
}
