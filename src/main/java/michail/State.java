package michail;
import java.util.*;

public class State {
    private final String name;
//    private final Pair<State, Output> pair;
    private final Map<String, TransitionPair> nextStates;
    private final boolean isFinal;
    private final regexMapAlphabet rma;

    private final String symbolsForGeneration;

    public State(String name, boolean isFinal, String symbolsForGeneration) {
        this.name = name;
        nextStates = new HashMap<>();
        this.isFinal = isFinal;
        this.rma = new regexMapAlphabet();
        this.symbolsForGeneration = symbolsForGeneration;
    }

    public TransitionPair getTransitionParameters(char c){
        // Iterate next states and find that match

        for (String key : nextStates.keySet()) {
            String pattern = rma.get(key);
            if (Character.toString(c).matches(pattern)) {
                return nextStates.get(key);
            }
        }

        // C is not from the alphabet
        return null;
    }

    public void addTransition(String s, State next, Output output) {
        nextStates.put(s, new TransitionPair(next, output));
    }

    public boolean isFinal() {
        return isFinal;
    }

    public boolean isError(){
        return name.equals("error");
    }

    public Character generateChar(){
        Random random = new Random();
        int index = random.nextInt(symbolsForGeneration.length());
        return symbolsForGeneration.charAt(index);
    }
}
