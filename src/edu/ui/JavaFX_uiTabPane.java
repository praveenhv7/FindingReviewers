package edu.ui;



import java.util.List;

/*import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;*/

import edu.msd.eventmanager.EventManager;
import edu.neu.msd.dao.AllSearchDetailsDAO;
import edu.neu.msd.dao.AuthorDetailsDAO;
import edu.neu.msd.dao.ConferenceDetailsDAO;
import edu.neu.msd.dao.JournalDetailsDAO;
import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.AuthorsMapper;
import edu.neu.msd.dto.CommonAuthorMapper;
import edu.neu.msd.dto.JournalMapper;
import edu.neu.msd.dto.PublicationMapper;
import edu.neu.msd.dto.SearchAuthMapper;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Rectangle2D;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent.*;
import javafx.event.EventHandler;
import javafx.scene.text.Font;


import javafx.stage.Stage;
 

public class JavaFX_uiTabPane extends Application {
	static int commiteeServed = 1;
	static int noOfpub;
	static int noOfJour;
	static String authorName1= null;
	protected static String journalName1;
	protected static String conferenceName1;
	protected static String confName = null;
	protected static String jrnlName = null;
	protected static String pubyear = "1";
	static String yearOption = "EQ";
	static String titleKeyword = null;
	protected static int min = 1;
	protected static int start = 0;
	protected static int end = 10;
	protected static int lengthResult = 0;
	static String role = null;
	static String conlist = null;
	static List<SearchAuthMapper> resultA;
	protected static int actualSize;
	protected static String choice = "OR";
	protected static String applyFilter = null;
	Button loadMoreB = new Button("     Next         ");
	Button previousB = new Button("Previous");
	Stage newStage;
	Tab tabS;
	Tab tabB;
	Label lblShortlistedAuthor = new Label();
	ListView listViewS = new ListView();
	ListView listViewB = new ListView();
	List<Author> resultS;
	//Button btnShortlist;
	//public static Logger logger = Logger.getLogger(JavaFX_uiTabPane.class);
 
  
  public static void main(String[] args) {
      launch(args);
      //PropertyConfigurator.configure("src\\edu\\neu\\msd\\properties\\log4j.properties");
      
      //Log in console in and log file
      //logger.debug("Log4j appender configuration is successful !!");
  }
  

