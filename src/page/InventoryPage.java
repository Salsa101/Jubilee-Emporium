package page;

//Group 3
//2602159414 - Kanya Fadhillah Azmi
//2602165631 - Afrida Salsabila Putri
//2602152244 - Bernice Abigail Barakati
//2602162503 - Daniel Satrya Dewangga

import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.cart;
import main.charm;
import main.inventory;
import main.user;

public class InventoryPage {
	private user authenticateduser;
	
	private ArrayList<user> users;
	private ArrayList<charm> charms;
	private Stage stage;
	
	BorderPane bp;
	VBox vb;
	FlowPane fp;
	
	MenuBar menubar;
	Menu menu;
	MenuItem MIlogout, MIshop;
	
	Label titlelbl, invemptylbl;
	
	ImageView ivAppLogo;
	
	public InventoryPage(Stage stage, ArrayList<user> users, user authenticateduser, ArrayList<charm> charms) {
		this.stage = stage;
		this.users = users;
		this.authenticateduser = authenticateduser;
		this.charms = charms;
		setinventory(stage);
	}
	
	public void init() {
		bp = new BorderPane();
		vb = new VBox();
		fp = new FlowPane();
		
		menubar = new MenuBar();
		menu =  new Menu("Menu");
		MIlogout = new MenuItem("Log Out");
		MIshop = new MenuItem("Shop");
		
		titlelbl = new Label(authenticateduser.getName() + "'s INVENTORY");
		invemptylbl = new Label("Inventory is Empty");
	}
	
	public void setup() {
		bp.setTop(menubar);
		bp.setCenter(vb);
		bp.setAlignment(vb, Pos.TOP_CENTER);
		
		vb.getChildren().addAll(titlelbl, fp);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(10);
		
		fp.setAlignment(Pos.TOP_CENTER);
		fp.setMinHeight(800);
		fp.setHgap(20);
		fp.setMaxWidth(760);
		fp.setVgap(20);
		
		menubar.getMenus().add(menu);
		menu.getItems().addAll(MIshop, MIlogout);
		
		ivAppLogo = loadImage("Assets/image/applogo.mp4");
	}
	
	public void setinventory(Stage stage) {
		init();
		setup();
		stylecss();
		setEvent();
		setupFlowPane(fp, authenticateduser.getInv());
		
		
		Scene scene = new Scene(bp, 1280, 720);
		stage.setScene(scene);
		if(ivAppLogo.getImage() != null) {
			stage.getIcons().add(ivAppLogo.getImage());
		}
		stage.setTitle("Jubilee Emporium");
		stage.show();
	}
	
	public void stylecss() {
		bp.setStyle("-fx-background-color: #B05F03;");
		
		titlelbl.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 32px");
	}
	
	public void setEvent() {
		MIlogout.setOnAction(e ->{
			//close window
			Stage shopStage = (Stage) MIlogout.getParentPopup().getOwnerWindow();
	        shopStage.close();
	        
	        //open login page
	        LoginPage lp = new  LoginPage(stage, users, charms);
			lp.setlogin(stage);
		});
		
		MIshop.setOnAction(e ->{
			Stage shopStage = (Stage) MIshop.getParentPopup().getOwnerWindow();
	        shopStage.close();
	        
			ShopWindowPage sp = new ShopWindowPage(stage, users, authenticateduser, charms);
			sp.setshop(stage);
		});
	}
	
	private void setupFlowPane(FlowPane flowPane, ArrayList<inventory> inv) {
		for (inventory item : inv) {
		    VBox invvbox = new VBox();
		    invvbox.setMinHeight(300);
		    invvbox.setMinWidth(210);
		    invvbox.setSpacing(5);
		    invvbox.setAlignment(Pos.CENTER);
		    invvbox.setStyle("-fx-background-color: #FF8C00;");
		    	
		    Label charmnameLabel = new Label(item.getItemname());
		    charmnameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 20px");
		        
		    Label quantityLabel = new Label("Quantity: " + item.getItemqty() + "X");
		    quantityLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 20px");
		        
		    ImageView ivcharm = loadImage(getimagepath(item.getItemname()));
		    ivcharm.setOnMouseClicked(e ->{
            	openviewpopup(item.getItemname());
            });
		    
		    Button usecharmbtn = new Button("Use Charm");
		    usecharmbtn.setStyle("-fx-background-color: #622F22; -fx-font-size:16px; -fx-font-weight: Bold; -fx-text-fill: #FFFFFF;");
		        
		    usecharmbtn.setOnAction(e -> {
	            item.setItemqty(item.getItemqty() - 1);
	            quantityLabel.setText("Quantity: " + item.getItemqty() + "X");

	            if (item.getItemqty() <= 0) {
	                inv.remove(item);
	                flowPane.getChildren().remove(invvbox);
	            }
	        });

		    invvbox.getChildren().addAll(charmnameLabel, ivcharm, quantityLabel, usecharmbtn);
		    flowPane.getChildren().add(invvbox);
		    }
		}
	
	
	private ImageView loadImage(String url) {
		File file = new File(url);
		return new ImageView(file.toURI().toString());
	}
	
	private String getimagepath(String charmname) {
		String url;
		for(charm c : charms) {
			if(c.getName().equals(charmname)) {
				return c.getImagepath();
			}
		}
		return null;
	}
	
	public void openviewpopup(String charmname) {
		ViewCharmPage vcp = new ViewCharmPage(charms, charmname);
	}
}
