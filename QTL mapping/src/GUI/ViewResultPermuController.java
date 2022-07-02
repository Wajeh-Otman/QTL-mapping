package GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * 
 * 
 *  This class represents a controller for the gui View Result Permu Controller
 *
 */
public class ViewResultPermuController implements Initializable{
	
	public static Stage primaryStage;
	static ArrayList<String> implemented_tests=new ArrayList<>();
	@FXML
    private Button NewSerach;
	  @FXML
	    private Button ShowRes;
	  @FXML
	    private Label resultStudent; 

	    @FXML
	    private Label resultF;
	  
	    @FXML
	    private Label resultANOVA;
	
	@FXML
    private Button LogOutBtn;
/**
 * 
 * @param event of button
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
    void newSearch(ActionEvent event) {
    	InputController inputController= new InputController();
    	inputController.start(primaryStage);
    }
	/**
	 * 
	 * @param event of button
	 */
    @FXML
    void ShowResult(ActionEvent event) {
    	resultStudent.setText(implemented_tests.get(0));
		resultStudent.setVisible(true);	
		resultF.setText(implemented_tests.get(1));
		resultF.setVisible(true);
		resultANOVA.setText(implemented_tests.get(2));
		resultANOVA.setVisible(true);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
			root = FXMLLoader.load(getClass().getResource("ViewResultPermu.fxml"));
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
	 * @param List_tests contain the results
	 */
	public void setList(ArrayList List_tests) {
		
    	implemented_tests=List_tests;
    	System.out.println("after");
		for(String t:implemented_tests)
			System.out.println(t);
		
	}
}
