package GUI;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** This method load the information into the gui
	*
	*
	* @param primaryStage  appearance of the software
	*/
public class SearchQTLController implements Initializable{
		///Data data=new Data("null", "null"', 'null', Single, false, true, true, 'false', 'false', 'true', 'sim', '120', '13', '170', '400', '1', '10', '-300')
		public static Stage primaryStage;
		public int count=0;
		boolean take=false;
		
		public static Data data;
		
		
	    @FXML
	    private Text messageTEXT;
	
	    @FXML
	    private Button calculteBtn;
	    
	    @FXML
	    private Button LogOutBtn;
	    
	    @FXML
	    private Button SelectAllBtn;

	    @FXML
	    private ComboBox<String> comboTest;

	    @FXML
	    private CheckBox StudentTestCeBox;

	    @FXML
	    private CheckBox PermuTestCeBox;
	    
	    @FXML
	    private CheckBox VarPermuTestCeBox;

	    @FXML
	    private CheckBox ANOVACeBox;

	    @FXML
	    private CheckBox KruskalWalisCeBox;

	    @FXML
	    private CheckBox FtestCeBox;
	    
	    /**
	     * 
	     * @param event of button
	     */
	    @FXML
	    void SelectAll(ActionEvent event) {
	    	if (count%2==0) {
	    		StudentTestCeBox.setSelected(true);
		    	PermuTestCeBox.setSelected(true);
		    	VarPermuTestCeBox.setSelected(true);
		    	ANOVACeBox.setSelected(true);
		    	KruskalWalisCeBox.setSelected(true);
		    	FtestCeBox.setSelected(true);
		    	
			}
	    	else {
	    		StudentTestCeBox.setSelected(false);
		    	PermuTestCeBox.setSelected(false);
		    	VarPermuTestCeBox.setSelected(false);
		    	ANOVACeBox.setSelected(false);
		    	KruskalWalisCeBox.setSelected(false);
		    	FtestCeBox.setSelected(false);
				
			}
	    	count++;
	    	
	    }
	    /**
	     * 
	     * @param event of button
	     * @throws Exception when no test is selected
	     */
	    @FXML
	    void Calculate(ActionEvent event) throws Exception{
	    	ArrayList<String> implemented_tests=new ArrayList<>();
	    	ArrayList<String> implemented_testsChrWise=new ArrayList<>();
	    	int conut=3;
	    	if (StudentTestCeBox.isSelected() == false && PermuTestCeBox.isSelected() == false &&VarPermuTestCeBox.isSelected() == false&& ANOVACeBox.isSelected() == false && KruskalWalisCeBox.isSelected() == false && FtestCeBox.isSelected() == false) {
	    		Platform.runLater(new Runnable() {
        			@Override
        			public void run() {
        				Alert alert = new Alert(AlertType.ERROR);
        				alert.setAlertType(AlertType.ERROR); 
        				alert.setContentText("You must select at least one test");
        				alert.show(); 
        			}
        		});
			}
	    	else {
	    		data.setStudent_Test(StudentTestCeBox.isSelected());
	    		data.setPermutation_Test(PermuTestCeBox.isSelected());
	    		data.setPermutationVAR_Test(VarPermuTestCeBox.isSelected());
	    		data.setF_Test(FtestCeBox.isSelected());
	    		data.setANOVA_Test(ANOVACeBox.isSelected());
	    		data.setKruskalWalis_Test(KruskalWalisCeBox.isSelected());
	    		
		    	if (comboTest.getValue()=="Single marker test") {
		    		data.setTestName("Single");
				}
		    	System.out.println("*****************");
		    	System.out.println(data.toString());
		    	System.out.println("*****************");
		    	
		    	try {
					String s=null;
					//ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\Public\\a1.py", d.getGeneticMapPath(),d.getGenotypePath(),d.getTraitPath(),d.getTestName()); /*Or python3*/
					//Process process = pb.start();
					//String absolutePath = new File("pythProject//MAIN.py").getAbsolutePath();
					Process process=Runtime.getRuntime().exec("python pythProject\\MAIN.py " + " "+ data.getGeneticMapPath() +" "+ data.getGenotypePath() +" "+
					data.getTraitPath() +" "+ data.getTestName() +" "+ data.getStudent_Test() +" "+ data.getPermutation_Test() +" "+data.getPermutationVAR_Test() +" "+ data.getF_Test()  +" "+
					data.getKruskalWalis_Test() +" "+ data.getANOVA_Test() +" "+ data.getInputType()  +" "+ data.getChrLength() +" "+ data.getNumMarkers() +" "+
					data.getMean() +" "+ data.getVar() +" "+ data.getNumQTL() +" "+ data.getAlleMean() +" "+ data.getAlleVar());
					BufferedReader in=new BufferedReader(new InputStreamReader(process.getInputStream()));
					while ((s = in.readLine()) != null) {
						System.out.println(s);
						if (take) {
							implemented_testsChrWise.add(s);
							conut--;
							if (conut==0) {
								take=false;
							}	
						}
						if (s.equals("Chromosomal Permutation tests result:")) {
							take=true;
						}
					}
				} catch (StringIndexOutOfBoundsException  e) {
					// TODO: handle exception
				}
		    	
		    	if (data.getStudent_Test()) {
		    		
		    		implemented_tests.add("SMT_Student_test");
				}
		    	if (data.getPermutation_Test()) {
		    		implemented_tests.add("SMT_Permutation_test");
				}
		    	if (data.getPermutationVAR_Test()) {
		    		implemented_tests.add("SMT_Permutation_VAR_test");
				}
		    	if (data.getF_Test()) {
		    		implemented_tests.add("SMT_f_test");
				}
		    	if (data.getKruskalWalis_Test()) {
		    		implemented_tests.add("SMT_Kruskal_Walis_test");
				}
		    	if (data.getANOVA_Test()) {
		    		implemented_tests.add("SMT_ANOVA_test");
				}
		    	
		    	
		    	
		    	if(comboTest.getValue()=="Single marker test") {
		    		FXMLLoader loader=new FXMLLoader(getClass().getResource("ViewResult.fxml"));
			    	Parent root =loader.load();
			    	ViewResultController viewResult=loader.getController();
			    	viewResult.setList(implemented_tests);
			    	viewResult.start(primaryStage);
		    	}
		    	else {
		    		FXMLLoader loader1=new FXMLLoader(getClass().getResource("ViewResultPermu.fxml"));
			    	Parent root =loader1.load();
			    	ViewResultPermuController viewResultPermu=loader1.getController();
			    	viewResultPermu.setList(implemented_testsChrWise);
			    	viewResultPermu.start(primaryStage);
				}
		    	
			}

	    }
	    
