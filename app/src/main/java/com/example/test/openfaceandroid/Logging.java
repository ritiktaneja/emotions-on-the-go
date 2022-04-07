package com.example.test.openfaceandroid;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.test.openfaceandroid.Globals.GetCurrentTimeStamp;

import android.app.usage.UsageStats;
import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Robin on 30.07.2017.
 */


class Emotion {
    String timestamp;
    List<String> emotions;
    String STUDY_ID;
    Emotion(String timestamp, String pred_text, String STUDY_ID) {
        this.timestamp = timestamp;
        this.emotions = Arrays.asList(pred_text.split(";").clone()).subList(0,7);
        this.STUDY_ID = STUDY_ID;
    }

    public void setSTUDY_ID(String STUDY_ID) {
        this.STUDY_ID = STUDY_ID;
    }

    public String getSTUDY_ID() {
        return STUDY_ID;
    }

    public void setEmotions(List<String> emotions) {
        this.emotions = emotions;
    }

    public List<String> getEmotions() {
        return emotions;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

class AppUsageEntry
{
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(long totalUsage) {
        this.totalUsage = totalUsage;
    }

    String packageName;
    long totalUsage;
    AppUsageEntry(String packageName,long totalUsage)
    {
        this.packageName = packageName;
        this.totalUsage = totalUsage;
    }

}
class AppUsage {
    String timestamp;
    String STUDY_ID;
    List<AppUsageEntry> stats;
    AppUsage(String timestamp, String STUDY_ID,List<AppUsageEntry> st)
    {
        this.timestamp = timestamp;
        this.STUDY_ID = STUDY_ID;
        this.stats= st;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSTUDY_ID() {
        return STUDY_ID;
    }

    public void setSTUDY_ID(String STUDY_ID) {
        this.STUDY_ID = STUDY_ID;
    }

    public List<AppUsageEntry> getStats() {
        return stats;
    }

    public void setStats(List<AppUsageEntry> stats) {
        this.stats = stats;
    }

}

public class Logging {

    public static String STUDY_ID = "X";
    private static final String TAG = "Logging";
    public static String LOG_PATH = "";
    public static String LOG_FILE_CAM = "";
    public static String DEBUG_FILE_CAM = "";
    public static String LOG_FILE_ACT = "";
    public static String LOG_FILE_WEATHER = "";
    public static String LOG_FILE_USER_EMOTIONRATING = "";
    public static String LOG_FILE_APP_USAGE="";

    static FirebaseDatabase db = FirebaseDatabase.getInstance("https://emotions-on-the-go-default-rtdb.asia-southeast1.firebasedatabase.app/");
    //static DatabaseReference myRef = database.getReference("");


    /**
     *  FIREBASE COLLECTIONS CONFIG
     *
     */

    //private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static String PREDICTIONS_COLLECTION = "PREDICTIONS";
    public static String DEBUG_COLLECTIONS = "DEBUG";
    public static String ACTION_COLLECTIONS = "ACTION";
    public static String WEATHER_COLLECTIONS = "WEATHER";
    public static String EMOTION_RATING_COLLECTIONS = "EMOTION_RATING";
    public static String APP_USAGE_COLLECTION = "APP_USAGE";





    public static void setLogPaths()
    {
        String timestamp = GetCurrentTimeStamp(false);
        String extStore = System.getenv("EXTERNAL_STORAGE");
        LOG_PATH = extStore + "/OpenFaceAndroid";
        LOG_FILE_CAM = LOG_PATH + "/OFA_CAM_" + timestamp + ".log";
        DEBUG_FILE_CAM = LOG_PATH + "/OFA_CAM_DEBUG_" + timestamp + ".log";
        LOG_FILE_ACT = LOG_PATH + "/OFA_ACT_" + timestamp + ".log";
        LOG_FILE_WEATHER = LOG_PATH + "/OFA_WEATHER_" + timestamp + ".log";
        LOG_FILE_USER_EMOTIONRATING = LOG_PATH + "/OFA_USEREMOTRATING_" + timestamp + ".log";
        LOG_FILE_APP_USAGE = "APP_USAGE";
    }

    public static void appendLog(String text, String file) {
        appendLog(text, file, true, true);
    }

    public static void appendLog(List<AppUsageEntry> entries,String file) {
        if (db == null)
        {
            Log.d(TAG,"Unable to set up db connection");
            return;
        }
        if ( file == LOG_FILE_APP_USAGE)
        {
            DatabaseReference appUsageCollection = db.getReference(APP_USAGE_COLLECTION);

            appUsageCollection.push().setValue(new AppUsage(GetCurrentTimeStamp(true),STUDY_ID,entries)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG,"App usage pushed to database");
                }
            });
        }
    }

