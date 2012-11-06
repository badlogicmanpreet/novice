package com.javageek.game.framework.impl;

import android.media.SoundPool;

import com.javageek.game.framework.Sound;

public class AndroidSound implements Sound {
	SoundPool soundPool;
	int soundID;

	public AndroidSound(SoundPool soundPool, int soundID) {
		this.soundPool = soundPool;
		this.soundID = soundID;
	}

	@Override
	public void play(float volume) {
		soundPool.play(soundID, volume, volume, 0, 0, 1);
	}

	@Override
	public void dispose() {
		soundPool.unload(soundID);
	}

}
