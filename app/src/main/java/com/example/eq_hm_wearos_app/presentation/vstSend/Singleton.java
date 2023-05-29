package com.example.eq_hm_wearos_app.presentation.vstSend;

import android.util.Log;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.lang.Integer;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class Singleton {

    // holds created instance of the singleton class
    private static Singleton Instance;
    public List<ProgressDescription> progressDescriptionList;
    int server_port = 54000;
    boolean acc_open = false;

    private static final DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
    private static final DecimalFormat df = new DecimalFormat("0.00", dfs);

    String comparator = "";

    // private constructor
    private Singleton() {

    }

    public static Singleton getInstance() {
        // return the created instance on all subsequent calls
        // checks if instance is created
        if (Instance == null)
        {
            // if not - create one
            Instance = new Singleton();
        }
        // return the created instance on every subsequent call
        return Instance;
    }

    public List<ProgressDescription> getProgressDescriptionList() {
        return this.progressDescriptionList;
    }

    public void setProgressDescriptionList(List<ProgressDescription> progressDescriptionList) {
        this.progressDescriptionList = progressDescriptionList;
    }

    public void setProgressDescriptionIndividualListValue(String pdField_name, int index, float value) {
        getProgressDescriptionList().set(index, new ProgressDescription(pdField_name, value));
    }

    public int getServer_port() {
        return this.server_port;
    }

    public boolean get_acc_state() {
        return this.acc_open;
    }
    public void t_acc_state() { this.acc_open = true; }
    public void f_acc_state() { this.acc_open = false; }

    //---------------------

    public void executeAsyncTask()
    {
        SendControls sendControls = new SendControls();
        String json_test = createJsonFromData();
        if (json_test.equals(comparator)) {}
        else {
            Log.d("JSON",json_test); //to check if json is ok

            sendControls.execute(json_test);
        }
        comparator = json_test;
    }



    public String createJsonFromData() {
        //progresses
        float m1p = getProgressDescriptionList().get(0).progress;
        float m2p = getProgressDescriptionList().get(1).progress;
        float m3p = getProgressDescriptionList().get(2).progress;
        float m4p = getProgressDescriptionList().get(3).progress;
        float m5p = getProgressDescriptionList().get(4).progress;
        float m6p = getProgressDescriptionList().get(5).progress;
        float m7p = getProgressDescriptionList().get(6).progress;
        float m8p = getProgressDescriptionList().get(7).progress;

        //convertion *100
        int m1 = (int) m1p * 100;
        int m2 = (int) m2p * 100;
        int m3 = (int) m3p * 100;
        int m4 = (int) m4p * 100;
        int m5 = (int) m5p * 100;
        int m6 = (int) m6p * 100;
        int m7 = (int) m7p * 100;
        int m8 = (int) m8p * 100;


        //strings from progresses
        String json_m1 = "\"1\"" + " : " + df.format(m1p);
        String json_m2 = "\"2\"" + " : " + df.format(m2p);
        String json_m3 = "\"3\"" + " : " + df.format(m3p);
        String json_m4 = "\"4\"" + " : " + df.format(m4p);
        String json_m5 = "\"5\"" + " : " + df.format(m5p);
        String json_m6 = "\"6\"" + " : " + df.format(m6p);
        String json_m7 = "\"7\"" + " : " + df.format(m7p);
        String json_m8 = "\"8\"" + " : " + df.format(m8p);




        //json
        String json_full = "{" //+ "\n"
                + " " + json_m1 + "," //+ "\n"
                + " " + json_m2 + "," //+ "\n"
                + " " + json_m3 + "," //+ "\n"
                + " " + json_m4 + "," //+ "\n"
                + " " + json_m5 + "," //+ "\n"
                + " " + json_m6 + "," //+ "\n"
                + " " + json_m7 + "," //+ "\n"
                + " " + json_m8 + "" //+ "\n"
                + " }" + "\n";

        return json_full;
    }
}
