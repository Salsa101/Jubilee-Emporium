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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.charm;
import main.user;

public class adminpage {
    private ArrayList<user> users;
	private ArrayList<charm> charms;
	private Stage stage;
	
	public adminpage(Stage stage, ArrayList<user> users, ArrayList<charm> charms) {
        this.stage = stage;
		this.users = users;
		this.charms = charms;
		setadmin(stage);
	}
	
	BorderPane bp;
	ScrollPane scrollpane;
	FlowPane fp;
	
	VBox vbox, vboxiconheart, vboxdescheart;
	VBox vboxiconcoffee, vboxdesccoffee;
	VBox vboxiconwhetstone, vboxdescwhetstone;
	VBox vboxiconsmokebomb, vboxdescsmokebomb;
	VBox vboxicontwinheart, vboxdesctwinheart;
	
	HBox hboxpricestockheart, hboxpricestockcoffee, hboxpricestockwhetstone, hboxpricestocksmokebomb, hboxpricestocktwinheart;
	GridPane gpitemheart, gpitemcoffee, gpitemwhetstone, gpitemsmokebomb, gpitemtwinheart;
	ImageView ivAppLogo, ivhearticon, ivtwinhearticon, ivwhetstoneicon, ivsmokebombicon, ivcoffeeicon;
	
	Label adminpagelbl;
	Label hearticonlbl, heartpricelbl, heartstocklbl, heartdescriptionlbl;
	Label coffeeiconlbl, coffeepricelbl, coffeestocklbl, coffeedescriptionlbl;
	Label whetstoneiconlbl, whetstonepricelbl, whetstonestocklbl, whetstonedescriptionlbl;
	Label smokebombiconlbl, smokebombpricelbl, smokebombstocklbl, smokebombdescriptionlbl;
	Label twinhearticonlbl, twinheartpricelbl, twinheartstocklbl, twinheartdescriptionlbl;
	
	TextArea tadescriptionheart, tadescriptioncoffee, tadescriptionwhetstone, tadescriptionsmokebomb, tadescriptiontwinheart;
	TextField tfpriceheart, tfpricecoffee, tfpricewhetstone, tfpricesmokebomb, tfpricetwinheart;
	Spinner<Integer> spstockheart, spstockcoffee, spstockwhetstone, spstocksmokebomb, spstocktwinheart;
	
	Button updatebtnheart, updatebtncoffee, updatebtnwhetstone, updatebtnsmokebomb, updatebtntwinheart;
	
	MenuBar menubar;
	Menu menu;
	MenuItem MIlogout;
	
	public void init() {
		bp = new BorderPane();
		vbox = new VBox();
		scrollpane = new ScrollPane(vbox);
		fp = new FlowPane();
		menubar = new MenuBar();
		menu = new Menu("Menu");
		MIlogout = new MenuItem("Log Out");
		
		adminpagelbl = new Label("Admin Page");
	}
	
	public void setup() {
		ivAppLogo = loadImage("Assets/image/applogo.png");
		    
		bp.setCenter(scrollpane);
		bp.setTop(menubar);
		
		menubar.getMenus().add(menu);
		menu.getItems().add(MIlogout);
		
		bp.setAlignment(vbox, Pos.CENTER);
		
		vbox.getChildren().addAll(adminpagelbl, fp);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
	}
	
