
/**
 * Write a description of class Model here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Model {
    
    Calculation calculation; // Model uses the business class Calculation - which does the math
    
    View view; //Reference to the View; Model tells View to update itself
    
    // The three used to update the View
    private String currentOperator; // Current operator to display in the View
    private String resultStatus;    // Status indicator (e.g., "Result will appear below" or "=") for the View
    private String result;          // Calculation Result or failure message for the View

    // A nested class used to store the history of calculations
    class History {
        private ArrayList<Integer> firstNum = new ArrayList<Integer>();
        private ArrayList<Integer> secondNum = new ArrayList<Integer>();
        private ArrayList<String> operator = new ArrayList<String>();
        private ArrayList<Integer> result = new ArrayList<Integer>();

        boolean histFileExists = true;

        int counter = 0; // this variable is used to track where in history we are when the user is scrubbing through their history
        
        // in most cases we want to save to disk so i've used method overloading to make that the default option
        void new_entry(int one, int two, int result, String operator){
            new_entry(one, two, result, operator, true); 
        }
        
        void new_entry(int one, int two, int result, String operator, boolean writeToDisk){
            this.firstNum.add(one);
            this.secondNum.add(two);
            this.result.add(result);
            this.operator.add(operator);
            
            // Reset the user to the present if they are looking through their history
            counter = firstNum.size() - 1; // we subtract 1 here and on line 41 because the size function counts from 1 but counter needs to be a valid index and indexes count from 0
            if (writeToDisk){
                try {
                    // TODO: limit history entries
                    FileWriter histFile = new FileWriter("history.txt", true); // the true here stops filewriter from overwriting the content in history.txt
                    histFile.write(String.valueOf(one) + " " + String.valueOf(two) + " " + String.valueOf(result) + " " + operator + "\n");
                    histFile.close();
                } catch (Exception e) {
                    // TODO: what do i put here
                }
            }
        }

        void navigate_history(int i){
            if (i == 1){
                if (counter < firstNum.size() - 1) {
                    counter++;
                    history_update(firstNum.get(counter), secondNum.get(counter), result.get(counter), operator.get(counter));
                }
            } else if (i == -1){
                if (counter > 0) {
                    counter--;
                    history_update(firstNum.get(counter), secondNum.get(counter), result.get(counter), operator.get(counter));
                }
            }
        }

        public History(){
            // create history.txt if it does not exist
            File h = new File("history.txt");
            if (h.exists()){
                try (Scanner histFileRead = new Scanner(h)){
                    while (histFileRead.hasNextLine()){
                        String data = histFileRead.nextLine();
                        String[] split = data.split(" ");
                        new_entry(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), split[3], false);
                    }
                    counter++;
                } catch(Exception e) {

                }
            } else{
                try{
                    h.createNewFile();
                } catch(Exception e) {
                    boolean histFileExists = false;
                }
            }
        }
    }
    History History = new History();

    void doAdd(){
        currentOperator = "+";
        try{
            int num1 = Integer.parseInt(view.tfNum1.getText().trim());
            int num2 = Integer.parseInt(view.tfNum2.getText().trim());
            int resultNumber = calculation.add(num1,num2);
            result = resultNumber + "";
            resultStatus = "= ";
            History.new_entry(num1, num2, resultNumber, currentOperator);
        }
        catch(Exception e){
            resultStatus = "Result will appear below";
            result = "Invalid input: must be whole numbers";
        }
        update();
    }
    void doSub(){
        currentOperator = "-";
        try{
            int num1 = Integer.parseInt(view.tfNum1.getText().trim());
            int num2 = Integer.parseInt(view.tfNum2.getText().trim());
            int resultNumber = calculation.sub(num1,num2);
            result = resultNumber + "";
            resultStatus = "= ";
            History.new_entry(num1, num2, resultNumber, currentOperator);
        }
        catch(Exception e){
            resultStatus = "Result will appear below";
            result = "Invalid input: must be whole numbers";
        }
        update();
    }
    void doMul(){
        currentOperator = "×";
        try{
            int num1 = Integer.parseInt(view.tfNum1.getText().trim());
            int num2 = Integer.parseInt(view.tfNum2.getText().trim());
            int resultNumber = calculation.mul(num1,num2);
            result = resultNumber + "";
            resultStatus = "= ";
            History.new_entry(num1, num2, resultNumber, currentOperator);
        }
        catch(Exception e){
            resultStatus = "Result will appear below";
            result = "Invalid input: must be whole numbers";
        }
        update();
    }
    void dodiv(){
        currentOperator = "÷";
        try{
            int num1 = Integer.parseInt(view.tfNum1.getText().trim());
            int num2 = Integer.parseInt(view.tfNum2.getText().trim());
            int resultNumber = calculation.div(num1,num2);
            result = resultNumber + "";
            resultStatus = "= ";
            History.new_entry(num1, num2, resultNumber, currentOperator);
        }
        catch(Exception e){
            resultStatus = "Result will appear below";
            result = "Invalid input: must be whole numbers";
        }
        update();
    }
    void doModulo(){
        currentOperator = "%";
        try{
            int num1 = Integer.parseInt((view.tfNum1.getText().trim()));
            int num2 = Integer.parseInt(view.tfNum2.getText().trim());
            int resultNumber = calculation.mod(num1,num2);
            result = resultNumber + "";
            resultStatus = "= ";
            History.new_entry(num1, num2, resultNumber, currentOperator);
        }
        catch(Exception e){
            resultStatus = "Result will appear below";
            result = "Invalid input: must be whole numbers";
        }
        update();
    }
    
    void unimplementedOperation(String currentOperator){
        resultStatus = "Result will appear below";
        result = currentOperator + " not implemented yet";
        update();
    }
    
    // Notifies the View to refresh the UI; Called by the Model itself when data changes.
    private void update(){
        view.update(currentOperator,resultStatus, result);
    }
    private void history_update(int num1, int num2, int result, String operator){
        view.history_update(num1, num2, result, operator);
    }
}
