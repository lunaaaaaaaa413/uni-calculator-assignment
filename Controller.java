
/**
 * Write a description of class Controller here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Controller{
    
    Model model; //Controller talks to Model
    
    // Bridge method: called by the View to delegate respomsibilites to Model.
    void doCalculate(String action){
        
        switch(action){
            case "+":
                model.doAdd();
                break;
            case "%":
                model.doModulo();
                break;
            
            //TODO:
            //add your code to make other buton works ("-","X","÷")
            
            default: model.unimplementedOperation(action);
        }
    }
}