	    public void setData(Data d) {
	    	data=d;
	    	
	    }
	    /**
	     * 
	     * @param event of button
	     */
	    @FXML
	    void LogOut(ActionEvent event) {
	    	HomeController  homeController = new HomeController();
	    	homeController.start(primaryStage);

	    }
	    	    
		public Stage getStage() {
			return primaryStage;
		}
		
		/**
		 * initialize the parameters
		 */
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			comboTest.getItems().removeAll(comboTest.getItems());
			comboTest.getItems().addAll("Single marker test","Chromosome wise test");
			StudentTestCeBox.setVisible(false);
			VarPermuTestCeBox.setVisible(false);
			FtestCeBox.setVisible(false);
			ANOVACeBox.setVisible(false);
			KruskalWalisCeBox.setVisible(false);
			PermuTestCeBox.setVisible(false);
			messageTEXT.setVisible(false);
			SelectAllBtn.setVisible(false);

		}
		/**
		 * 
		 * @param event of button
		 */
		@FXML
	    void setcomboTestAction(ActionEvent event) {
			String selectedValue = comboTest.getValue();
			//in case single marker test
			if(selectedValue.equals("Single marker test")) {
				SelectAllBtn.setVisible(true);
				messageTEXT.setVisible(false);
				StudentTestCeBox.setVisible(true);
				FtestCeBox.setVisible(true);
				ANOVACeBox.setVisible(true);
				KruskalWalisCeBox.setVisible(true);
				PermuTestCeBox.setVisible(true);
				VarPermuTestCeBox.setVisible(true);
				//set values for the check boxes
				StudentTestCeBox.setSelected(false);
				PermuTestCeBox.setSelected(false);
				ANOVACeBox.setSelected(false);
				KruskalWalisCeBox.setSelected(false);
				FtestCeBox.setSelected(false);
				VarPermuTestCeBox.setSelected(false);

				
			}
			else {//in case chromosome wise test
				SelectAllBtn.setVisible(false);
				StudentTestCeBox.setVisible(false);
				FtestCeBox.setVisible(false);
				ANOVACeBox.setVisible(false);
				KruskalWalisCeBox.setVisible(false);
				PermuTestCeBox.setVisible(false);
				PermuTestCeBox.setSelected(true);
				messageTEXT.setVisible(true);
				VarPermuTestCeBox.setVisible(false);
			}
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
				root = FXMLLoader.load(getClass().getResource("SearchQTL.fxml"));
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


}
