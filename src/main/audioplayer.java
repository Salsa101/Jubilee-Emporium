package main;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class audioplayer {
	 private static MediaPlayer mediaPlayer;
	 
	 public static void initialize() {
	        if (mediaPlayer == null) {
	            Media bgaudio = new Media(new File("Assets/audio/bgm.mp3").toURI().toString());
	            mediaPlayer = new MediaPlayer(bgaudio);
	            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	            mediaPlayer.play();
	        }
	    }
	 
	 public static void stop() {
	     if (mediaPlayer != null) {
	            mediaPlayer.stop();
	     	}
	 	}

	 public static void play() {
	     if (mediaPlayer != null) {
	            mediaPlayer.play();
	     	}
	 	}
}

