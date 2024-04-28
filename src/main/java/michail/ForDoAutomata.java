package michail;

import org.apache.commons.math3.util.Pair;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ForDoAutomata {
    public static DFAutomata automata;

    private static Pair<Integer, Integer> parseY(String input){
//        String input = "y3,y10";
//        Pair<Integer, Integer> result;
        Pattern pattern = Pattern.compile("y(\\d+),?y?(\\d+)?");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String firstNumberString = matcher.group(1);
            Integer firstNumber = firstNumberString != null ? Integer.parseInt(firstNumberString) : null;

            String secondNumberString = matcher.group(2);
            Integer secondNumber = secondNumberString != null ? Integer.parseInt(secondNumberString) : null;

            return new Pair<Integer, Integer>(firstNumber, secondNumber);
        }
        return new Pair<Integer, Integer>(null, null);
    }
    static final int NUM_STATES = 15;
    static final int NUM_TRANSITIONS = 20;
    static String automataForDiagram = "";
    static {
        automataForDiagram = "#states\n";
        for (int i = 0; i < NUM_STATES; i++) {
            automataForDiagram += "s" + i + "\n";
        }
        automataForDiagram += "#initial\ns0\n";
        automataForDiagram += "#accepting\n";
        for (int i = 0; i < NUM_STATES; i++) {
            automataForDiagram += "s" + i + "\n";
        }
        automataForDiagram += "#alphabet\n";
        String automataForDiagramTransitions = "";

        // Create all states
        Vector<State> states = new Vector<>(NUM_STATES);
        for (int i = 0; i < NUM_STATES; i++) {
            states.add(new State(String.valueOf(i), false, "a"));
        }


        // Add transitions from .xlsx file
        try {
            FileInputStream file = new FileInputStream(new File("D:\\УНИВЕР\\6 сем\\МатЛог\\lab1 report\\img\\dfa.xlsx"));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet1 = workbook.getSheetAt(0); // Получаем первый лист из книги
            Sheet sheet2 = workbook.getSheetAt(1);

            Row firstRow = sheet1.getRow(0);

            for (int i = 1; i <= NUM_STATES; i++) {
                Row rowState = sheet1.getRow(i);
                Row rowOutput = sheet2.getRow(i);
                Cell firstCell = rowState.getCell(0);
                Integer firstCellInt = (int) firstCell.getNumericCellValue();
                for (int j = 1; j <= NUM_TRANSITIONS; j++) {
                    String inputTitle = firstRow.getCell(j).getStringCellValue();
                    Cell cellState = rowState.getCell(j);
                    Cell cellOutput = rowOutput.getCell(j);
                    Integer stateInt = (int) cellState.getNumericCellValue();
                    String outputString = cellOutput.getStringCellValue();

                    // To print:
//                    $\delta (s_{}, f) = \{q_{\firstArg}\},$\\
//                    s0:a/y0>s3
                    if(i == 1) { // form alphabet
                        if(inputTitle.equals(":")){
                            automataForDiagram += "C\n";
                        } else if (inputTitle.equals(">")){
                            automataForDiagram += "G\n";
                        } else if (inputTitle.equals(";")){
                            automataForDiagram += "SC\n";
                        } else if (inputTitle.equals("<")){
                            automataForDiagram += "<\n";
                        } else {
                            automataForDiagram += inputTitle + "\n";
                        }
                    }

                    // form transitions
                    if(inputTitle.equals(":")){
                        automataForDiagramTransitions += "s" + String.valueOf((int) (i - 1)) + ":" + "C" + ">s" + stateInt +"\n";
                    } else if (inputTitle.equals(">")){
                        automataForDiagramTransitions += "s" + String.valueOf((int) (i - 1)) + ":" + "G" + ">s" + stateInt +"\n";
                    } else if (inputTitle.equals(";")){
                        automataForDiagramTransitions += "s" + String.valueOf((int) (i - 1)) + ":" + "SC" + ">s" + stateInt +"\n";
                    } else if (inputTitle.equals("<")){
                        automataForDiagramTransitions += "s" + String.valueOf((int) (i - 1)) + ":" + "<" + ">s" + stateInt +"\n";
                    } else {
                        automataForDiagramTransitions += "s" + String.valueOf((int) (i - 1)) + ":" + inputTitle + ">s" + stateInt +"\n";
                    }

//                    System.out.print("s" + String.valueOf((int) (i - 1)) + ":\"" + inputTitle + "\">s" + stateInt +"\n");
//                   System.out.print("$\\delta (s_{" + String.valueOf((int) (i - 1)) + "}, " + inputTitle + ") = \\{s_{" + stateInt + "}\\},$\\\\"+"\n");
//                    System.out.print("$\\lambda (s_{" + String.valueOf((int) (i - 1)) + "}, " + inputTitle + ") = \\{"+ outputString + "\\},$\\\\"+"\n");
//                    System.out.print(String.valueOf((int) (i - 1)) + " " + String.valueOf((int) (j - 1)) + " addTransition: " + inputTitle + ", " +  stateInt + ", " + outputString + ": {"+ String.valueOf(parseY(outputString).getFirst()) + ", " + String.valueOf(parseY(outputString).getSecond()) + "}" + "\n");
                    states.get(i - 1).addTransition(inputTitle,states.get(stateInt), new Output(parseY(outputString).getFirst(), parseY(outputString).getSecond()));
                }
            }
            automataForDiagram += "#transitions\n";
            automataForDiagram += automataForDiagramTransitions;

            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.print(automataForDiagram);

        automata = new DFAutomata(states.get(0));
    }
    private ForDoAutomata() {

    }
}
