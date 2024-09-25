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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import main.charm;
import main.user;

public class RegisterPage {
    private ArrayList<user> users;
    private ArrayList<charm> charms;
    
	private Stage stage;
	
	public RegisterPage(Stage stage, ArrayList<user> users, ArrayList<charm> charms) {
		this.stage = stage;
		this.users = users;
		this.charms = charms;
		setregister(stage);
	}
	
	BorderPane bp;
	VBox vbox1, vbox2;
	StackPane root;
	
	Label Namelbl, Emaillbl, Passwordlbl, ConfirmPasslbl, clickherelbl;
	TextField tfName, tfEmail;
	PasswordField pfPassword, pfConfirmPass;
	ImageView ivAppLogo, ivLogo, ivbg;
	Button registerbtn;
	MediaPlayer mpaudio;
	Media bgaudio;
	Pane backgroundpic;
	
	public void initsetup() {
		bp = new BorderPane();
		vbox1 = new VBox();
		vbox2 = new VBox();
		
		Namelbl = new Label("Name");
		Emaillbl = new Label("Email");
		Passwordlbl = new Label("Password");
		ConfirmPasslbl = new Label("Confirm Password");
		clickherelbl = new Label("Login here");
		
		tfName = new TextField();
		tfName.setPromptText("Name");
		tfEmail = new TextField();
		tfEmail.setPromptText("Email");
		pfPassword = new PasswordField();
		pfPassword.setPromptText("Password");
		pfConfirmPass = new PasswordField();
		pfConfirmPass.setPromptText("Confirm Password");
		
		registerbtn = new Button("Register Account");
		registerbtn.setMaxWidth(400);
		
		ivAppLogo = loadImage("Assets/image/applogo.png");
		ivLogo = loadImage("Assets/image/logo.png");
		
		ivbg = loadImage("Assets/image/registerbg.jpg");
		ivbg.fitWidthProperty().bind(Bindings.selectDouble(bp.sceneProperty(), "width"));
		ivbg.fitHeightProperty().bind(Bindings.selectDouble(bp.sceneProperty(), "height"));
		
		backgroundpic = new Pane();
		backgroundpic.getChildren().add(ivbg);
		
		vbox1.getChildren().addAll(ivLogo, vbox2, registerbtn);
		vbox2.getChildren().addAll(Namelbl, tfName, Emaillbl, tfEmail, Passwordlbl, pfPassword, ConfirmPasslbl, pfConfirmPass, clickherelbl);
		vbox1.setMaxWidth(400);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setSpacing(10);
		vbox2.setSpacing(10);
		vbox2.setMaxWidth(400);
		 
		bp.setCenter(vbox1);
		bp.setAlignment(vbox1, Pos.CENTER);
		bp.setPadding(new Insets(10));
		
		root = new StackPane();
		root.getChildren().addAll(backgroundpic, bp);
	}
	
	//setscene
	public void setregister(Stage stage){
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
		registerbtn.setStyle("-fx-background-color: #CD7F32; -fx-text-fill: #FFFFFF; -fx-font-weight: Bold");
		Namelbl.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 18px;");
		Emaillbl.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 18px;");
		Passwordlbl.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 18px;");
		ConfirmPasslbl.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 18px;");
		clickherelbl.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 18px;");
	}
	
	public void setEvent() {
		registerbtn.setOnMouseClicked(e ->{
			registerlogic();
		});
		
		clickherelbl.setOnMouseClicked(e ->{
			//change to login page
			LoginPage rp = new LoginPage(stage, users, charms);
			rp.setlogin(stage);
		});
	}
	
	public void registerlogic() {
		String Name, Email, Password, ConfirmPass;
		Name = tfName.getText();
		Email = tfEmail.getText();
		Password = pfPassword.getText();
		ConfirmPass = pfConfirmPass.getText();
		
		//Name logic
		if(Name.isEmpty()) {
			showerror("Name must be filled");
			return;
		}
		
		if(Name.length() < 3 || Name.length() > 12) {
			showerror("Name must be 3-12 characters long");
			return;
		}
		
		//Email logic
		if(Email.isEmpty()) {
			showerror("Email must be filled");
			return;
		}
		
		//cek unique email
		for(user item : users) {
			if(item.getEmail().equals(Email)) {
				showerror("Email already existed");
				return;
			}
		}
		
		if(!Email.endsWith("@gmail.com")) {
			showerror("Email must end with '@gmail.com'");
			return;
		}
		
		if(!emailvalid(Email)) {
			showerror("Email must contain only one '@'");
			return;
		}
		
		if(spacecheck(Email)) {
			showerror("Email must not contain space");
			return;
		}
		
		//password logic
		if(Password.isEmpty()) {
			showerror("Password must be filled");
			return;
		}
		
		if(Password.length() < 8) {
			showerror("Password must at least 8 characters long");
			return;
		}
		
		if(!isAlphanumeric(Password)) {
			showerror("Password must be alphanumeric");
			return;
		}
		
		//confirm password logic
		if(ConfirmPass.isEmpty()) {
			showerror("Confirm Password must be filled");
			return;
		}
		
		if(!ConfirmPass.equals(Password)) {
			showerror("Password and Confirm Password must be the same");
			return;
		}
		
		users.add(new user(Name, Email, Password, 100));
		
		Stage loginStage = (Stage) registerbtn.getScene().getWindow();
        loginStage.close();
		LoginPage lp = new LoginPage(stage, users, charms);
		
	}
	
	public void showerror(String text) {
		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(text);
		a.show();
	}
	
	//function buat cek email cuma ada 1 '@'
	private boolean emailvalid(String email) {
	    int atCount = email.length() - email.replace("@", "").length();
	    return atCount == 1;
	}
	
	//function buat cek email gaada space
	private boolean spacecheck(String email) {
	    for (char ch : email.toCharArray()) {
	        if (Character.isWhitespace(ch)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	//function buat cek password alphanumeric
	public boolean isAlphanumeric(String password) {
	    if (password == null || password.isEmpty()) {
	        return false;
	    }
	    
	    boolean hasLetter = false;
	    boolean hasNumber = false;
	   
	    for (char ch : password.toCharArray()) {
	    	if (Character.isLetter(ch)) {
	            hasLetter = true;
	        }
	        if (Character.isDigit(ch)) {
	            hasNumber = true;
	        }
	    }
	    return hasLetter && hasNumber;
	}
	
	private user authenticateUser(String email, String password) {
        for (user user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
	
	
	
}

