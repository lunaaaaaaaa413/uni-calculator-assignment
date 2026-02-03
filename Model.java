
/**
 * Write a description of class Model here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class Model {
    
    Calculation calculation; // Model uses the business class Calculation - which does the math
    
    View view; //Reference to the View; Model tells View to update itself
    
    // The three used to update the View
    private String currentOperator; // Current operator to display in the View
    private String resultStatus;    // Status indicator (e.g., "Result will appear below" or "=") for the View
    private String result;          // Calculation Result or failure message for the View
    private String historyFirstNum = "null"; // This variable will only be changed from null if the user is scrubbing through their history, if it's null we know they aren't
    private String historySecondNum = "null";
    private String historyResult = "null";

    // A nested class used to store the history of calculations
    class History {
        ArrayList<Integer> firstNum = new ArrayList<Integer>();
        ArrayList<Integer> secondNum = new ArrayList<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();

        int counter = 0; // this variable is used to track where in history we are when the user is scrubbing through their history
        void new_entry(int one, int two, int result){
            this.firstNum.add(one);
            this.secondNum.add(two);
            this.result.add(result);
            counter = firstNum.size() - 1; // we subtract 1 here and on line 41 because the size is the true number of entries however we need the counter to refer to an index
                                           // and indexes start counting from zero, TODO: try and make this more clear / concise
        }

        void navigate_history(int i){
            if (i == 1){
                if (counter < firstNum.size() - 1) {
                    counter++;
                    history_update(firstNum.get(counter), secondNum.get(counter), result.get(counter));
                }
            } else if (i == -1){
                if (counter > 0) {
                    counter--;
                    history_update(firstNum.get(counter), secondNum.get(counter), result.get(counter));
                }
            }
        }
    }
    History History = this.new History();

    void doAdd(){
        currentOperator = "+";
        try{
            int num1 = Integer.parseInt(view.tfNum1.getText().trim());
            int num2 = Integer.parseInt(view.tfNum2.getText().trim());
            int resultNumber = calculation.add(num1,num2);
            result = resultNumber + "";
            resultStatus = "= ";
            History.new_entry(num1, num2, resultNumber);
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
            History.new_entry(num1, num2, resultNumber);
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
            History.new_entry(num1, num2, resultNumber);
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
            History.new_entry(num1, num2, resultNumber);
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
            History.new_entry(num1, num2, resultNumber);
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
    private void history_update(int num1, int num2, int result){
        view.history_update(num1, num2, result);
    }
}