  @Override
  public void start(Stage primaryStage) {
      primaryStage.setTitle("Hunt For Reviewers");
      StackPane stack = new StackPane();
      stack.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
      Scene scene = new Scene(stack);
      TabPane tabPane = new TabPane();
      tabPane.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING);
      BorderPane mainPane = new BorderPane();
      AuthorDetailsDAO auth = new AuthorDetailsDAO();
	  resultS = auth.getShortListedAuthors();
      tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {	
    	    @Override
    	    public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
    	        if(newTab == tabS) {
    	        	 displayShortlistedAuthor(listViewS);
    	            }
    	        if(resultA != null &&newTab == tabB ){
    	        	end=Math.min(10, resultA.size());
    	        	 listDisplay(listViewB,resultA,start,end);
    	        }
    	        
    	        }
    	    });
      //Create Tabs
      
      //Home Page
      Tab tabA = new Tab();
      tabA.setText("Home");
      tabA.setClosable(false);
      
      BorderPane tabAPane = new BorderPane();
      //Add something in Tab
      VBox mainA = new VBox(5);
      
      final double MAX_FONT_SIZE = 40.0;
      BorderPane top = new BorderPane();
      Label message = new Label("HUNT FOR REVIEWERS");
      message.setFont(new Font(MAX_FONT_SIZE));
      top.setCenter(message);
      top.setPrefHeight(50);
      BorderPane bottom = new BorderPane();
      BorderPane center = new BorderPane();
      center.setPrefHeight(400);
      Label guide = new Label();
      guide.setFont(new Font(16.0));
      String Value = "Hunt Author : This tab gives the list of authors for the search parameter entered by the user.\n Shortlist option allows you to add Authors for consideration. \n The shortlist tab gives all the users added so far. \nFor multiple parameters seperate by comma ','";
      guide.setText("GUIDLINES \n "+Value);
      Label stats = new Label();
      String temp = "Statictics \n Total Number of Records : 861392 \n Total number of Authors served in committee : 3027";
      stats.setText(temp);
      stats.setFont(new Font(20.0));
      BorderPane.setMargin(guide, new Insets(300,20,20,20));
      bottom.setLeft(guide);
      center.setCenter(stats);
      mainA.getChildren().add(top);
      mainA.getChildren().addAll(center,bottom);
      tabA.setContent(mainA);
      tabPane.getTabs().add(tabA);
     
      
      //Authors tab
      Author aB = new Author(); 
      tabB = new Tab();
      tabB.setText("Hunt  Authors");  
      tabB.setClosable(false);
      
      //Add something in Tab
      VBox mainB = new VBox(5);
      GridPane gridB = new GridPane();
      gridB.setVgap(5);
      gridB.setHgap(5);
      gridB.setAlignment(Pos.TOP_CENTER);
      TextField authorName = new TextField();
      authorName.setPromptText("Enter Author Name");
      authorName.setPrefWidth(800);
      gridB.add(authorName,0,0);
      //ListView listViewB = new ListView();
      Button btnSearch = new Button("Search");
      btnSearch.setId("search");
      HBox hb = new HBox(15);
      hb.setPadding(new Insets(10));
      TextField comferenceName = new TextField();
      comferenceName.setPromptText("Enter conference name");
      comferenceName.setFocusTraversable(false);
      TextField journalNameA = new TextField();
      journalNameA.setPromptText("Enter joural name");
      journalNameA.setFocusTraversable(false);
      TextField noOfPublications = new TextField();
      noOfPublications.setPromptText("Number of C.Publications");
      noOfPublications.setFocusTraversable(false);
      noOfPublications.setPrefWidth(150);
      TextField noOfJournal = new TextField();
      noOfJournal.setPromptText("Number of J.Publications");
      noOfJournal.setFocusTraversable(false);
      noOfJournal.setPrefWidth(150);
      TextField txtYear = new TextField();
      txtYear.setPromptText("Enter year of publication");
      txtYear.setFocusTraversable(false);
      ObservableList<String> options = 
    		    FXCollections.observableArrayList(
    		        "Equal to",
    		        "Greater than",
    		        "Less than",
    		        "Not Equal to"
    		    );
      ComboBox yearSelection = new ComboBox(options);
      yearSelection.setPromptText("Year Filter");
      TextField txtKeyword = new TextField();
      txtKeyword.setPromptText("Enter keyword");
      txtKeyword.setFocusTraversable(false);
      
      hb.getChildren().addAll(comferenceName,journalNameA,noOfPublications,noOfJournal,yearSelection,txtYear);
     
      btnSearch.setOnAction(new EventHandler<ActionEvent>(){
    	  @Override
          public void handle(ActionEvent event) {
    		  int flag = 0;
    		  listViewB.getItems().clear();
    		  
    		  if(authorName.getText().isEmpty()&&noOfPublications.getText().isEmpty()&&comferenceName.getText().isEmpty() && journalNameA.getText().isEmpty()
    				  && txtKeyword.getText().isEmpty()&& txtYear.getText().isEmpty() &&noOfJournal.getText().isEmpty())
    		  {
    			  Alert alert = new Alert(AlertType.WARNING);
    			  alert.setTitle("No filters set");
    			  alert.setHeaderText(null);
    			  alert.setContentText("Please set some filters");
    			  alert.showAndWait();
    		  }
    		  else
    		  {
    			  JavaFX_uiTabPane.authorName1 = authorName.getText().trim();
    			 
        		  aB.setAuthName(authorName1);  
        		  aB.setServedChoice(applyFilter);
        		  aB.setServedConference(conlist);
        		  aB.setServedTitleOption(role);
    			  aB.setCommitteServed(JavaFX_uiTabPane.commiteeServed );
        		  if(noOfPublications.getText().matches("\\d+") )
        			  JavaFX_uiTabPane.noOfpub = Integer.parseInt(noOfPublications.getText().trim());
        		  aB.setNoOfPublications(JavaFX_uiTabPane.noOfpub);
        		  
        	      if(noOfJournal.getText().matches("\\d+") )
        			  JavaFX_uiTabPane.noOfJour = Integer.parseInt(noOfJournal.getText().trim());
        		  aB.setNoOfJournals(noOfJour);
        		  if ((!noOfJournal.getText().isEmpty()&&!noOfJournal.getText().matches("\\d+") )|| (!noOfPublications.getText().isEmpty()&&!noOfPublications.getText().matches("\\d+")))
        		  {
        			  flag = 1;
        			  Alert alert = new Alert(AlertType.WARNING);
        			  alert.setTitle("Invalid Input");
        			  alert.setHeaderText(null);
        			  alert.setContentText("Please Enter a valid no of publication");
        			  alert.showAndWait();
        		  }
        		  if(!comferenceName.getText().isEmpty())
        		  {
        			  JavaFX_uiTabPane.confName = comferenceName.getText().trim();
        			  aB.setConfereceName(JavaFX_uiTabPane.confName);
        			 
        		  }
        		  if (!journalNameA.getText().isEmpty())
        		  {
        			  JavaFX_uiTabPane.jrnlName = journalNameA.getText().trim();
        			  aB.setJournalName(JavaFX_uiTabPane.jrnlName);
        			  
        		  }
        		  
        		  if (!txtYear.getText().isEmpty() && (!txtYear.getText().matches("\\d+") || txtYear.getText().length() != 4 ))
        		  {
        			  flag = 1;
        			  Alert alert = new Alert(AlertType.WARNING);
        			  alert.setTitle("Invalid Input");
        			  alert.setHeaderText(null);
        			  alert.setContentText("Please Enter a valid year");
        			  alert.showAndWait();
        		  }
        		  if (txtYear.getText().matches("\\d+") ||!txtYear.getText().isEmpty() || txtYear.getText().length() == 4 )
        		  {
        			  JavaFX_uiTabPane.pubyear = txtYear.getText().trim();
        			  aB.setYear(pubyear);
        		  }
        		  if(!txtKeyword.getText().isEmpty())
        		  {
        			  JavaFX_uiTabPane.titleKeyword = txtKeyword.getText().trim();
        			  aB.setTitle(titleKeyword);
        		  }
        		  if(!yearSelection.getSelectionModel().isEmpty())
        		  {
    	    		  JavaFX_uiTabPane.yearOption = yearSelection.getSelectionModel().getSelectedItem().toString();
    	    		  if(yearOption == "Greater than")
    	    		  {
    	    			  JavaFX_uiTabPane.yearOption = "GEQ";
    	    		  }
    	    		  if(yearOption == "Less than")
    	    		  {
    	    			  JavaFX_uiTabPane.yearOption = "LEQ";
    	    		  }
    	    		  if(yearOption == "Not Equal to")
    	    		  {
    	    			  JavaFX_uiTabPane.yearOption = "NEQ";
    	    		  }
    	    		  aB.setYearOption(yearOption);
        		  }
        		  JavaFX_uiTabPane.min = 0;
        		  int max = JavaFX_uiTabPane.min + 100;
        		  loadMoreB.setDisable(false);
        		  loadMoreB.setText("Next");
        		  previousB.setDisable(false);
        		  previousB.setText("Previous");
        		  if(flag == 0)
        		  {
        			  searchAuthorByAuthorName(aB,listViewB,min,max);
        		  }
        		  
        		  JavaFX_uiTabPane.min = max + 1;
        		  
        	  }
    		  }
    		  
      });
      loadMoreB.setOnAction(new EventHandler<ActionEvent>() {
    	  @Override
    	  
          public void handle(ActionEvent event) {
    		  previousB.setText("Previous");
    		 
			  previousB.setDisable(false);
    		  System.out.println("next  "+lengthResult);
    		  if(lengthResult > 0)
    		  {
    			  
    			  JavaFX_uiTabPane.start = JavaFX_uiTabPane.end;
    			  if(lengthResult > 11){
	    			  JavaFX_uiTabPane.lengthResult -= 12;
	    			  JavaFX_uiTabPane.end = JavaFX_uiTabPane.start + 12;
    			  }
    			  else
    			  {
    				  JavaFX_uiTabPane.end = start+lengthResult;
    				  JavaFX_uiTabPane.lengthResult = 0;
	    			  
    			  }
    			  
	    		  listDisplay(listViewB,resultA,start,end);
    		  }
    		  if(actualSize >= 100 && lengthResult <= 0)
    		  {
    			 
        		  int max = JavaFX_uiTabPane.min + 100;
        		  searchAuthorByAuthorName(aB,listViewB,min,max);
    		  }
    		  if (actualSize < 100 && lengthResult <= 0) {
    			  
    			  loadMoreB.setText("No More Results");
    			  loadMoreB.setDisable(true);
    		  }
    	  
    	  }
      });
      previousB.setOnAction(new EventHandler<ActionEvent>(){
    
		@Override
		public void handle(ActionEvent arg0) {
			System.out.println("Previous  "+lengthResult);
			// TODO Auto-generated method stub
			loadMoreB.setText("    Next    ");
			loadMoreB.setDisable(false);
			int temp = lengthResult;
			if(lengthResult < (actualSize-12)){
				
				JavaFX_uiTabPane.end = JavaFX_uiTabPane.start;
				if(temp >= 12)
				{
					JavaFX_uiTabPane.lengthResult += 12;
				}
				else
				{
					JavaFX_uiTabPane.lengthResult = temp;
				}
				
  			  	JavaFX_uiTabPane.start = JavaFX_uiTabPane.end - 12;	
  			    listDisplay(listViewB,resultA,start,end);
  			    
			}
			else
			{
				previousB.setText("No more Results");
				previousB.setDisable(true);
			}
			
		}
    	  
      });
     
      
      gridB.add(hb, 0, 1);
      mainB.getChildren().add(gridB);
      GridPane b = new GridPane();
      HBox buttons = new HBox(15);
      
      Button clearFilter = new Button("Reset");
      clearFilter.setFocusTraversable(false);
      clearFilter.setOnAction(new EventHandler<ActionEvent>()
	  {
  			public void handle(ActionEvent event) {
  				noOfPublications.clear();;
  				comferenceName.clear();
  				journalNameA.clear();
  				noOfJournal.clear();
  				authorName.clear();
  				txtKeyword.clear();
  				txtYear.clear();
  				JavaFX_uiTabPane.jrnlName = null;
  				JavaFX_uiTabPane.confName = null;
  				JavaFX_uiTabPane.noOfpub = 0;
  				JavaFX_uiTabPane.authorName1 = null;
  				JavaFX_uiTabPane.titleKeyword = null;
  				JavaFX_uiTabPane.pubyear = null;
  				JavaFX_uiTabPane.noOfJour = 0;
  				JavaFX_uiTabPane.role = null;
  				JavaFX_uiTabPane.conlist = null;
  				JavaFX_uiTabPane.applyFilter = null;
  				
  			}
	  });
      buttons.getChildren().addAll(btnSearch,clearFilter);
      b.setHgap(45);
      Button committeeFilter = new Button("Committee Filter");
      committeeFilter.setFocusTraversable(false);
      committeeFilter.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			displayFilter();
		}
    	  
      });
      b.add(txtKeyword, 0, 0);
      b.add(committeeFilter, 1, 0);
      b.add(buttons,2,0);
      b.setAlignment(Pos.CENTER);
      mainB.getChildren().add(b);
      mainB.getChildren().add(listViewB);
      HBox resultControls = new HBox(20);
      resultControls.getChildren().addAll(previousB,loadMoreB);
      mainB.getChildren().add(resultControls);
      tabB.setContent(mainB);
      tabPane.getTabs().add(tabB);
      

      
      
   // Shortlist Tab
      tabS = new Tab();
      tabS.setClosable(false);
      tabS.setText("Shortlisted Authors"); 
      
      VBox mainS = new VBox(5);
      Label lblHeading = new Label();
      mainS.getChildren().add(lblHeading);
      System.out.println(tabS.isSelected());
     
      mainS.getChildren().add(listViewS);
      Button btnDelAll = new Button("Delete All");
      btnDelAll.setOnAction(new EventHandler<ActionEvent>()
    		  {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					AuthorDetailsDAO auth = new AuthorDetailsDAO();
					auth.deleteShortListedAuthors(null, true);
					listViewS.getItems().clear();
				}
    	           
    		  });
      mainS.getChildren().add(btnDelAll);
      tabS.setContent(mainS);
      tabS.setClosable(false);
      tabPane.getTabs().add(tabS);
     
      mainPane.setCenter(tabPane);
      mainPane.prefHeightProperty().bind(scene.heightProperty());
      mainPane.prefWidthProperty().bind(scene.widthProperty());
     
      stack.getChildren().add(mainPane);
      primaryStage.setScene(scene);
      Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
      //set Stage boundaries to visible bounds of the main screen
      primaryStage.setWidth(primaryScreenBounds.getWidth());
      primaryStage.setHeight(primaryScreenBounds.getHeight());
      primaryStage.setResizable(false);
      primaryStage.sizeToScene();
      primaryStage.show();
  }
  
 
