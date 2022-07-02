package GUI;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotActiveException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * 
 * 
 *  This class represents a controller for the gui ViewResultController
 *
 */

public class ViewResultController implements Initializable{
	
	public static Stage primaryStage,stage;
	public HomeController homeController;
	static ArrayList<String> implemented_tests=new ArrayList<>();
	public Result result;
	public File file ;
	public Scanner sc ;
	public String line,path="Results\\";;
	ArrayList<Result> resultList=new ArrayList<>();
	
	@FXML
    private Button NewSerach;
	
	@FXML
    private ImageView ViewImg;
	
    @FXML
    private Button LogOutBtn;
    
    @FXML
    private TableView<Result> table;
    @FXML
    private TableColumn<Result, String> ColMarker;

    @FXML
    private TableColumn<Result, String> ColChr;

    @FXML
    private TableColumn<Result, String> ColPos;

    @FXML
    private TableColumn<Result, String> Colp;

    @FXML
    private TableColumn<Result, String> Colq;

    @FXML
    private TableColumn<Result, String> ColSignif;
    
    @FXML
    private ComboBox<String> DisplayFileCombox;;

    @FXML
    private ComboBox<String> comboDisplayMode;
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
    void LogOut(ActionEvent event) {
    	homeController = new HomeController();
    	homeController.start(primaryStage);
    }
    /**
     * 
     * @param event  of button
     * @throws Exception when the file is not exist
     */
    @FXML
    void displayMode(ActionEvent event) throws Exception {
    	
    	String selectedValue = comboDisplayMode.getValue();
    	System.out.println(selectedValue);
    	if (selectedValue.equals("Graph mode")) {
    		DisplayFileCombox.setVisible(false);
    		table.setVisible(false);
    	
    		FileInputStream imgResult = null;
			try {
				imgResult = new FileInputStream("Results\\tests result.png");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
    		Image image = new Image(imgResult);
    		
    		ViewImg.setImage(image);
    		ViewImg.setLayoutX(200);
    		ViewImg.setLayoutY(90);
    		ViewImg.setFitWidth(1000);
    		ViewImg.setFitHeight(400);
    		//ViewImg.setVisible(true);
    		ViewImg.setPreserveRatio(true);
    	}
    	else {
    		DisplayFileCombox.setVisible(true);
			table.setVisible(true); 
		}
    }
    /**
     * 
     * @param event of button
     * @throws Exception when the file is not exist
     */
    @FXML
    void displayFile(ActionEvent event) throws Exception {
    	resultList.clear();
    
    	String display=DisplayFileCombox.getValue();
    	table.getItems().clear();
    	file = new File(path+display+".txt");
    	sc = new Scanner(file);
    	line = sc.nextLine();
    	Result result;
    	while (sc.hasNextLine()) {
    		line = sc.nextLine();
    		String[] s= line.split("\t");
    	
    		result = new Result();
    		result.setmarker(s[0]);
    		result.setchr(s[1]);
    		result.setpos(s[2]);
    		result.setpvalue(s[3]);
    		result.setqvalue(s[4]);
    		result.setsignjf(s[5]);
    		//list = result
    		resultList.add(result);	
    	}
    	
    	ObservableList<Result> details = FXCollections.observableArrayList(resultList);
    	ColMarker.setCellValueFactory(cd->cd.getValue().markerProperty());
    	ColChr.setCellValueFactory(cd->cd.getValue().chrProperty());
    	ColPos.setCellValueFactory(cd->cd.getValue().posProperty());
    	Colp.setCellValueFactory(cd->cd.getValue().pvalueProperty());
    	Colq.setCellValueFactory(cd->cd.getValue().qvalueProperty());
    	ColSignif.setCellValueFactory(cd->cd.getValue().signjfProperty());	
    	System.out.println(resultList.toString());
    	table.setItems(details);
    }
    
    /** This method load the information into the gui
  	*
  	*
  	* @param primaryStage  appearance of the software
  	*/
    public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("ViewResult.fxml"));
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
    
    public void setList(ArrayList List_tests) {
    	implemented_tests=List_tests;
    }
    

	public Stage getStage() {
		return primaryStage;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboDisplayMode.getItems().removeAll(comboDisplayMode.getItems());
		DisplayFileCombox.getItems().removeAll(DisplayFileCombox.getItems());
		comboDisplayMode.getItems().addAll("Graph mode","Table mode");
		DisplayFileCombox.setVisible(false);
		DisplayFileCombox.getItems().addAll(implemented_tests);
		table.setVisible(false);
	}

}
