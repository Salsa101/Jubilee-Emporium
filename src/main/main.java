package main;

//Group 3
//2602159414 - Kanya Fadhillah Azmi
//2602165631 - Afrida Salsabila Putri
//2602152244 - Bernice Abigail Barakati
//2602162503 - Daniel Satrya Dewangga

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import page.LoginPage;

public class main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ArrayList<user> users = new ArrayList<user>();
		users.add(new user("Peter Parker", "peterparker@gmail.com", "Spiderman123", 100)); //Test Account
		users.add(new user("John Doe", "johndoe@gmail.com", "Test1234", 100)); // Test Account
		users.add(new user("Jane Smith", "janesmith@gmail.com", "Test1234", 100)); // Test Account00
		users.add(new user("Test", "test@gmail.com", "Test1234", 100)); // Test Account
		
		ArrayList<charm> charms = new ArrayList<charm>();
		charms.add(new charm("Heart", "Adds an additional hit point but lightly weakens your attack power", 5, 10, "Assets/image/heart.png"));
		charms.add(new charm("Coffee", "Super meter continuously fills in addition to what you earn", 8, 7, "Assets/image/coffee.png"));
		charms.add(new charm("Whetstone", "Your first parry move doubles as a damaging axe attack", 6, 6, "Assets/image/whetstone.png"));
		charms.add(new charm("Smoke Bomb", "You will not take damage during a dash. A great defense maneuver", 6, 12, "Assets/image/smokebomb.png"));
		charms.add(new charm("Twin Heart", "Adds two additional hit points but weakens your attack power", 5, 3, "Assets/image/twinheart.png"));
		
		//start bgm
		audioplayer.initialize();
		
		LoginPage lp = new LoginPage(primaryStage, users, charms);
	}

}



