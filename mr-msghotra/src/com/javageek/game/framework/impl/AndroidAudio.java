package com.javageek.game.framework.impl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.javageek.game.framework.Audio;
import com.javageek.game.framework.Music;
import com.javageek.game.framework.Sound;

public class AndroidAudio implements Audio {
	public AssetManager assets;
	public SoundPool soundPool;

	public AndroidAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}

	@Override
	public Sound newSound(String fileName) {
		int soundID = 0;
		try {
			AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
			soundID = soundPool.load(assetFileDescriptor, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new AndroidSound(soundPool, soundID);
	}

	@Override
	public Music newMusic(String fileName) {
		AssetFileDescriptor assetFileDescriptor = null;
		try {
			assetFileDescriptor = assets.openFd(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new AndroidMusic(assetFileDescriptor);
	}

}
