
/**
 * Write a description of class Model here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Model {
    
    Calculation calculation; // Model uses the business class Calculation - which does the math
    
    View view; //Reference to the View; Model tells View to update itself
    
    // The three used to update the View
    private String currentOperator; // Current operator to display in the View
    private String resultStatus;    // Status indicator (e.g., "Result will appear below" or "=") for the View
    private String result;          // Calculation Result or failure message for the View

    void doAdd(){
        currentOperator = "+";
        try{
            int num1 = Integer.parseInt(view.tfNum1.getText().trim());
            int num2 = Integer.parseInt(view.tfNum2.getText().trim());
            int resultNumber = calculation.add(num1,num2);
            result = resultNumber + "";
            resultStatus = "= ";
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
}