protected void displayFilter() {
	// TODO Auto-generated method stub
	newStage = new Stage();
    newStage.setTitle("Committee Served Filter");
    VBox comp = new VBox(5);
  //Committee filter
   
    Label lblcommittee=new Label("Committee Role");
    RadioButton p=new RadioButton("Program Committee members.");
    RadioButton pc=new RadioButton(" Program Chair");
    RadioButton c=new RadioButton("Conference Chair");
    RadioButton e=new RadioButton(" External Review Committee");
    ToggleGroup group=new ToggleGroup();
    p.setToggleGroup(group);
    p.setUserData("Program Committee members");
    pc.setToggleGroup(group);
    pc.setUserData("Program Chair");
    c.setToggleGroup(group);
    c.setUserData("Conference Chair");
    e.setToggleGroup(group);
    e.setUserData("External Review Committee");
    p.setSelected(true);
    Label confcom = new Label("Conference served: ");
    TextField tcon = new TextField();
    tcon.setPromptText("Enter Conferences Served");
    BorderPane bp = new BorderPane();
    Button btnApply = new Button("Apply Filters");
    bp.setCenter(btnApply);
    btnApply.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			JavaFX_uiTabPane.applyFilter = "YES";
			if(!tcon.getText().isEmpty())
			{
				JavaFX_uiTabPane.conlist = tcon.getText();
			}
			String temp = group.getSelectedToggle().getUserData().toString();
			 
			 if(temp == "Program Committee members")
			  {
				  JavaFX_uiTabPane.role = null;
			  }
			  if(temp == "Program Chair")
			  {
				  JavaFX_uiTabPane.role = "p";
			  }
			  if(temp == "Conference Chair")
			  {
				  JavaFX_uiTabPane.role = "c";
			  }
			  if(temp == "Enter Conferences Served")
			  {
				  JavaFX_uiTabPane.role = "e";
			  }
			  newStage.close();
		}
    	
    });
    comp.getChildren().addAll(lblcommittee,p,pc,c,e,confcom,tcon,bp);
   
    Scene stageScene = new Scene(comp, 400, 300);
    
    newStage.setScene(stageScene);
    newStage.show();  
	
}


