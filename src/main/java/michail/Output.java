package michail;

import java.util.Vector;

public class Output {
    private static String buffer = "";
    private final Vector<Integer> output; // null or any value from 0 to 10

    public Output() {
        // Initialize outputs, can be set to null to denote 'none'
        this.output = new Vector<>(0);
//        this.output.add(null); // or any value from 0 to 10
//        this.output.add(null); // or any value from 0 to 10
    }

    public Output(Integer output) {
        this.output = new Vector<>(1);
        this.output.add(output); // or any value from 0 to 10
    }

    public Output(Integer output1, Integer output2) {
        this.output = new Vector<>(2);
        this.output.add(output1); // or any value from 0 to 10
        this.output.add(output2); // or any value from 0 to 10
    }

    public static String forTestGetBuffer(){
        return buffer;
    }

    public String apply(Character c, Boolean isEnd) {
        StringBuilder res = new StringBuilder();
        for (Integer integer : output) {
            switch (integer) {
                case null:
                    break;
                case 0:
                    buffer += c;
                    break;
                case 1:
                    res.append(!res.isEmpty() ? "\n" : "");
                    res.append("for(");
                    buffer = "";
                    break;
                case 2:
                    res.append(!res.isEmpty() ? "\n" : "");
                    res.append(")do");
                    buffer = "";
                    break;
                case 3:
                    res.append(!res.isEmpty() ? "\n" : "");
                    res.append("IDENTIFICATOR: ").append(buffer);
                    buffer = "";
                    break;
                case 4:
                    res.append(!res.isEmpty() ? "\n" : "");
                    res.append(":=");
                    buffer = "";
                    break;
                case 5:
                    res.append(!res.isEmpty() ? "\n" : "");
                    res.append("CONST: ").append(buffer);
                    buffer = "";
                    break;
                case 6:
                    res.append(!res.isEmpty() ? "\n" : "");
                    res.append(">");
                    buffer = "";
                    break;
                case 7:
                    if (!isEnd) {
                        res.append(!res.isEmpty() ? "\n" : "");
                        res.append(";");
                        buffer = "";
                    }
                    break;
                case 8:
                    res.append(!res.isEmpty() ? "\n" : "");
                    res.append("Error: ").append(buffer);
                    buffer = "";
                    break;
                case 9:
                    res.append(!res.isEmpty() ? "\n" : "");
                    res.append("<");
                    buffer = "";
                    break;
                case 10:
                    res.append(!res.isEmpty() ? "\n" : "");
                    res.append("=");
                    buffer = "";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + integer);
            }
        }
        return res.toString();
    }

}
