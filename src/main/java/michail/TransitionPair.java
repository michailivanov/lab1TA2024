package michail;

public class TransitionPair {
    Output output;
    State state;

    public TransitionPair(State state, Output output) {
        this.output = output;
        this.state = state;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