	public void setadmin(Stage stage) {
		init();
		setup();
		
		stylecss();
		setEvent();
		setupCharm(fp);

		scrollfunction();
		
		Scene scene = new Scene(bp, 1280, 720);
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
	
	private void setupCharm(FlowPane flowPane ) {
        for (charm charm : charms) {
            ImageView iv = loadImage(getimagepath(charm.getName()));
            
            VBox icon = new VBox();
            icon.setSpacing(5);
            icon.setMinWidth(200);
            icon.setAlignment(Pos.CENTER);
            
            VBox detail = new VBox();
            detail.setSpacing(10);
            
            GridPane item = new GridPane();
            item.setPadding(new Insets(10));
            item.setMaxWidth(800);
            
            TextField tfprice = new TextField();
            tfprice.setText(String.valueOf(charm.getPrice()));
            
            Spinner<Integer> sp = new Spinner<Integer>(0,100,1);
            sp.getValueFactory().setValue(charm.getStock());
            
            TextArea tadesc = new TextArea();
            tadesc.setText(String.valueOf(charm.getDescription()));
            tadesc.setMinHeight(100);
    		tadesc.setPrefHeight(100);
    		tadesc.setMaxHeight(100);
    		tadesc.setWrapText(true);
    		tadesc.setPrefRowCount(4);
    		tadesc.setScrollTop(Double.MAX_VALUE); 
    		tadesc.setEditable(true);
    		
            Label charname = new Label(charm.getName());
            charname.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 24px;");
            
            Label price = new Label("Price: ");
            price.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 16px;");
            
            Label stock = new Label("Stock: ");
            stock.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 16px;");
            
            Label desc = new Label("Description: ");
            desc.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 16px;");
            
            Button updatebtn = new Button("Update");
            updatebtn.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 16px; -fx-background-color: #B05F03");
            
            HBox pricestock = new HBox();
            pricestock.setSpacing(10);
            pricestock.getChildren().addAll(price, tfprice, stock, sp);
            
            item.add(icon, 0, 0);
            item.add(detail, 1, 0);
            
            icon.getChildren().addAll(charname, iv);
            detail.getChildren().addAll(pricestock, desc, tadesc, updatebtn);
            
            GridPane gp = new GridPane();
            gp.setStyle("-fx-background-color: #FF8C00");
            gp.setAlignment(Pos.CENTER_LEFT);
            gp.setMinHeight(300);
            gp.setMinWidth(750);
            gp.add(icon, 0, 0);
            gp.add(detail, 1, 0);
            gp.setHgap(20);
            
		    flowPane.getChildren().add(gp);
		    flowPane.setAlignment(Pos.CENTER);
		    flowPane.setVgap(20);
		    
            updatebtn.setOnMouseClicked(e ->{
            	String pricetext = tfprice.getText();
            	Integer cstock = sp.getValue();
            	String cdesc = tadesc.getText();
            	
            	if (pricetext.isEmpty()) {
        	        showerror("Price must be filled.");
        	        return;
        	    }
        	    
            	int cprice;
        	    try {
        	        cprice = Integer.parseInt(pricetext);
        	    } catch (NumberFormatException ex) {
        	        showerror("Price must be numeric");
        	        return;
        	    }
        	    
        		
        		if(cprice <= 0) {
        			showerror("Price must be more than 0");
        			return;
        		}
        		
        		if(cstock <= 0) {
        			showerror("Stock must be more than 0");
        			return;
        		}
        		
        		if(cdesc.isEmpty()) {
        			showerror("Description must be filled");
        			return;
        		}
        		charm.setPrice(cprice);
		        charm.setStock(cstock);
		        charm.setDescription(cdesc);
        		
        		});
            
            iv.setOnMouseClicked(e ->{
            	openviewpopup(charm.getName());
            });           
        }        
	}            
	
	private void stylecss() {
		bp.setStyle("-fx-background-color: #B05F03");
		scrollpane.setStyle("-fx-background-color: #B05F03; -fx-background: #B05F03");
		adminpagelbl.setStyle("-fx-font-weight: Bold; -fx-text-fill : #FFFFFF; -fx-font-size: 32px;");
	}
	
	public void setEvent() {
		MIlogout.setOnAction(e ->{		
			//close window
			Stage adminStage = (Stage) MIlogout.getParentPopup().getOwnerWindow();
	        adminStage.close();
	        
	        //open login page
	        LoginPage rp = new LoginPage(stage, users,charms);
			rp.setlogin(stage);
		});
		
		
	}
	
	
	public void showerror(String text) {
		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(text);
		a.show();
	}
	
	public void scrollfunction() {
		scrollpane.setFitToWidth(true);
		scrollpane.setFitToHeight(true);
		scrollpane.setPannable(true);
		
		scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	}
	
	public charm findCharmByName(String name) {
	    for (charm c : charms) {
	        if (c.getName().equalsIgnoreCase(name)) {
	            return c;
	        }
	    }
	    return null;
	}
	

	
	public void openviewpopup(String charmname) {
		ViewCharmPage vcp = new ViewCharmPage(charms, charmname);
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
}
