package com.example.arushi.fireapp;
import android.app.Application;

import com.firebase.client.Firebase;
/**
 * Created by Arushi on 4/28/2017.
 */
public class FireApp extends Application {
    public void onCreate(){
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
