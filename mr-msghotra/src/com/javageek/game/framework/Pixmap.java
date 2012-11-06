package com.javageek.game.framework;

import com.javageek.game.framework.Graphics.PixmapFormat;

public interface Pixmap {

	public int getWidth();
	
	public int getHeight();
	
	public PixmapFormat format();
	
	public void dispose();
}
