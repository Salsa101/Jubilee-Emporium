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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.cart;
import main.charm;
import main.inventory;
import main.user;

public class ShopWindowPage {
	int total = 0;
	private user authenticateduser;
	
	private ArrayList<user> users;
	private ArrayList<charm> charms;
	private Stage stage;
	
	BorderPane bp;
	VBox vboxL, vboxR, vbcharmheart, vbcharmcoffee, vbcharmwhetstone, vbcharmsmokebomb, vbcharmtwinheart;
	HBox hbButton, mainhbox, carthbox, charmshophbox, totalhbox, coinhbox;
	FlowPane fp;
	ScrollPane scrollpane;
	GridPane gpshopitem;
	
	ImageView ivAppLogo;
	
	Menu menu;
	MenuBar menubar;
	MenuItem MIinventory, MIlogout;
	
	Label yourcartlbl, charmshoplbl, totallbl, coinlbl, cartemptylbl;
	Label charmheartlbl, priceheartlbl, stockheartlbl;
	Label charmcoffeelbl, pricecoffeelbl, stockcoffeelbl;
	Label charmwhetstonelbl, pricewhetstonelbl, stockwhetstonelbl;
	Label charmsmokebomblbl, pricesmokebomblbl, stocksmokebomblbl;
	Label charmtwinheartlbl, pricetwinheartlbl, stocktwinheartlbl;
	
	ImageView ivcharmheart, ivcharmcoffee, ivcharmwhetstone, ivcharmsmokebomb, ivcharmtwinheart;
	
	Button checkoutbtn, clearcartbtn;
	
	public ShopWindowPage(Stage stage, ArrayList<user> users, user authenticateduser, ArrayList<charm> charms) {
		this.stage = stage;
		this.users = users;
		this.authenticateduser = authenticateduser;
		this.charms = charms;
		setshop(stage);
	}
	
	public void init() {
		bp = new BorderPane();
		vboxL = new VBox();
		vboxR = new VBox();
		hbButton = new HBox();
		carthbox = new HBox();
		charmshophbox = new HBox();
		totalhbox = new HBox();
		coinhbox = new HBox();
		
		fp = new FlowPane();
		scrollpane = new ScrollPane();
		gpshopitem = new GridPane();
		mainhbox = new HBox();
		
		menubar = new MenuBar();
		menu = new Menu("Menu");
		MIinventory = new MenuItem("Inventory");
		MIlogout = new MenuItem("Log Out");
		
		yourcartlbl = new Label("Your Cart");
		charmshoplbl = new Label("Charm Shop");
		totallbl = new Label("Total: " + total);
		coinlbl = new Label("Coin: " + authenticateduser.getCoins());
		cartemptylbl = new Label("YOUR CART IS CURRENTLY EMPTY!");
		
		checkoutbtn = new Button("Check Out");
		clearcartbtn = new Button("Clear Cart");
		
		vbcharmheart = new VBox();
		vbcharmcoffee = new VBox();
		vbcharmwhetstone = new VBox();
		vbcharmsmokebomb = new VBox();
		vbcharmtwinheart = new VBox();
		
		charmheartlbl = new Label("Heart");
		priceheartlbl = new Label("Price: " + getcharmprice("Heart"));
		stockheartlbl = new Label("Stock: " + getcharmstock("Heart"));
		
		charmcoffeelbl = new Label("Coffee");
		pricecoffeelbl = new Label("Price: " + getcharmprice("Coffee"));
		stockcoffeelbl = new Label("Stock: " + getcharmstock("Coffee"));
		
		charmwhetstonelbl = new Label("Whetstone");
		pricewhetstonelbl = new Label("Price: " + getcharmprice("Whetstone"));
		stockwhetstonelbl = new Label("Stock: " + getcharmstock("Whetstone"));
		
		charmsmokebomblbl = new Label("Smoke Bomb");
		pricesmokebomblbl = new Label("Price: " + getcharmprice("Smoke Bomb"));
		stocksmokebomblbl = new Label("Stock: " + getcharmstock("Smoke Bomb"));
		
		charmtwinheartlbl = new Label("Twin Heart");
		pricetwinheartlbl = new Label("Price: " + getcharmprice("Twin Heart"));
		stocktwinheartlbl = new Label("Stock: " + getcharmstock("Twin Heart"));
		
	}
	