public void listDisplay(ListView listView, List<SearchAuthMapper> result, int start, int end)
  {
	
	if(start != 0 || end != 0)
	{
		listView.getItems().clear();
	}
	BorderPane header = new BorderPane();
	header.setStyle("-fx-background-color: rgb(145,206,145)");
	HBox headers = new HBox(75);
	Label name = new Label("Author Name");
	name.setFont(new Font(20));
	Label pubNo = new Label("No of C.Publication");
	pubNo.setFont(new Font(20));
	Label jorNo = new Label("No of J.Publication");
	jorNo.setFont(new Font(20));
	headers.getChildren().addAll(name,pubNo,jorNo);
	header.setLeft(headers);
	Label act = new Label("Actions");
	act.setFont(new Font(20));
	header.setRight(act);
	listView.getItems().add(header);
	for(int i = start; i<end ;i++)
	{
		  BorderPane gridList = new BorderPane();
	      
	      Button btnReview = new Button("Review Profile");
	      btnReview.setId(result.get(i).getAuthorName());
	      Button btnShortlist = new Button("Shortlist");
	      
	      btnReview.setOnAction(new EventHandler<ActionEvent>()
	    		  {
			    	  @Override
		              public void handle(ActionEvent event) {
			    		  System.out.println(" id " +btnReview.getId());
			    		  getAuthorDetails(btnReview.getId());
			    		  
			    	  }
	    	  
	    		  });
	      for(Author item : resultS)
	      {
	    	 
		      if(item.getAuthName().equalsIgnoreCase(result.get(i).getAuthorName()))
		      {
		    	 
		    	  btnShortlist.setText("Added");
		    	  btnShortlist.setDisable(true);
		    	  break;
		      }
		      else
		      {
		    	 
		    	  btnShortlist.setText("Shortlist");
		    	  btnShortlist.setDisable(false);
		      }
	      }
	     
	      
	      btnShortlist.setId(result.get(i).getAuthorName());
	      btnShortlist.setOnAction(new EventHandler<ActionEvent>()
		  {
	    	  @Override
            public void handle(ActionEvent event) {
	    		  AuthorDetailsDAO auth = new AuthorDetailsDAO();
	    		  auth.AddShortListedAuthor(btnShortlist.getId());
	    		  btnShortlist.setText("Added");
	    		  btnShortlist.setDisable(true);
	    		  displayShortlistedAuthor(listViewS);
	    	  }
	  
		  });
	      HBox value = new HBox(100);
	      Label lblAuthor = new Label();    
	      lblAuthor.setText(result.get(i).getAuthorName());
	      lblAuthor.setPrefWidth(150);
	      Label pub = new Label();
	      pub.setText(result.get(i).getCountPublications().toString());
	      pub.setContentDisplay(ContentDisplay.CENTER);
	      pub.setPrefWidth(170);
	      Label jour = new Label();
	      jour.setText(result.get(i).getCountJournals().toString());
	      jour.setContentDisplay(ContentDisplay.CENTER);
	      value.getChildren().addAll(lblAuthor,pub,jour);
	      gridList.setLeft(value);
	      HBox hList = new HBox(5);
	      hList.getChildren().addAll(btnReview,btnShortlist);
	      gridList.setRight(hList);
	      listView.getItems().add(gridList);
	 
	      
	}
  }

