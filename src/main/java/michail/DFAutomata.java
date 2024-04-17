package michail;

import java.util.Objects;
import java.util.Random;

public class DFAutomata {
    private final State initialState;
    private State currentState;

    public DFAutomata(State initialState) {
        this.initialState = initialState;
    }

    public void execute(String str) {
        currentState = initialState;

        StringBuilder errors = new StringBuilder();

        for (char c : str.toCharArray()) {
            // Skip spaces and new line symbols
            if (c == ' ' || c == '\n'){
                continue;
            }

            // Get next state and output
            TransitionPair tp = currentState.getTransitionParameters(c);
            if(tp != null){
                // Get output and execute it
                String res = tp.getOutput().apply(c, false);
                if (!res.isEmpty())
                {
                    // Print result
                    System.out.println(res);

                    // Print errors (after result)
                    if(!errors.isEmpty())
                    {
                        System.out.print(errors);
                        errors = new StringBuilder();
                    }
                }
                // Change state to the next state
                currentState = tp.getState();
            } else {
                errors.append("Error: ").append(c).append(" is not in alphabet!");
            }
        }

        // Get the last result

        // Get next state and output
        TransitionPair tp = currentState.getTransitionParameters(';');

        // Get output and execute it
        String res = tp.getOutput().apply(';', true);
        if (!res.isEmpty())
        {
            // Print result
            System.out.println(res);

            // Print errors (after result)
            if(!errors.isEmpty())
            {
                System.out.print(errors);
                errors = new StringBuilder();
            }
        }
    }

    public String stringGeneration() {
        StringBuilder res = new StringBuilder();
        currentState = initialState;
        char randSymbol;

        while (!currentState.isFinal()) {
            randSymbol = currentState.generateChar();
            res.append(randSymbol);
            currentState = currentState.getTransitionParameters(randSymbol).getState();
        }

        Random random = new Random();
        int randomNumber = random.nextInt(5) + 3; // Generates a random number between 0 and 4, then adds 2 to it

        for (int i = 0; i < randomNumber; i++) {
            randSymbol = currentState.generateChar();
            res.append(randSymbol);
            currentState = currentState.getTransitionParameters(randSymbol).getState();
        }

        return res.toString();
    }

}
