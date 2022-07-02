package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * 
 * 
 * This class represents a controller for the gui Create Account Controller
 *
 */
public class CreateAccountController implements Initializable{
	
	FileWriter fw = null;
	BufferedWriter bw = null;
	PrintWriter pw = null;


	public static Stage primaryStage;
	public HomeController homeController;
	
    @FXML
    private Button CreateBtn;
    
    @FXML
    private Button BackBtn;

    @FXML
    private TextField FirstNametxt;

    @FXML
    private TextField LastNametxt;

    @FXML
    private TextField ConfirmPasswordtxt;

    @FXML
    private TextField UserNametxt;;

    @FXML
    private TextField Passwordtxt;
    
    /**
     * 
     * @param  event of button
     */
    @FXML
    void GoBack(ActionEvent event) {
    	homeController = new HomeController();
    	homeController.start(primaryStage);

    }
    
    /**
     * 
     * @param event of button
     * @throws Exception in case the username is already exist in database
     */

    @FXML
    void CreateAccount(ActionEvent event) throws Exception {
    	homeController = new HomeController();
    	boolean allDataValid=true;
    	File file = new File("C:\\Users\\wajeh otman\\eclipseProjects\\FinalProject\\src\\GUI\\DataBase.txt");
    	FileReader fr=new FileReader(file);   //reads the file  
    	BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
    	StringBuffer sb=new StringBuffer();  
    	String line = null,username=UserNametxt.getText(); 
    	String []linee;
    	
    	while((line=br.readLine())!=null)  {  	
    		linee=line.split(",");
    		if (username.equals(linee[2])) {
    			allDataValid=false;
    			Platform.runLater(new Runnable() {
        			@Override
        			public void run() {
        				Alert alert = new Alert(AlertType.ERROR);
        				alert.setAlertType(AlertType.ERROR); 
        				alert.setContentText("User name already used!!");
        				alert.show(); 
        			}
        		});
				
			}
    		
    		
    	}
    	//in case one textfield or more is empty
    	if (FirstNametxt.getText().isEmpty()||LastNametxt.getText().isEmpty()||UserNametxt.getText().isEmpty()||Passwordtxt.getText().isEmpty()||ConfirmPasswordtxt.getText().isEmpty()) 
    	{
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
    	
    	//in case password confirmation is failed
    	else if(!Passwordtxt.getText().equals(ConfirmPasswordtxt.getText()))
    	{
 
    		
    		Platform.runLater(new Runnable() {
    			@Override
    			public void run() {
    				Alert alert = new Alert(AlertType.ERROR);
    				alert.setAlertType(AlertType.ERROR); 
    				alert.setContentText("Password Not matches!");
    				alert.show(); 
    			}
    		});
    	}
    	
    	//in case that all the input data for the new account is valid
    	else if(allDataValid){
    		User user = new User(FirstNametxt.getText(),LastNametxt.getText(),UserNametxt.getText(),Passwordtxt.getText(),ConfirmPasswordtxt.getText());
    		System.out.println(user.toString());
    		fw = new FileWriter("C:\\Users\\wajeh otman\\eclipseProjects\\FinalProject\\src\\GUI\\DataBase.txt", true);
    		bw = new BufferedWriter(fw);
    		pw = new PrintWriter(bw);
    		pw.println(user.getFirstName()+","+user.getLastName()+","+user.getUserName()+","+user.getPassword()+","+user.getConPassword());

    		System.out.println("Data successfully appended into file");
    		pw.flush();	
    		homeController.start(primaryStage);
		}
    	
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setAlertType(AlertType.CONFIRMATION); 
				alert.setContentText("Account successfully created");
				alert.show(); 
			}
		});
    	
    	
    		
    }
    /** This methode load the information into the gui
	*
	*
	* @param primaryStage  appearance of the software
	*/
    public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
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

	public Stage getStage() {
		return primaryStage;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
