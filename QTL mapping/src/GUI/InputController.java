package GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * This class represents a controller for the gui InputController
 *
 */
public class InputController implements Initializable{
	
	public static Stage primaryStage;
	
	private File Geno,Trait,Map;
	
	@FXML
    private Button AutofillBtn;
	@FXML
    private Button LogOutBtn;

    @FXML
    private TextField GenoBox;

    @FXML
    private Button NextStepBtn;

    @FXML
    private TextField geneticMapBox;

    @FXML
    private TextField TraitBox;

    @FXML
    private Button DisplayTraitBtn;

    @FXML
    private Button DisplayGenoBtn;

    @FXML
    private Button DisplayMapBtn;

    @FXML
    private Button UploadTraitBtn;

    @FXML
    private Button UploadGenoBtn;

    @FXML
    private Button UploadMapBtn;

    @FXML
    private ComboBox<String> inputTypecombox;

    @FXML
    private Label LableChrLen;

    @FXML
    private TextField ChrLengthtxt;

    @FXML
    private Label LableNumMarkers;

    @FXML
    private TextField NumMarkerstxt;

    @FXML
    private Label LableMeanVar;

    @FXML
    private TextField meantxt;

    @FXML
    private TextField vartxt;

    @FXML
    private Label LableNumQTL;

    @FXML
    private TextField numQTLtxt;

    @FXML
    private Label LableAlleMean;
    
    @FXML
    private Label LableAlleVar;
    
    @FXML
    private TextField AlleMeantxt;

    @FXML
    private TextField AlleVartxt;
    
    
    /**
     * 
     * @param event  of button
     */
    @FXML
    void LogOut(ActionEvent event) {
    	HomeController  homeController = new HomeController();
    	homeController.start(primaryStage);

    }
    /**
     * 
     * @param event of button
     */
    @FXML
    void inputTypecobox(ActionEvent event) {
    	AutofillBtn.setVisible(true);
    	String selectedValue = inputTypecombox.getValue();
    	//if Run simulator was selected show only the relevant buttons
		if(selectedValue.equals("Run simulator")) {
			GenoBox.setVisible(false);
			geneticMapBox.setVisible(false);
			TraitBox.setVisible(false);
			DisplayTraitBtn.setVisible(false);
			DisplayGenoBtn.setVisible(false);
			DisplayMapBtn.setVisible(false);
			UploadTraitBtn.setVisible(false);
			UploadGenoBtn.setVisible(false);
			UploadMapBtn.setVisible(false);
			ChrLengthtxt.setVisible(true);
			NumMarkerstxt.setVisible(true);
			meantxt.setVisible(true);
			vartxt.setVisible(true);
			numQTLtxt.setVisible(true);
			AlleMeantxt.setVisible(true);
			AlleVartxt.setVisible(true);
			LableAlleMean.setVisible(true);
			LableAlleVar.setVisible(true);
			LableChrLen.setVisible(true);
			LableNumMarkers.setVisible(true);
			LableMeanVar.setVisible(true);
			LableNumQTL.setVisible(true);
		}
		else {//if upload file was selected show only the relevant buttons
			GenoBox.setVisible(true);
			geneticMapBox.setVisible(true);
			TraitBox.setVisible(true);
			DisplayTraitBtn.setVisible(true);
			DisplayGenoBtn.setVisible(true);
			DisplayMapBtn.setVisible(true);
			UploadTraitBtn.setVisible(true);
			UploadGenoBtn.setVisible(true);
			UploadMapBtn.setVisible(true);
			ChrLengthtxt.setVisible(false);
			NumMarkerstxt.setVisible(false);
			meantxt.setVisible(false);
			vartxt.setVisible(false);
			numQTLtxt.setVisible(false);
			AlleMeantxt.setVisible(false);
			AlleVartxt.setVisible(false);
			LableAlleMean.setVisible(false);
			LableAlleVar.setVisible(false);
			LableChrLen.setVisible(false);
			LableNumMarkers.setVisible(false);
			LableMeanVar.setVisible(false);
			LableNumQTL.setVisible(false);
			
		}

    }
    /**
     * 
     * @param event of button
     */
    @FXML
    void Autofill(ActionEvent event) {
    	String selectedValue = inputTypecombox.getValue();
    	//autofill input for run simulator
		if(selectedValue.equals("Run simulator")) {
			ChrLengthtxt.setText("120");
			NumMarkerstxt.setText("13");
			meantxt.setText("170");
			vartxt.setText("400");
			numQTLtxt.setText("1");
			AlleMeantxt.setText("10");
			AlleVartxt.setText("-300");
		}
		else {
			//autofill input for upload file
			Geno = new File("pythProject\\genotypesExample.txt");
			Trait = new File("pythProject\\traitsExample.txt");   
			Map = new File("pythProject\\geneticMap.txt");   
			GenoBox.setText(Geno.getName());
			geneticMapBox.setText(Map.getName());
			TraitBox.setText(Trait.getName());
			
		}
    	

    }
    
    /**
     * 
     * @param event of button
     * @throws Exception in case one or more text filed is empty
     */

