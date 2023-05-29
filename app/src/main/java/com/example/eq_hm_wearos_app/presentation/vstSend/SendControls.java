package com.example.eq_hm_wearos_app.presentation.vstSend;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/*public class AsyncTaskClass extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This starts the AsyncTask
        // Doesn't need to be in onCreate()
        new SendControls().execute("my string parameter");
    }
}*/

public class SendControls extends AsyncTask<String, Integer, String> {

    // Here is the AsyncTask class:
    //
    // AsyncTask<Params, Progress, Result>.
    //    Params – the type (Object/primitive) you pass to the AsyncTask from .execute()
    //    Progress – the type that gets passed to onProgressUpdate()
    //    Result – the type returns from doInBackground()
    // Any of them can be String, Integer, Void, etc.

    int server_port = Singleton.getInstance().getServer_port();
    String host_ip_address = "192.168.1.16"; // 192.168.1.16


    //String host_ip_address = "";

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;

    /*public SendControls() {
        try {
            s = new Socket(host_ip_address, server_port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    // This is run in a background thread
    @Override
    protected String doInBackground(String... params) {

        String message = params[0];
        Log.d("Message", message);
        try
        {
            s = new Socket(host_ip_address, server_port);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            //s.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return "this string is passed to onPostExecute";
    }

    // This is called from background thread but runs in UI
    /*@Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        // Do things like update the progress bar
    }*/

    // This runs in UI when background thread finishes
    /*@Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // Do things like hide the progress bar or change a TextView
    }*/

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (pw != null) {
            pw.close();
        }
        if (s != null) {
            s.close();
        }
    }
}
