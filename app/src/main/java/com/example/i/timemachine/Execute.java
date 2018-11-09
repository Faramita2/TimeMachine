package com.example.i.timemachine;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

 class Execute {
    static String exc(String... commands) {
        StringBuilder strings = new StringBuilder();

        try {
            Process su = Runtime.getRuntime().exec("su");
            // su stand for the app
            // stdin means user input also is the same to app output to android
            // stdout means user get output also is the same to app android
            // get input from android
            DataOutputStream stdIn = new DataOutputStream(su.getOutputStream());
            DataInputStream stdError = new DataInputStream(su.getErrorStream());
            DataInputStream stdOut = new DataInputStream(su.getInputStream());

            for (String command : commands)
                stdIn.writeBytes(command + '\n');
            stdIn.flush();
            // after execute everything that exit
            stdIn.writeBytes("exit\n");
            stdIn.flush();
            stdIn.close();

            // normal output
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(stdOut));
            while ((line = br.readLine()) != null)
//                Log.d("[Output]", line);
                strings.append("[Output]").append(line).append("\n");
            br.close();

            // error output
            br = new BufferedReader(new InputStreamReader(stdError));
            while ((line = br.readLine()) != null)
//                Log.e("[Error]", line);
                strings.append("[Error]").append(line).append("\n");
            br.close();

            su.waitFor();
            su.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strings.toString();
    }
}
