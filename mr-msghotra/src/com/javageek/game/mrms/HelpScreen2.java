package com.javageek.game.mrms;

import java.util.List;

import com.javageek.game.framework.Game;
import com.javageek.game.framework.Graphics;
import com.javageek.game.framework.Input.TouchEvent;
import com.javageek.game.framework.Screen;

public class HelpScreen2 extends Screen {

	public HelpScreen2(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent touchEvent = touchEvents.get(i);
			if (touchEvent.type == TouchEvent.TOUCH_UP) {
				if (touchEvent.x > 256 && touchEvent.y > 416) {
					game.setScreen(new HelpScreen3(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.help2, 64, 100);
		g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
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
