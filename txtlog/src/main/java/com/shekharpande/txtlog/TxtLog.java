package com.shekharpande.txtlog;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shekhar pande
 */
public class TxtLog {

    public static final String TAG = TxtLog.class.getSimpleName();
    private static File logFile;
    private static Application context;
    private static boolean isDebugModeOnly = false;

    /**
     * This method is used for the initialize sdk
     * @param context : Application context need for file operation.
     * @param isDebugModeOnly : Set it true if you wants log writing for debug mode only.
     */
    public static void sdkInitialize(Application context, boolean isDebugModeOnly) {
        TxtLog.context = context;
        TxtLog.isDebugModeOnly = isDebugModeOnly;
    }

    public static void setIsDebugModeOnly(boolean isDebugModeOnly) {
        TxtLog.isDebugModeOnly = isDebugModeOnly;
    }

    /**
     * This method is used  for write log with TAG
     * @param tag : Tag like class name, or method name.
     * @param message : what you wants to write in file
     */
    public static void write(String tag, String message) {

        if (context == null) {
            String error = "library not init please call TxtLog.sdkInitialize(this)";
            Log.e(TAG, error);
            return;

        }

        if (!BuildConfig.DEBUG && isDebugModeOnly) {
            String error = "Log writer enable for debug only";
            Log.e(TAG, error);
            return;
        }

        if (tag == null || tag.equals("")) {
            tag = TAG;
        }
        if (message == null || message.equals("")) {
            System.out.println("Message is empty");
            return;
        }
        Log.d(tag, message);
        writeToFile(tag, message);
    }

    private static String getDateTime() {
        Calendar cal = Calendar.getInstance();
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String localTime = date.format(currentLocalTime);
        return localTime;
    }

    private static void writeToFile(String tag, String message) {
        String data = getDateTime() + " " + tag + " " + message;
        try {
            FileOutputStream f = new FileOutputStream(getLogFile(), true);
            PrintWriter pw = new PrintWriter(f);
            pw.println(data);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG, "******* File not found. Did you" +
                    " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            Log.i(TAG, "******* File not found. Did you" +
                    " added a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
            e.printStackTrace();
        }
    }

    public static void write(String message) {
        write(TAG, message);
    }

    private static File getLogFile() {
        if (logFile == null) {
            File logDir = context.getExternalFilesDir("");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            logFile = new File(logDir, "TxtLogFile.txt");
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return logFile;
    }
}
