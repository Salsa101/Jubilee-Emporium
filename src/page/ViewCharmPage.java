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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.charm;
import main.user;

public class ViewCharmPage {
	private ArrayList<charm> charms;
	private String charmname;
	private Stage stage;
	
	double rotationcount = 0;
	double scalecount = 1;
	
	
	StackPane stackpane;
	BorderPane bp;
	HBox buttoncontainer;
	VBox vbox;
	
	Button zoominbtn, zoomoutbtn, rotateleftbtn, rotaterightbtn;
	ImageView ivAppLogo, charmimage;
	Label charmnamelbl, charmdesclbl;

	public ViewCharmPage(ArrayList<charm> charms, String charmname) {
		this.charms = charms;
		this.charmname = charmname;
		setviewcharmpage();
	}
	
	public void init() {
		stackpane = new StackPane();
		bp = new BorderPane();
		buttoncontainer = new HBox();
		vbox = new VBox();
		
		zoominbtn = new Button("+");
		zoomoutbtn = new Button("-");
		rotateleftbtn = new Button("<-");
		rotaterightbtn = new Button("->");
		
		zoominbtn.setMinSize(35, 25);
	    zoomoutbtn.setMinSize(35, 25);
	    rotateleftbtn.setMinSize(35, 25);
	    rotaterightbtn.setMinSize(35, 25);
		
		charmnamelbl = new Label();
		charmdesclbl = new Label();
	}
	
	public void setup() {
		ivAppLogo = loadImage("Assets/image/applogo.png");
		charmimage.setFitWidth(300);
		charmimage.setFitHeight(300);
		charmimage.setPreserveRatio(true);
		
		bp.setCenter(stackpane);
		bp.setAlignment(stackpane, Pos.CENTER);
		bp.setPadding(new Insets(10));
		
		stackpane.getChildren().add(vbox);
		stackpane.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(charmnamelbl, charmimage, charmdesclbl,  buttoncontainer);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(30);
		
		buttoncontainer.getChildren().addAll(zoominbtn, zoomoutbtn, rotateleftbtn, rotaterightbtn);
		buttoncontainer.setAlignment(Pos.CENTER);
		buttoncontainer.setSpacing(10);
		
		charmdesclbl.setMaxWidth(600);
		charmdesclbl.setWrapText(true);
		charmdesclbl.setTextAlignment(TextAlignment.CENTER);
	}
	
	
	public void setviewcharmpage() {
		init();
		charmviewsetup(charmname);
		setup();
		stylecss();
		setEvent();
		
		stage = new Stage();
		
		Scene scene = new Scene(bp, 800, 600);
		stage.setScene(scene);
		if(ivAppLogo.getImage() != null) {
			stage.getIcons().add(ivAppLogo.getImage());
		}
		stage.setTitle("Jubilee Emporium");
		stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
	}
	
	public void charmviewsetup(String charmname) {
		for(charm c : charms) {
			if(c.getName() == charmname) {
				charmimage = loadImage(getimagepath(charmname));
				charmnamelbl.setText(c.getName());
				charmdesclbl.setText(c.getDescription());
			}
		}
	}
	
	private ImageView loadImage(String url) {
		File file = new File(url);
		return new ImageView(file.toURI().toString());
	}
	
	private void stylecss() {
		bp.setStyle("-fx-background-color: #FF8C00");
		zoominbtn.setStyle("-fx-background-color: #622F22; -fx-font-size:16px; -fx-font-weight: Bold; -fx-text-fill: #FFFFFF;");
		zoomoutbtn.setStyle("-fx-background-color: #622F22; -fx-font-size:16px; -fx-font-weight: Bold; -fx-text-fill: #FFFFFF;");
		rotateleftbtn.setStyle("-fx-background-color: #622F22; -fx-font-size:16px; -fx-font-weight: Bold; -fx-text-fill: #FFFFFF;");
		rotaterightbtn.setStyle("-fx-background-color: #622F22; -fx-font-size:16px; -fx-font-weight: Bold; -fx-text-fill: #FFFFFF;");
		charmnamelbl.setStyle("-fx-font-weight: Bold; -fx-text-fill: #FFFFFF; -fx-font-size: 32px");
		charmdesclbl.setStyle("-fx-font-weight: Bold; -fx-text-fill: #FFFFFF; -fx-font-size: 30px");
		
	}
	
	public void setEvent() {
		zoominbtn.setOnMouseClicked(e ->{
			scaleimage(charmimage, 1.1);
		});
		
		zoomoutbtn.setOnMouseClicked(e ->{
			scaleimage(charmimage, 0.9);
		});
		
		rotateleftbtn.setOnMouseClicked(e -> {
			rotateimage(charmimage, -15);
		});
		
		rotaterightbtn.setOnMouseClicked(e -> {
			rotateimage(charmimage, 15);
		});
	}
	
	private void scaleimage(ImageView image, double num) {
        double newscale = scalecount * num;

        if (newscale >= 1.5 || newscale < 0.75) {
            return;
        }

        scalecount = newscale;
        transformimage(image);
    }

    private void rotateimage(ImageView image, double degree) {
        rotationcount += degree;
        transformimage(image);
    }

    private void transformimage(ImageView image) {
        image.getTransforms().clear();

        Scale scale = new Scale(scalecount, scalecount, image.getBoundsInLocal().getWidth() / 2, image.getBoundsInLocal().getHeight() / 2);
        Rotate rotate = new Rotate(rotationcount, image.getBoundsInLocal().getWidth() / 2, image.getBoundsInLocal().getHeight() / 2);

        image.getTransforms().addAll(scale, rotate);
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
