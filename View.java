import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
/**
 * Write a description of JavaFX class Calculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class View {
    
    int H = 400;         // Height of window pixels 
    int W = 450;         // Width  of window pixels 

    Controller controller;  // View talks to Controller
    
    //components of the user interface
    TextField  tfNum1,tfNum2; // User input fields
    private TextField  tfResult; //Displays the calculation result, or a reminder message, eg: "The calculation ÷ not implemented yet"

    private Label laCurrentOperator; // Displays "Operator" by default, or the selected operator symbol (+, -, etc.) after an operation button is clicked.
    private Label laResultStatus; // Displays "Result will appear below" by default, or "=" when a calculation succeeds.
    
    private GridPane   gridPane;      // main layout manager grid
    private TilePane   buttonPane;    // tiled area for operator (+ - X ÷) buttons
    
    public void start(Stage window){
        window.setTitle("Simple Calculator");
        
        // Create layout managers and assign a CSS ID for styling. 
        // CSS is only for appearance; not needed for functionality, 
        // You can ignore the css usage for now
        gridPane = new GridPane();
        gridPane.setId("GridPaneStyle");  
        buttonPane = new TilePane();          
        buttonPane.setId("ButtonsPaneStyle");    
        
        //create UI controls and add them to layout manager
        tfNum1 = new TextField();
        gridPane.add(tfNum1,1,1);
        
        laCurrentOperator = new Label("Operator");
        gridPane.add(laCurrentOperator,1,2);
        
        tfNum2 = new TextField();
        gridPane.add(tfNum2,1,3);
        
        laResultStatus = new Label("Result will appear below");
        gridPane.add(laResultStatus,1,4);
        
        tfResult = new TextField();
        tfResult.setEditable(false);
        gridPane.add(tfResult,1,5);
        
        // Buttons - these are laid out on a TilePane,
        // then the whole tile pane is added to the main gridPane
        String[] operatorSymbols = { "+","-","×","÷", "%"}; // Symbols shown on operator buttons.

        //loop through the array, making a Button object for each symbol
        for(String text: operatorSymbols){
            Button btn = new Button(text);
            buttonPane.getChildren().add(btn);
            btn.setOnAction(this::operatorButtonClick); //Sets the action to be performed (event handler) when the button is clicked.
        } //this:: buttonClick - Refers to the current object of the class (View here) that defines buttonClick.
        
        gridPane.add(buttonPane,1,6);

        TilePane historyButtonPane = new TilePane();
        
        Button historyBackwards = new Button("<");
        historyButtonPane.getChildren().add(historyBackwards);
        historyBackwards.setOnAction(this::historyButtonClick);
        
        Button historyForwards = new Button(">");
        historyButtonPane.getChildren().add(historyForwards);
        historyForwards.setOnAction(this::historyButtonClick);
        
        gridPane.add(historyButtonPane,1,7);

        Scene scene = new Scene(gridPane,W,H);
        scene.getStylesheets().add("Calculator.css"); // tell the app to use our css
        window.setScene(scene);
        window.show();
    }
    
    private void historyButtonClick(ActionEvent event){
        Button btn = (Button) event.getSource();
        if (controller != null) {
            if (btn.getText() == ">"){controller.historyForwards(); }
            else{controller.historyBackwards();}
        }
    }
    // This method is called when a button is clicked
    // It fetches the text on the button and passes it to the controller's doCalculate method
    private void operatorButtonClick(ActionEvent event){
        // this line asks the event to provide the actual Button object that was clicked
        Button btn = (Button) event.getSource();
        if (controller != null){
            String text = btn.getText();   // get the button text
            controller.doCalculate(text);
        }
    }
    
    //This method is called by the Model to update the UI.
    void update(String currentOperator, String resultStatus, String result){       
        laCurrentOperator.setText(currentOperator);
        laResultStatus.setText(resultStatus);
        tfResult.setText(result);
    }
    void history_update(int num1, int num2, int result, String operator){
        tfNum1.setText(num1 + "");
        tfNum2.setText(num2 + "");
        tfResult.setText(result + "");
        
        laCurrentOperator.setText(operator);
    }
}

