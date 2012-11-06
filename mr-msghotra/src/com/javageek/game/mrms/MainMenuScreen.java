package com.javageek.game.mrms;

import java.util.List;

import com.javageek.game.framework.Game;
import com.javageek.game.framework.Graphics;
import com.javageek.game.framework.Input.TouchEvent;
import com.javageek.game.framework.Screen;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent touchEvent = touchEvents.get(i);
			if (touchEvent.type == TouchEvent.TOUCH_UP) {
				if (inBounds(touchEvent, 0, g.getHeight() - 64, 64, 64)) {
					Settings.soundEnabled = !Settings.soundEnabled;
					if (Settings.soundEnabled)
						Assets.click.play(1);
				}
				if (inBounds(touchEvent, 64, 220, 192, 42)) {
					game.setScreen(new GameScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if (inBounds(touchEvent, 64, 220 + 42, 192, 42)) {
					game.setScreen(new HighscoreScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
				}
				if (inBounds(touchEvent, 64, 220 + 84, 192, 42)) {
					game.setScreen(new HelpScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
				}
			}
		}

	}

	private boolean inBounds(TouchEvent touchEvent, int x, int y, int width, int height) {
		if ((touchEvent.x > x && touchEvent.x < x + width - 1) && (touchEvent.y > y && touchEvent.y < y + height - 1))
			return true;
		else
			return false;
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.logo, 32, 20);
		g.drawPixmap(Assets.mainMenu, 64, 220);
		if (Settings.soundEnabled)
			g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
		else
			g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
