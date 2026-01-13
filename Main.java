import javafx.application.Application;
import javafx.stage.Stage;

// Calculator App Main class
/**
 * Write your description here.
 *
 * @author
 * @version (a version number or a date)
 */
public class Main extends Application {
    
    public static void main( String args[] ){
        // The main method only gets used when launching from the command line
        // launch initialises the system and then calls start
        // In BlueJ, BlueJ calls start itself
        launch(args);
    }
    
    @Override
    public void start(Stage window){
        
        // Create  Model, View and Controller objects, as well as calculator object
        Model model = new Model();
        View view = new View();
        Controller  controller = new  Controller();
        Calculation calculation = new Calculation();
        
        // Link them together so they can talk to each other
        view.controller = controller;
        controller.model = model;
        model.view = view;
        model.calculation = calculation;
       
        // start up the GUI (view)
        view.start(window);         
    }
}
