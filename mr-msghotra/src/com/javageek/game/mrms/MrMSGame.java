package com.javageek.game.mrms;

import com.javageek.game.framework.Screen;
import com.javageek.game.framework.impl.AndroidGame;

public class MrMSGame extends AndroidGame {

	@Override
	public Screen getStartScreen() {
	    return new LoadingScreen(this);
	}

}
