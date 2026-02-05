
/**
 * Passes commands from the view class to the model.
 */
public class Controller{
    
    Model model; //Controller talks to Model
    
    // Bridge method: called by the View to delegate respomsibilites to Model.
    /**
     * Tells the model about a button press.
     */
    void doAction(String action){
        
        switch(action){
            case "+":
                model.doAdd();
                break;
            case "-":
                model.doSub();
                break;
            case "×":
                model.doMul();
                break;
            case "÷":
                model.dodiv();
                break;
            case "%":
                model.doModulo();
                break;
            case ">":
                model.History.navigate_history(1);
                break;
            case "<":
                model.History.navigate_history(-1);
                break;
            //TODO:
            //add your code to make other buton works ("-","X","÷")
            
            default: model.unimplementedOperation(action);
        }
    }
    /**Tells the model to step forwards in history */
    // void historyForwards(){model.History.navigate_history(1);}
    /**Tells the model to step backwards in history */
    // void historyBackwards(){model.History.navigate_history(-1);}
}
