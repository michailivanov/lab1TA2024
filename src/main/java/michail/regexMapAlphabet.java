package michail;

import java.util.HashMap;
import java.util.Map;

public class regexMapAlphabet {
    Map<String, String> regexMap = new HashMap<>();

    public regexMapAlphabet() {
        // Добавление регулярного выражения и соответствующих строк
        regexMap.put("f", "[fF]");
        regexMap.put("o", "[oO]");
        regexMap.put("r", "[rR]");
        regexMap.put("d", "[dD]");

        regexMap.put("N", "[0-9]");
        regexMap.put("(", "\\(");
        regexMap.put(")", "\\)");
        regexMap.put("L", "[[A-Za-z]&&[^fFoOrRdDeE]]");
        regexMap.put("e", "[eE]");
        regexMap.put("=", "=");
        regexMap.put(":", ":");
        regexMap.put("[", "\\[");
        regexMap.put("]", "\\]");
        regexMap.put("-", "-");
        regexMap.put(".", "\\.");
        regexMap.put(";", ";");
        regexMap.put("+", "\\+");
        regexMap.put("<", "<");
        regexMap.put(">", ">");
    }
    public String get(String s){
        return regexMap.get(s);
    }

}