public void searchAuthorByAuthorName(Author author,ListView listView,int min,int max)
{
	
		EventManager manager=new EventManager();	
		  JavaFX_uiTabPane.start = 0;
		  JavaFX_uiTabPane.end = 12;
		 // AllSearchDetailsDAO authDao=new AllSearchDetailsDAO();
		  System.out.println(author.getConfereceName());
		  try {
			JavaFX_uiTabPane.resultA = manager.searchForAllCategory(author.getConfereceName(), author.getJournalName(), author,choice,min, max);
		
		  JavaFX_uiTabPane.lengthResult = resultA.size() - 12 ;
		  JavaFX_uiTabPane.actualSize = resultA.size();
		  System.out.println(actualSize);
		  listDisplay(listView,resultA,start,(end > actualSize) ? actualSize:end);
		  JavaFX_uiTabPane.min = max + 1 ;
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}



public void displayShortlistedAuthor(ListView listView) {
	// TODO Auto-generated method stub
	
		  AuthorDetailsDAO auth = new AuthorDetailsDAO();
		  resultS = auth.getShortListedAuthors();
		  listView.getItems().clear();
		  for (Author a : resultS){
			  BorderPane gridList = new BorderPane();
		      Button btnReview = new Button("Review Profile");
		     
		      
		      btnReview.setOnAction(new EventHandler<ActionEvent>()
		    		  {
				    	  @Override
			              public void handle(ActionEvent event) {
				    		   
				    		  getAuthorDetails(a.getAuthName());
				    	  }
		    	  
		    		  });
		      Button btnContact = new Button("Contact Author");
		      
		      btnContact.setOnAction(new EventHandler<ActionEvent>()
			  {
		    	  @Override
	            public void handle(ActionEvent event) {
		    		  contactAuthor();  
		    	  }
		  
			  });
		      Button btnDelete = new Button("Delete");
		      btnDelete.setId(a.getAuthName());
		      btnDelete.setOnAction(new EventHandler<ActionEvent>()
		    		  {
		    	  		@Override
				        public void handle(ActionEvent event) {
					    		  deleteAuthor(btnDelete.getId(),listView);  
			    	  }
		    		  });
		      Label lblAuthor = new Label();
		      
		      lblAuthor.setText(a.getAuthName());
		      gridList.setLeft(lblAuthor);
		      HBox hList = new HBox(5);
		      hList.getChildren().addAll(btnReview,btnContact,btnDelete);
		      gridList.setRight(hList);
		      listView.getItems().add(gridList);
		  }
		  
	      
	}

// Method for Contacting Author
// EFFECT : When pressed the button "Contact", a window pops up
// displaying the message "The author has been notified".
public void contactAuthor() {
	// TODO Auto-generated method stub
	Stage newStage = new Stage();
	newStage.setTitle("Contact Author");
	VBox v =  new VBox(5);
	v.setPadding(new Insets(10));
	Label s = new Label();
	s.setText("Author has been notified!!");
	v.getChildren().add(s);
	Scene stageScene = new Scene(v,250,100);
	newStage.setScene(stageScene);
	newStage.show();
	
}

/***
 * 
 * @param name
 * @param listView
 */
public void deleteAuthor(String name , ListView listView)
{
	AuthorDetailsDAO auth = new AuthorDetailsDAO();
	auth.deleteShortListedAuthors(name, false);
	displayShortlistedAuthor(listView);
}
  
  


public void getAuthorDetails(String name)
{
	 AuthorDetailsDAO auth = new AuthorDetailsDAO();
	 
	 Stage reviewStage = new Stage();
	 reviewStage.setTitle("Review Author");
	 BorderPane l1 = new BorderPane();
	 BorderPane l2 = new BorderPane();
	 BorderPane l3 = new BorderPane();
	 VBox main = new VBox(5);
	 VBox h = new VBox(5);
	 HBox h1 = new HBox(10);
	 VBox h2 = new VBox(5);
	 HBox hname = new HBox(5);
	 Label authorNamer = new Label("Name:");
	 authorNamer.setFont(new Font(20.0));
	 Label lblauthorName = new Label(name);
	 lblauthorName.setFont(new Font(20.0));
	 hname.getChildren().addAll(authorNamer,lblauthorName);
	 VBox details = new VBox(5);
	 name = name.trim();
	 Label lblAffiliation= new Label("Affiliation:  "+ auth.getAuthorAffiliation(name));
	 lblAffiliation.setFont(new Font(15.0));
	 Label lblHomepage = new Label("Website:   "+ auth.getAuthorHomepage(name.toUpperCase()));
	 lblHomepage.setFont(new Font(15.0));
	 details.getChildren().addAll(lblAffiliation,lblHomepage);
	 details.setAlignment(Pos.TOP_CENTER);
	 hname.setAlignment(Pos.TOP_CENTER);
	 h.setMargin(hname,new Insets(14,14,14,14));
	 h.getChildren().addAll(hname,details);
	 Label headconfList = new Label("List of Conferences");
	 ListView conflist = new ListView();
	 Author auth1 = new Author();
	 auth1.setAuthName(name);
	 auth1.setYear(null);
	 auth1.setTitle(null);
	 //Conference list populate
	 ConferenceDetailsDAO con = new ConferenceDetailsDAO();
	 List<PublicationMapper> jlist = con.getConferenceDetails(auth1, 0, 10);
	 conflist.setPrefHeight(200);
	 conflist.setPrefWidth(295);
	 for(PublicationMapper p : jlist)
	 {
		 BorderPane value = new BorderPane(); 
		 Label temp = new Label(p.getTitle());
		 value.setLeft(temp);
		 conflist.getItems().add(value);
	 }
	 l1.setTop(headconfList);
	 l1.setBottom(conflist);
	 h1.getChildren().add(l1);
	 
	 Label headarticlesList = new Label("List of Articles");
	 ListView articlesList = new ListView();
	 l2.setTop(headarticlesList);
	 
	 //Journal List populate
	 JournalDetailsDAO jou = new JournalDetailsDAO();
	 List<JournalMapper> conlist = jou.getJournalDetails(auth1, 1, 10);
	 articlesList.setPrefHeight(200);
	 articlesList.setPrefWidth(295);
	 for(JournalMapper j : conlist)
	 {
		 BorderPane value = new BorderPane(); 
		 Label temp = new Label(j.getArticleTitle());
		 value.setLeft(temp);
		 articlesList.getItems().add(value);
	 }
	 l2.setBottom(articlesList);
	 h1.getChildren().add(l2);
	 
	 Label headcoauthorList = new Label("List of Co-Authors");
	 ListView coauthorList = new ListView();
	 AuthorDetailsDAO a = new AuthorDetailsDAO();
	 List <Author> ad = a.getCoAuthorDetails(auth1);
	 l3.setTop(headcoauthorList);
	 for(int i = 0;i<ad.size();i++)
	 {
		 BorderPane value = new BorderPane(); 
		 Label temp = new Label(ad.get(i).getAuthName());
		 value.setLeft(temp);
		 coauthorList.getItems().add(value);
	 }
	 l3.setBottom(coauthorList);
	 h2.getChildren().add(l3);
	 main.getChildren().addAll(h,h1,h2);
	 Scene stageScene = new Scene(main, 600, 600);
	 reviewStage.setScene(stageScene);
	 reviewStage.show(); 
	
}}