    @FXML
    void NextStep(ActionEvent event) throws Exception {
    	
    	String selectedValue = inputTypecombox.getValue();
    	Data data = null;
    	
    	
		if(selectedValue.equals("Run simulator")) {//get the input data for run simulator
			if (ChrLengthtxt.getText().isEmpty()||NumMarkerstxt.getText().isEmpty()||meantxt.getText().isEmpty()||vartxt.getText().isEmpty()||numQTLtxt.getText().isEmpty()||AlleMeantxt.getText().isEmpty()||AlleVartxt.getText().isEmpty()) {
				Platform.runLater(new Runnable() {
	    			@Override
	    			public void run() {
	    				Alert alert = new Alert(AlertType.INFORMATION);
	    				alert.setAlertType(AlertType.INFORMATION); 
	    				alert.setContentText("There is one or more empty field");
	    				alert.show(); 
	    			}
	    		});
				
			}
			else {//all the data was valid then create data object with the input data
				data =new Data (null,null,null,null,false,false,false,false,false,false,"sim",ChrLengthtxt.getText(),NumMarkerstxt.getText(),meantxt.getText(),vartxt.getText(),numQTLtxt.getText(),AlleMeantxt.getText(),AlleVartxt.getText());
				FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchQTL.fxml"));
		    	Parent root =loader.load();
		    	SearchQTLController sreachQTL=loader.getController();
		    	sreachQTL.setData(data);
		    	sreachQTL.start(primaryStage);
			}
		}
		else {//get the input data for upload file
			if (GenoBox.getText().isEmpty()||geneticMapBox.getText().isEmpty()||TraitBox.getText().isEmpty()) {
				Platform.runLater(new Runnable() {
	    			@Override
	    			public void run() {
	    				Alert alert = new Alert(AlertType.INFORMATION);
	    				alert.setAlertType(AlertType.INFORMATION); 
	    				alert.setContentText("There is one or more empty field");
	    				alert.show(); 
	    			}
	    		});
			}
			else {//all the data was valid then create data object with the input data
				data =new Data (Map.getPath(),Geno.getPath(),Trait.getPath(),null,false,false,false,false,false,false,"file",ChrLengthtxt.getText(),NumMarkerstxt.getText(),meantxt.getText(),vartxt.getText(),numQTLtxt.getText(),AlleMeantxt.getText(),AlleVartxt.getText());
				FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchQTL.fxml"));
		    	Parent root =loader.load();
		    	SearchQTLController sreachQTL=loader.getController();
		    	sreachQTL.setData(data);
		    	sreachQTL.start(primaryStage);
			}
		}
    	
    }
	
    /**
     * initialize the parameters
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AutofillBtn.setVisible(false);
		inputTypecombox.getItems().removeAll(inputTypecombox.getItems());
		inputTypecombox.getItems().addAll("Run simulator","Upload Files");
		GenoBox.setVisible(false);
		geneticMapBox.setVisible(false);
		TraitBox.setVisible(false);
		DisplayTraitBtn.setVisible(false);
		DisplayGenoBtn.setVisible(false);
		DisplayMapBtn.setVisible(false);
		UploadTraitBtn.setVisible(false);
		UploadGenoBtn.setVisible(false);
		UploadMapBtn.setVisible(false);
		ChrLengthtxt.setVisible(false);
		NumMarkerstxt.setVisible(false);
		meantxt.setVisible(false);
		vartxt.setVisible(false);
		numQTLtxt.setVisible(false);
		AlleMeantxt.setVisible(false);
		AlleVartxt.setVisible(false);
		LableAlleMean.setVisible(false);
		LableAlleVar.setVisible(false);
		LableChrLen.setVisible(false);
		LableNumMarkers.setVisible(false);
		LableMeanVar.setVisible(false);
		LableNumQTL.setVisible(false);
	}

	   /** This method load the information into the gui
		*
		*
		* @param primaryStage  appearance of the software
		*/
	public void start(Stage primaryStage)  {
		this.primaryStage = primaryStage;
		
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("Input.fxml"));
			Scene scene = new Scene(root);
			this.primaryStage.setScene(scene);
			this.primaryStage.setResizable(false);
			this.primaryStage.setTitle("QTL MAPPING");
			this.primaryStage.show();
			this.primaryStage.setOnCloseRequest(event -> {
				System.exit(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param event of button manually open file
	 */
	@FXML
	public void displayMap(ActionEvent event) {
		try  
		{  
		//constructor of file class having file as argument  
			File file = Map;   
			if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
			{  
				System.out.println("not supported");  
				return;  
			}  
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists())         //checks file exists or not  
				desktop.open(file);              //opens the specified file  
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
		
	}
/**
 * 
 * @param event of button manually open file
 */
	@FXML
	public void displayGeno(ActionEvent event) {
		try  
		{  
		//constructor of file class having file as argument  
			File file = Geno;   
			if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
			{  
				System.out.println("not supported");  
				return;  
			}  
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists())         //checks file exists or not  
				desktop.open(file);              //opens the specified file  
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
		
	}
	/**
	 * 
	 * @param event of button manually open file
	 */
	@FXML
	public void displayTrait(ActionEvent event) {
		try  
		{  
		//constructor of file class having file as argument  
			File file = Trait;   
			if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
			{  
				System.out.println("not supported");  
				return;  
			}  
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists())         //checks file exists or not  
				desktop.open(file);              //opens the specified file  
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	
	}
	
	/**
	 * 
	 * @param event of button manually upload file
	 * @throws IOException
	 */
	@FXML
	public void uploadMap(ActionEvent event)  {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		Map = fileChooser.showOpenDialog(primaryStage);
		geneticMapBox.setText(Map.getName());
		System.out.println(Map.getPath());
	
		
	}
	/**
	 * 
	 * @param event manually upload file
	 *
	 */
	@FXML
	public void uploadGeno(ActionEvent event)  {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		Geno = fileChooser.showOpenDialog(primaryStage);
		GenoBox.setText(Geno.getName());
		System.out.println(Geno.getPath());
		
		
	}
	/**
	 * 
	 * @param event of button manually upload file
	 */
	@FXML
	public void uploadTrait(ActionEvent event)  {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		Trait = fileChooser.showOpenDialog(primaryStage);
		TraitBox.setText(Trait.getName());
		System.out.println(Trait.getPath());
		
	
	}

}
