package page;

//Group 3
//2602159414 - Kanya Fadhillah Azmi
//2602165631 - Afrida Salsabila Putri
//2602152244 - Bernice Abigail Barakati
//2602162503 - Daniel Satrya Dewangga

import java.io.File;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.charm;
import main.user;

public class LoginPage {
	private ArrayList<user> users;
	private ArrayList<charm> charms;
	
	private Stage stage;
	
	public LoginPage(Stage stage, ArrayList<user> users, ArrayList<charm> charms) {
        this.stage = stage;
		this.users = users;
		this.charms = charms;
		setlogin(stage);
	}
	
	BorderPane bp;
	VBox vbox1, vbox2;
	StackPane root;
	
	Label Emaillbl, Passwordlbl, clickherelbl;
	TextField tfEmail;
	PasswordField pfPassword;
	ImageView ivAppLogo, ivLogo;
	Button loginbtn;
	MediaPlayer mpvid;
	Media bgvid;
	MediaView mvBG;
	
	public void initsetup() {
		bp = new BorderPane();
		vbox1 = new VBox();
		vbox2 = new VBox();
		
		Emaillbl = new Label("Email");
		Passwordlbl = new Label("Password");
		clickherelbl = new Label("Click here to register");
		
		tfEmail = new TextField();
		tfEmail.setPromptText("Email");
		pfPassword = new PasswordField();
		pfPassword.setPromptText("Password");
		
		loginbtn = new Button("LOGIN");
		loginbtn.setMaxWidth(400);
		
		ivAppLogo = loadImage("Assets/image/applogo.png");
		ivLogo = loadImage("Assets/image/logo.png");
		
	    mvBG = new MediaView();
	    bgvid = new Media(new File("Assets/video/video.mp4").toURI().toString());
	    mpvid = new MediaPlayer(bgvid);
	    mpvid.setCycleCount(MediaPlayer.INDEFINITE); // loop video
	    mpvid.play();
	    
	    mvBG.setMediaPlayer(mpvid);
	    mvBG.fitWidthProperty().bind(Bindings.selectDouble(bp.sceneProperty(), "width"));
	    mvBG.fitHeightProperty().bind(Bindings.selectDouble(bp.sceneProperty(), "height"));
		
		vbox1.getChildren().addAll(ivLogo, vbox2, loginbtn);
		vbox2.getChildren().addAll(Emaillbl, tfEmail, Passwordlbl, pfPassword, clickherelbl);
		vbox1.setMaxWidth(400);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setSpacing(10);
		vbox2.setSpacing(10);
		vbox2.setMaxWidth(400);
		
		bp.setCenter(vbox1);
		bp.setAlignment(vbox1, Pos.CENTER);
		bp.setPadding(new Insets(10));
		
		root = new StackPane();
		root.getChildren().addAll(mvBG,bp);
		
	}

	public void setlogin(Stage stage){
		this.stage = stage;
		
		initsetup();
		stylecss();
		setEvent();
		Scene scene = new Scene(root, 1280, 720);
		stage.setScene(scene);
		if(ivAppLogo.getImage() != null) {
			stage.getIcons().add(ivAppLogo.getImage());
		}
		stage.setTitle("Jubilee Emporium");
		stage.show();
	}
	
	private ImageView loadImage(String url) {
		File file = new File(url);
		return new ImageView(file.toURI().toString());
	}
	
	private void stylecss() {
		loginbtn.setStyle("-fx-background-color: #CD7F32; -fx-text-fill: #FFFFFF; -fx-font-weight: Bold;");
		Emaillbl.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 18px;");
		Passwordlbl.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 18px;");
		clickherelbl.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 18px; ");
	}
	
	public void setEvent(){
		loginbtn.setOnMouseClicked(e ->{
			loginlogic();
		});
		
		clickherelbl.setOnMouseClicked(e ->{
			RegisterPage rp = new RegisterPage(stage, users, charms);
			rp.setregister(stage);
		});
	}
	
	public void loginlogic() {
		String email = tfEmail.getText();
		String password = pfPassword.getText();
		
		if(email.isEmpty()) {
			showerror("Email field must be filled!");
			return;
		}
		
		if(password.isEmpty()) {
			showerror("Password field must be filled!");
			return;
		}
		
		if(email.equals("admin@gmail.com") && password.equals("admin")) {
			Stage loginStage = (Stage) loginbtn.getScene().getWindow();
	        loginStage.close();
	        
			adminpage ap = new adminpage(stage, users, charms);
	        ap.setadmin(stage);
	        
			return;
		}
		
		boolean emailfound = false;
		for(user item : users) {
			if(item.getEmail().equals(email)) {
				emailfound = true;
				user authenticatedUser = authenticateUser(email, password);
	            if (authenticatedUser != null) {
	            	Stage loginStage = (Stage) loginbtn.getScene().getWindow();
	    	        loginStage.close();
	    	        
	                ShopWindowPage swp = new ShopWindowPage(stage, users, authenticatedUser, charms);
	            } else {
	                showerror("Password is Invalid");
	            }
			}
		}
		
		if(!emailfound) {
			showerror("Email does not exist");
		}
		
			
	}
	
	private user authenticateUser(String email, String password) {
        for (user user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
	
	public void showerror(String text) {
		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(text);
		a.show();
	}
}