    public static void appendLog(String text, String file, boolean append, boolean timestamp) {

        if (db == null)
        {
            Log.d("Logging","Unable to get db reference");
            db = FirebaseDatabase.getInstance("https://emotions-on-the-go-default-rtdb.asia-southeast1.firebasedatabase.app/");
        }

        String ts = GetCurrentTimeStamp(true);
            try {

                if (file == LOG_FILE_CAM)
                {
                    DatabaseReference predCollection  = db.getReference(PREDICTIONS_COLLECTION);
                    predCollection.push().setValue(new Emotion(ts,text,STUDY_ID)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d(TAG,"Prediction Data Pushed to database");
                        }
                    });
                   // db.collection(PREDICTIONS_COLLECTION).add(new Prediction(ts,text));
                }
                else if (file == LOG_FILE_ACT)
                {


                }

                else if (file == LOG_FILE_USER_EMOTIONRATING)
                {
                    DatabaseReference predCollection  = db.getReference(EMOTION_RATING_COLLECTIONS);
                    predCollection.push().setValue(new Emotion(ts,text,STUDY_ID)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d(TAG,"Pushed to database");
                        }
                    });

                }
                else if (file == LOG_FILE_WEATHER)
                {

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


//    public static void appendLog(String text, String file, boolean append, boolean timestamp) {
//        if (file == null)
//            setLogPaths();
//        File logFile = new File(file);
//        String ts = GetCurrentTimeStamp(true);
//        if (!logFile.exists()) {
//            try {
//                File folder = new File(LOG_PATH);
//                if (!folder.exists()) {
//                    folder.mkdir();
//                }
//                logFile.createNewFile();
//                BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, append));
//                buf.append(ts + ": Init log " + "\n\n");
//                if (file == LOG_FILE_CAM)
//                {
//                    buf.append("Time;Anger;Disgust;Fear;Happiness;Neutral;Sad;Surprised;Attention;"
//                            + "app;long;lat"); // ;Weather;Temp
//                }
//                else if (file == LOG_FILE_ACT)
//                {
//                    buf.append("Time;STILL;ON_FOOT;WALKING;RUNNING;ON_BICYCLE;IN_VEHICLE;TILTING;UNKNOWN");
//
//                }
//                else if (file == LOG_FILE_USER_EMOTIONRATING)
//                {
//                    buf.append("Time;Anger;Disgust;Fear;Happiness;Neutral;Sad;Surprised");
//                }
//                else if (file == LOG_FILE_WEATHER)
//                {
//                    buf.append("Time;Weather;Temp;CountryCode;postalCode;locality;subLocality;thoroughfare;subThoroughfare;feature");
//                }
//                buf.newLine();
//                buf.close();
//            } catch (IOException e) {
//
//                e.printStackTrace();
//            }
//        }
//        try {
//            //BufferedWriter for performance, true to set append to file flag
//            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, append));
//            if (timestamp) {
//                buf.append(ts + ";" + text);
//            } else
//                buf.append(text);
//            buf.newLine();
//            buf.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
