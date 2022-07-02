package GUI;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LogInController implements Initializable{
	
	public static Stage primaryStage;
	public InputController inputCon;
	ArrayList<User> userList = new ArrayList<User>();
	
	
    @FXML
    private Button LogInBtn;

    @FXML
    private TextField Passwordtxt;

    @FXML
    private TextField UserNametxt;

    @FXML
    private Button ForgetPassBtn;

    @FXML
    void ForgetPass(ActionEvent event) {
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setAlertType(AlertType.INFORMATION); 
				alert.setContentText("Send An Email for technical support \nEmail:tech-support@gmail.com \nPhone Number:054-1234567");
				alert.show(); 
			}
		});
    }

    @FXML
    void LogIn(ActionEvent event) throws Exception {
    	String username=UserNametxt.getText();
    	String passwordString=Passwordtxt.getText();
    	
    	
    	
    	User user=null;
    	boolean found=false;
    	
    	inputCon = new InputController();
    	File file = new File("src\\GUI\\DataBase.txt");
    	FileReader fr=new FileReader(file);   //reads the file  
    	BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
    	StringBuffer sb=new StringBuffer();  
    	String line; 
    	String []linee;
    	
    	if (UserNametxt.getText().isEmpty()||Passwordtxt.getText().isEmpty()) 
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
    	
    	else {
    		while((line=br.readLine())!=null)  
        	{  	
        		linee=line.split(",");
        		user= new User(linee[0],linee[1],linee[2],linee[3],linee[4]);
        		userList.add(user);
        		
        	}  
        	
        	for(User temp:userList) {
        		if (temp.getUserName().equals(username)&& temp.getPassword().equals(passwordString)) {
        			found=true;
        			inputCon.start(primaryStage);
    			}
        	}
        	if (!found) {
            	Platform.runLater(new Runnable() {
        			@Override
        			public void run() {
        				Alert alert = new Alert(AlertType.ERROR);
        				alert.setAlertType(AlertType.ERROR); 
        				alert.setContentText("User name or password not valid");
        				alert.show(); 
        			}
        		});
    		}
		}
    	
    	
    }
    
    public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
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
