package com.example.sunshine.vmovie2.widget;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.sunshine.vmovie2.R;


/**
 * Created by Administrator on 2017/3/13 0013.
 */
public class AudioViewController {

    private static final String TAG = AudioViewController.class.getSimpleName();
    private static Toast sToast;

    public static void showLight(Context context, int currentValue){

        if (sToast == null){
            sToast = new Toast(context);
            sToast.setDuration(Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.LEFT,10,0);


        }
        Log.e(TAG, "showLight: " + currentValue);
        View view = LayoutInflater.from(context).inflate(R.layout.light, null);
        LightView lightView = (LightView) view.findViewById(R.id.teach_video_light_view);
        lightView.setCurrentValue(currentValue);
        sToast.setView(view);
        sToast.show();

    }


}