	public void setup() {
		ivAppLogo = loadImage("Assets/image/applogo.png");
		
		bp.setTop(menubar);
		menubar.getMenus().add(menu);
		menu.getItems().addAll(MIinventory, MIlogout);
		mainhbox.getChildren().addAll(vboxL, vboxR);
		mainhbox.setAlignment(Pos.CENTER);
		mainhbox.setHgrow(vboxL, Priority.ALWAYS);
		mainhbox.setHgrow(vboxR, Priority.ALWAYS);
		mainhbox.setSpacing(10);
		bp.setCenter(mainhbox);
		
		fp.setMinWidth(720);
		fp.setMaxWidth(720);
		fp.setMinHeight(580);
		fp.setMaxHeight(580);
	    fp.setHgap(10);
	    fp.setVgap(20);
		
		vboxL.getChildren().addAll(carthbox, fp, totalhbox, hbButton);
		vboxL.setPadding(new Insets(10));
		vboxL.setMinWidth(600);
		vboxL.setMinHeight(720);
		
		carthbox.getChildren().add(yourcartlbl);
		carthbox.setAlignment(Pos.CENTER);
		totalhbox.getChildren().add(totallbl);
		totalhbox.setAlignment(Pos.CENTER_LEFT);
		
		hbButton.getChildren().addAll(checkoutbtn, clearcartbtn);
		hbButton.setAlignment(Pos.CENTER);
		hbButton.setSpacing(20);
		
		vboxR.getChildren().addAll(charmshophbox, scrollpane, coinhbox);
		vboxR.setMaxWidth(450);
		vboxR.setMinHeight(720);
		vboxR.setSpacing(10);
		vboxR.setMargin(charmshoplbl, new Insets(10));
		
		scrollpane.setContent(gpshopitem);
		scrollpane.setFitToWidth(true);
		scrollpane.setMaxHeight(600);
		scrollpane.setPannable(true);
		scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		
		gpshopitem.add(vbcharmheart, 0, 0);
		gpshopitem.add(vbcharmcoffee, 1, 0);
		gpshopitem.add(vbcharmwhetstone, 0, 1);
		gpshopitem.add(vbcharmsmokebomb, 1, 1);
		gpshopitem.add(vbcharmtwinheart, 0, 2);
		gpshopitem.setHgap(20);
		gpshopitem.setVgap(25);
		gpshopitem.setMaxHeight(600);
		gpshopitem.setMinWidth(430);
		gpshopitem.setMaxWidth(430);
		
		charmshophbox.getChildren().add(charmshoplbl);
		charmshophbox.setAlignment(Pos.CENTER);
		coinhbox.getChildren().add(coinlbl);
		coinhbox.setAlignment(Pos.CENTER_RIGHT);		
	} 
	
