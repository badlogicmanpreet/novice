package com.badlogic.drop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Drop implements ApplicationListener {
	Texture dropImage;
	Texture bucketImage;
	Sound dropSound;
	Music rainMusic;

	@Override
	public void create() {
		// load the images from assets (Texture does the loading & stores it in video RAM)
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		// load sound & music from assets
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// start the background music now
		rainMusic.setLooping(true);
		rainMusic.play();

		//TODO
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
