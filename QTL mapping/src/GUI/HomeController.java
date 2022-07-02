package GUI;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * 
 *  This class represents a controller for the gui HomeController
 *
 */

public class HomeController extends Application implements Initializable{
	
	public static Stage primaryStage;
	public CreateAccountController createAcc;
	public LogInController logIn;
	
	
    @FXML
    private Button SignInBtn;

    @FXML
    private Button CreateAccBtn;
    
    /** This method load the information into the gui
	*
	*
	* @param primaryStage  appearance of the software
	*/

    public void start(Stage primaryStage) {
    	
    	

		this.primaryStage = primaryStage;
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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
    
/**
 *  
 * @param event of button
 */

	@FXML
    void CreateAccount(ActionEvent event) {
		System.out.println("Create accout");
		createAcc = new CreateAccountController();
		createAcc.start(primaryStage);
    }
	/**
	 *  
	 * @param event of button
	 */
    @FXML
    void SignIn(ActionEvent event) {
    	System.out.println("log in");
    	logIn = new LogInController();
    	logIn.start(primaryStage);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
