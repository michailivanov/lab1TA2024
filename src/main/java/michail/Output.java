package michail;

import java.util.Vector;

public class Output {
    public static boolean IS_COMMENT = false;
    private static Vector<String> IDENTIFIER;
    private static Vector<String> CONSTANTS;
    private static Vector<String> headers;
    private static Vector<Vector<String>> data;
    private static String buffer = "";
    private final Vector<Integer> output; // null or any value from 0 to 14

    public Output() {
        // Initialize outputs, can be set to null to denote 'none'
        this.output = new Vector<>(0);

        IDENTIFIER = new Vector<>();
        CONSTANTS = new Vector<>();

        headers = new Vector<>();
        data = new Vector<>();
        headers.add("Lexeme");
        headers.add("Type");
        headers.add("Value");
//        this.output.add(null); // or any value from 0 to 10
//        this.output.add(null); // or any value from 0 to 10
    }

    public Output(Integer output) {
        this.output = new Vector<>(1);
        this.output.add(output); // or any value from 0 to 10

        IDENTIFIER = new Vector<>();
        CONSTANTS = new Vector<>();

        headers = new Vector<>();
        data = new Vector<>();
        headers.add("Lexeme");
        headers.add("Type");
        headers.add("Value");
    }

    public Output(Integer output1, Integer output2) {
        this.output = new Vector<>(2);
        this.output.add(output1); // or any value from 0 to 10
        this.output.add(output2); // or any value from 0 to 10

        IDENTIFIER = new Vector<>();
        CONSTANTS = new Vector<>();

        headers = new Vector<>();
        data = new Vector<>();
        headers.add("Lexeme");
        headers.add("Type");
        headers.add("Value");
    }

    public static void addInfoToData(String v1, String v2, String v3){
        Vector<String> row = new Vector<>();
        row.add(v1);
        row.add(v2);
        row.add(v3);
        data.add(row);
    }

    public static void printResultTable(){
        TablePrinter.printTable(headers, data);
        IDENTIFIER = new Vector<>();
        CONSTANTS = new Vector<>();
        // Empty data
        data = new Vector<>();
    }

    public static String forTestGetBuffer(){
        return buffer;
    }

    public void apply(Character c, Boolean isEnd) {
        StringBuilder res = new StringBuilder();
        if (c == '['){
            Output.IS_COMMENT = true;
        } else if (c == ']'){
            Output.IS_COMMENT = false;
        }
        for (Integer integer : output) {

            switch (integer) {
                case null:
                    break;
                case 0:
                    buffer += c;
                    break;
                case 1:
                    addInfoToData("for", "FOR", "");
                    buffer = "";
                    break;
                case 2:
                    addInfoToData("do", "DO", "");
                    buffer = "";
                    break;
                case 3:
                    if(buffer.length() <= 16){
                        if(!IDENTIFIER.contains(buffer)){
                            IDENTIFIER.add(buffer);
                        }
                        addInfoToData(buffer, "IDENTIF", buffer + " : " + IDENTIFIER.indexOf(buffer));
                    } else {
                        addInfoToData(buffer, "ERROR:identIsBiggerThan16", "");
                    }
                    buffer = "";
                    break;
                case 4:
                    addInfoToData(":=", "ASSERT", "");
                    buffer = "";
                    break;
                case 5:
                    if(buffer.length() <= 16){
                        if(!CONSTANTS.contains(buffer)){
                            CONSTANTS.add(buffer);
                        }
                        addInfoToData(buffer, "CONST", buffer + " : " + CONSTANTS.indexOf(buffer));
                    } else {
                        addInfoToData(buffer, "ERROR:constIsBiggerThan16", "");
                    }
                    buffer = "";
                    break;
                case 6:
                    addInfoToData(">", "IS_BIGGER", "");
                    buffer = "";
                    break;
                case 7:
                    if (!isEnd) {
                        addInfoToData(";", "SEMICOLON", "");
                        buffer = "";
                    }
                    break;
                case 8:
                    if (!buffer.isEmpty()){
                        addInfoToData(buffer, "ERROR:invalidLexeme", "");
                    } else {
                        addInfoToData(String.valueOf(c), "ERROR:invalidLexeme", "");
                    }
                    buffer = "";
                    break;
                case 9:
                    addInfoToData("<", "IS_SMALLER", "");
                    buffer = "";
                    break;
                case 10:
                    addInfoToData("=", "IS_EQ", "");
                    buffer = "";
                    break;
                case 11:
                    addInfoToData("(", "OPEN_BRACKET", "");
                    buffer = "";
                    break;
                case 12:
                    addInfoToData(")", "CLOSE_BRACKET", "");
                    buffer = "";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + integer);
            }
        }
        if (isEnd)
        {
            buffer = "";
        }
    }

}