	public void setshop(Stage stage){
		init();
		setup();
		stylecss();
		setEvent();
		setupFlowPane(fp, authenticateduser.getCart());
		setupCharmVBox(gpshopitem);
		
		
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
	
	public void stylecss() {
		bp.setStyle("-fx-background-color: #B05F03;");
		scrollpane.setStyle("-fx-background-color: #B05F03;");
		
		yourcartlbl.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 28px");
		charmshoplbl.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 28px");
		totallbl.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 18px");
		coinlbl.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 18px");
		cartemptylbl.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 24px");
		
		vboxR.setStyle("-fx-background-color: #B05F03;");
		
		gpshopitem.setStyle("-fx-background-color: #B05F03;");
		
		checkoutbtn.setStyle("-fx-background-color: #622F22; -fx-font-size:16px; -fx-font-weight: Bold; -fx-text-fill: #FFFFFF;");
		clearcartbtn.setStyle("-fx-background-color: #622F22; -fx-font-size:16px; -fx-font-weight: Bold; -fx-text-fill: #FFFFFF;");
	}
	
	public void setEvent() {
		MIlogout.setOnAction(e ->{
			Stage shopStage = (Stage) MIlogout.getParentPopup().getOwnerWindow();
	        shopStage.close();
	        
	        LoginPage lp = new  LoginPage(stage, users, charms);
			lp.setlogin(stage);
		});
		
		clearcartbtn.setOnAction(e ->{
			clearCart();
		});
		
		checkoutbtn.setOnAction(e ->{
			checkout();
		});
		
		MIinventory.setOnAction(e ->{
			Stage shopStage = (Stage) MIinventory.getParentPopup().getOwnerWindow();
	        shopStage.close();
	        
			InventoryPage ip = new InventoryPage(stage, users, authenticateduser, charms);
			ip.setinventory(stage);
		});
		
	}
	
    private void setupCharmVBox(GridPane gridPane) {
        for (charm charm : charms) {
            VBox charmVBox = new VBox();
            charmVBox.setAlignment(Pos.CENTER);
            charmVBox.setMinWidth(205);
            charmVBox.setMinHeight(300);
            charmVBox.setSpacing(5);
            charmVBox.setStyle("-fx-background-color: #FF8C00;");
            
            
            Label nameLabel = new Label(charm.getName());
            nameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 20px");
            
            ImageView imageView = loadImage(charm.getImagepath());
            imageView.setOnMouseClicked(e ->{
            	openviewpopup(charm.getName());
            });
            
            Label priceLabel = new Label("Price: " + charm.getPrice());
            priceLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 18px");
            
            Label stockLabel = new Label("Stock: " + charm.getStock());
            stockLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 18px");
            

            charmVBox.getChildren().addAll(nameLabel, imageView, priceLabel, stockLabel);
            setupDragEventForCharm(charmVBox, charm);
            gridPane.add(charmVBox, charms.indexOf(charm) % 2, charms.indexOf(charm) / 2);
        }
        
        gridPane.setHgap(20);
        gridPane.setVgap(25);
        gridPane.setMaxHeight(600);
        gridPane.setMinWidth(430);
        gridPane.setMaxWidth(430);
    }
	 
	private void setupDragEventForCharm(VBox charmVBox, charm charm) {
	        charmVBox.setOnDragDetected(event -> {
	            Dragboard db = charmVBox.startDragAndDrop(TransferMode.COPY);
	            ClipboardContent content = new ClipboardContent();
	            content.putString(charm.getName());
	            db.setContent(content);
	            event.consume();
	        });
	    }
	
	private void setupFlowPane(FlowPane flowPane, ArrayList<cart> cart) {
		if(cart.isEmpty()) {
			fp.getChildren().add(cartemptylbl);
		}
		else {
			updateFlowPane(flowPane, cart);
		}

	    flowPane.setOnDragOver(event -> {
	        if (event.getGestureSource() != flowPane && event.getDragboard().hasString()) {
	            event.acceptTransferModes(TransferMode.COPY);
	        }
	        event.consume();
	    });
	    
	    flowPane.setOnDragDropped(event -> {
	        Dragboard db = event.getDragboard();
	        boolean success = false;
	        if (db.hasString()) {
	            String charmName = db.getString();
	            addToCart(charmName, cart, flowPane);
	            success = true;
	        }
	        event.setDropCompleted(success);
	        event.consume();
	    });
	}

	private void addToCart(String charmName, ArrayList<cart> cart, FlowPane flowPane) {
	    boolean found = false;
	    for (cart item : cart) {
	        if (item.getItemname().equals(charmName)) {
	            item.setItemqty(item.getItemqty() + 1);
	            found = true;
	            break;
	        }
	    }
	    if (!found) {
	        cart.add(new cart(authenticateduser.getName(), authenticateduser.getEmail(), authenticateduser.getPassword(), authenticateduser.getCoins(), charmName, 1));
	    }

	    updateFlowPane(flowPane, cart);
	}

	private void updateFlowPane(FlowPane flowPane, ArrayList<cart> cart) {    
		flowPane.getChildren().clear();
		
	    for (cart item : cart) {
	    	VBox cartvbox = new VBox();
	    	cartvbox.setMinHeight(180);
	    	cartvbox.setMinWidth(230);
	    	cartvbox.setSpacing(10);
	    	cartvbox.setAlignment(Pos.CENTER);
	    	cartvbox.setStyle("-fx-background-color: #FF8C00;");
	    	
	        Label charmnameLabel = new Label(item.getItemname());
	        charmnameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 20px");
	        
	        Label quantityLabel = new Label("Quantity: " + item.getItemqty());
	        quantityLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: Bold; -fx-font-size: 20px");
	        
	        cartvbox.getChildren().addAll(charmnameLabel, quantityLabel);
	        flowPane.getChildren().add(cartvbox);
	        updateTotal(cart);
	    }
	}
	
	private void updateTotal(ArrayList<cart> cart) {
		total = 0;
	    for (cart item : cart) {
	        total += getcharmprice(item.getItemname()) * item.getItemqty();
	    }
	    totallbl.setText("Total: " + total);
	}
	
	private void clearCart() {
	    authenticateduser.clearcart();
	    fp.getChildren().clear();
	    total = 0;
	    totallbl.setText("Total: " + total);
	}
	
	public int getcharmprice(String name) {
		charm Charm = findCharmByName(name);
	    if (name.equals("Heart")) {
	    	return(Charm.getPrice());
	    }
	    else if(name.equals("Coffee")) {
	    	return(Charm.getPrice());
	    }
	    else if(name.equals("Whetstone")) {
	    	return(Charm.getPrice());
	    }
	    else if(name.equals("Smoke Bomb")) {
	    	return(Charm.getPrice());
	    }
	    else if(name.equals("Twin Heart")) {
	    	return(Charm.getPrice());
	    }
	    
	    return 0;
	}
	
	public int getcharmstock(String name) {
		charm Charm = findCharmByName(name);
	    if (name.equals("Heart")) {
	    	return(Charm.getStock());
	    }
	    else if(name.equals("Coffee")) {
	    	return(Charm.getStock());
	    }
	    else if(name.equals("Whetstone")) {
	    	return(Charm.getStock());
	    }
	    else if(name.equals("Smoke Bomb")) {
	    	return(Charm.getStock());
	    }
	    else if(name.equals("Twin Heart")) {
	    	return(Charm.getStock());
	    }
		
		return 0;
	}
	
	public charm findCharmByName(String name) {
	    for (charm c : charms) {
	        if (c.getName().equalsIgnoreCase(name)) {
	            return c;
	        }
	    }
	    return null;
	}
	
	public void checkout() {
		if(authenticateduser.getCart().isEmpty()) {
			showerror("Your Cart is Empty, Cannot Proceed Check Out");
		}
		if(authenticateduser.getCoins() < total) {
			showerror("Insufficent Coins!");
			return;
		}
		
		for (cart item : authenticateduser.getCart()) {
	        for (charm c : charms) {
	            if (c.getName().equals(item.getItemname())) {
	                if (c.getStock() < item.getItemqty()) {
	                    showerror("Charm's Stock is not enough");
	                    return;
	                }
	                break;
	            }
	        }
	    }
		
	    for (cart item : authenticateduser.getCart()) {
	        for (charm c : charms) {
	            if (c.getName().equals(item.getItemname())) {
	                c.setStock(c.getStock() - item.getItemqty());
	                break;
	            }
	        }
	        
	        boolean found = false;
	        for (inventory invItem : authenticateduser.getInv()) {
	            if (invItem.getItemname().equals(item.getItemname())) {
	                invItem.setItemqty(invItem.getItemqty() + item.getItemqty());
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            authenticateduser.getInv().add(new inventory(authenticateduser.getName(), authenticateduser.getEmail(), authenticateduser.getPassword(), authenticateduser.getCoins(), item.getItemname(), item.getItemqty()));
	        }
	    }

	    authenticateduser.setCoins(authenticateduser.getCoins() - total);
	    coinlbl.setText("Coins: " + authenticateduser.getCoins());
	    setupCharmVBox(gpshopitem);
	    clearCart();

	    
	}
	
	public void showerror(String text) {
		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(text);
		a.show();
	}
	
	public void openviewpopup(String charmname) {
		ViewCharmPage vcp = new ViewCharmPage(charms, charmname);
	}
	
}
