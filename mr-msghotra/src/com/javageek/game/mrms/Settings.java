package com.javageek.game.mrms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.javageek.game.framework.FileIO;

public class Settings {

	public static boolean soundEnabled = true;
	public static int[] highscores = new int[] { 100, 80, 50, 30, 10 };

	public static void load(FileIO files) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(files.readFile(".mrms")));
			soundEnabled = Boolean.parseBoolean(bufferedReader.readLine());
			for (int i = 0; i < 5; i++) {
				highscores[i] = Integer.parseInt(bufferedReader.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void save(FileIO files) {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(files.writeFile(".mrms")));
			bufferedWriter.write(Boolean.toString(soundEnabled));
			for (int i = 0; i < 5; i++) {
				bufferedWriter.write(Integer.toString(highscores[i]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void addScore(int score) {
		for (int i = 0; i < 5; i++) {
			if (highscores[i] < score) {
				for (int j = 4; j > i; j--) {
					highscores[j] = highscores[j - 1];
				}
				highscores[i] = score;
				break;
			}
		}
	}
}
