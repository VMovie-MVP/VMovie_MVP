package com.example.sunshine.vmovie2.widget;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
public class AudioController {

    /**
     * 调高音量
     *      ① 获取当前音量
     *      ② 计算变化
     *      ③ 设置
     *
     *     yDelta < 0
     *
     */
    public static void turnUp(Context context, float yDelta, int width){
        AudioManager systemService = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        // 获取当前音乐音量  媒体音量
        int currentVolume = systemService.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = systemService.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 计算变化值
        int change = (int) (2 * maxVolume * yDelta / width);

        int volume = Math.min(maxVolume, currentVolume - change);
        // 设置值
        systemService.setStreamVolume(AudioManager.STREAM_MUSIC,volume, AudioManager.FLAG_SHOW_UI);
    }

    /**
     * 调低音量
     */
    public static void turnDown(Context context, float yDelta, int width){
        AudioManager systemService = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        // 获取当前音乐音量  媒体音量
        int currentVolume = systemService.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = systemService.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 计算变化值
        int change = (int) (2 * maxVolume * yDelta / width);

        int volume = Math.max(0, currentVolume - change);
        // 设置值
        systemService.setStreamVolume(AudioManager.STREAM_MUSIC,volume, AudioManager.FLAG_SHOW_UI);


    }
}
