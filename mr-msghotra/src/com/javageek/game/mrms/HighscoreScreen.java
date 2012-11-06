package com.javageek.game.mrms;

import java.util.List;

import com.javageek.game.framework.Game;
import com.javageek.game.framework.Graphics;
import com.javageek.game.framework.Input.TouchEvent;
import com.javageek.game.framework.Screen;

public class HighscoreScreen extends Screen {

	String[] lines = new String[5];

	public HighscoreScreen(Game game) {
		super(game);

		for (int i = 0; i < lines.length; i++) {
			lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
		}
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent touchEvent = touchEvents.get(i);
			if (touchEvent.type == TouchEvent.TOUCH_UP) {
				if (touchEvent.x < 64 && touchEvent.y > 416)
					game.setScreen(new MainMenuScreen(game));
				if (Settings.soundEnabled)
					Assets.click.play(1);
				return;
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.mainMenu, 64, 20, 0, 42, 196, 42);

		int y = 100;
		for (int i = 0; i < lines.length; i++) {
			drawText(g, lines[i], 20, y);
			y += 50;
		}

		g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
	}

	private void drawText(Graphics g, String line, int x, int y) {
		int len = line.length();
		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);

			if (character == ' ') {
				x += 20;
				continue;
			}

			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
				srcX = 200;
				srcWidth = 10;
			} else {
				srcX = (character - '0') * 20;
				srcWidth = 20;
			}

			g.drawPixmap(Assets.buttons, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

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
