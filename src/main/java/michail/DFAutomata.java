package michail;

import java.util.Objects;
import java.util.Random;
import java.util.Vector;

public class DFAutomata {
    private final State initialState;
    private State currentState;

    private static Vector<Vector<String>> notInAlphabetErrors;

    public DFAutomata(State initialState) {
        this.initialState = initialState;
        notInAlphabetErrors = new Vector<>();
    }

    public static void saveError(String v1, String v2, String v3){
        Vector<String> row = new Vector<>();
        row.add(v1);
        row.add(v2);
        row.add(v3);
        notInAlphabetErrors.add(row);
    }
    public static void addErrorsToData(){
        if(notInAlphabetErrors.isEmpty()){
            return;
        }
        // Save errors to data
        for (Vector<String> row : notInAlphabetErrors){
            Output.addInfoToData(row.get(0), row.get(1), row.get(2));
        }
        // Empty errors
        notInAlphabetErrors = new Vector<>();
    }

    public void execute(String str) {
        currentState = initialState;

        for (char c : str.toCharArray()) {
//            // Skip spaces and new line symbols
//            if (c == ' ' || c == '\n' || c == '\r'){
//                continue;
//            }

            // Get next state and output
            TransitionPair tp = currentState.getTransitionParameters(c);
            if(tp != null){
                // Get output and execute it
                tp.getOutput().apply(c, false);
                addErrorsToData();

                currentState = tp.getState();
            } else {
                if(!Output.IS_COMMENT)
                {
                    saveError(String.valueOf(c), "ERROR:notInAlphabet", "");
                }

            }
        }

        // Get the last result

        // Get next state and output
        TransitionPair tp = currentState.getTransitionParameters(';');

        // Get output and execute it (To get the last result)
        tp.getOutput().apply(';', true);
        addErrorsToData();

        Output.printResultTable();
    }
}
