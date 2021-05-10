// Zach Ogburn
// COSC 5366.001
// 4/4/2021
// Dr. Brown
//Assignment 4
// This program acts as a bottom-up parser

import java.util.*;

public class Assignment4 {

    public static void main(String[] args) {
        //create list of the language alphabet
        List<String> terminalList = Arrays.asList("+","*","(", ")","x", "$","E","T", "F");
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter a string to test: ");
        //get input string to test
        String inputStr = myScanner.nextLine();


        //splint input string into a list of chars
        List<String> inputList = new ArrayList<String>(Arrays.asList(inputStr.split("")));
        inputList.add("$");
        Stack<String> inputStack = new Stack<>();
        Collections.reverse(inputList);
        //push inputList onto inputStack
        for (int i = 0; i < inputList.size(); i++) {
            inputStack.push(inputList.get(i));
        }
        //create parser and production table
        Map<String, Map<String, String>> parserTable = new HashMap<>();

        Map<String, List<String>> productionsTable = new HashMap<>();

        Stack<String> stack = new Stack<String>();
        stack.push("0");
        //create the rows of the parser table
        Map<String, String> row0 = new HashMap<>();
        row0.put("+", "error");
        row0.put("*", "error");
        row0.put("(", "s1");
        row0.put(")", "error");
        row0.put("x", "s2");
        row0.put("E", "g3");
        row0.put("T", "g4");
        row0.put("F", "g5");
        row0.put("$", "error");
        Map<String, String> row1 = new HashMap<>();
        row1.put("+", "error");
        row1.put("*", "error");
        row1.put("(", "s1");
        row1.put(")", "error");
        row1.put("x", "s2");
        row1.put("E", "g6");
        row1.put("T", "g4");
        row1.put("F", "g5");
        row1.put("$", "error");
        Map<String, String> row2 = new HashMap<>();
        row2.put("+", "r6");
        row2.put("*", "r6");
        row2.put("(", "error");
        row2.put(")", "r6");
        row2.put("x", "error");
        row2.put("E", "error");
        row2.put("T", "error");
        row2.put("F", "error");
        row2.put("$", "r6");
        Map<String, String> row3 = new HashMap<>();
        row3.put("+", "s7");
        row3.put("*", "error");
        row3.put("(", "error");
        row3.put(")", "error");
        row3.put("x", "error");
        row3.put("E", "error");
        row3.put("T", "error");
        row3.put("F", "error");
        row3.put("$", "accept");
        Map<String, String> row4 = new HashMap<>();
        row4.put("+", "r2");
        row4.put("*", "s8");
        row4.put("(", "error");
        row4.put(")", "r2");
        row4.put("x", "error");
        row4.put("E", "error");
        row4.put("T", "error");
        row4.put("F", "error");
        row4.put("$", "r2");
        Map<String, String> row5 = new HashMap<>();
        row5.put("+", "r4");
        row5.put("*", "r4");
        row5.put("(", "error");
        row5.put(")", "r4");
        row5.put("x", "error");
        row5.put("E", "error");
        row5.put("T", "error");
        row5.put("F", "error");
        row5.put("$", "r4");
        Map<String, String> row6 = new HashMap<>();
        row6.put("+", "s7");
        row6.put("*", "error");
        row6.put("(", "error");
        row6.put(")", "s10");
        row6.put("x", "error");
        row6.put("E", "error");
        row6.put("T", "error");
        row6.put("F", "error");
        row6.put("$", "error");
        Map<String, String> row7 = new HashMap<>();
        row7.put("+", "error");
        row7.put("*", "error");
        row7.put("(", "s1");
        row7.put(")", "error");
        row7.put("x", "s2");
        row7.put("E", "error");
        row7.put("T", "g9");
        row7.put("F", "g5");
        row7.put("$", "error");
        Map<String, String> row8 = new HashMap<>();
        row8.put("+", "error");
        row8.put("*", "error");
        row8.put("(", "s1");
        row8.put(")", "error");
        row8.put("x", "s2");
        row8.put("E", "error");
        row8.put("T", "error");
        row8.put("F", "g11");
        row8.put("$", "error");
        Map<String, String> row9 = new HashMap<>();
        row9.put("+", "r1");
        row9.put("*", "s8");
        row9.put("(", "error");
        row9.put(")", "r1");
        row9.put("x", "error");
        row9.put("E", "error");
        row9.put("T", "error");
        row9.put("F", "error");
        row9.put("$", "r1");
        Map<String, String> row10 = new HashMap<>();
        row10.put("+", "r5");
        row10.put("*", "r5");
        row10.put("(", "error");
        row10.put(")", "r5");
        row10.put("x", "error");
        row10.put("E", "error");
        row10.put("T", "error");
        row10.put("F", "error");
        row10.put("$", "r5");
        Map<String, String> row11 = new HashMap<>();
        row11.put("+", "r3");
        row11.put("*", "r3");
        row11.put("(", "error");
        row11.put(")", "r3");
        row11.put("x", "error");
        row11.put("E", "error");
        row11.put("T", "error");
        row11.put("F", "error");
        row11.put("$", "r3");
        //add rows to parser table
        parserTable.put("0", row0);
        parserTable.put("1", row1);
        parserTable.put("2", row2);
        parserTable.put("3", row3);
        parserTable.put("4", row4);
        parserTable.put("5", row5);
        parserTable.put("6", row6);
        parserTable.put("7", row7);
        parserTable.put("8", row8);
        parserTable.put("9", row9);
        parserTable.put("10", row10);
        parserTable.put("11", row11);


        //create elements of productionTable
        List<String> prod0 = new ArrayList<>();
        prod0.add(0, "G");
        prod0.add(1, "1");
        List<String> prod1 = new ArrayList<>();
        prod1.add(0, "E");
        prod1.add(1, "3");
        List<String> prod2 = new ArrayList<>();
        prod2.add(0, "E");
        prod2.add(1, "1");
        List<String> prod3 = new ArrayList<>();
        prod3.add(0, "T");
        prod3.add(1, "3");
        List<String> prod4 = new ArrayList<>();
        prod4.add(0, "T");
        prod4.add(1, "1");
        List<String> prod5 = new ArrayList<>();
        prod5.add(0, "F");
        prod5.add(1, "3");
        List<String> prod6 = new ArrayList<>();
        prod6.add(0, "F");
        prod6.add(1, "1");
        //populate productionsTable
        productionsTable.put("0", prod0);
        productionsTable.put("1", prod1);
        productionsTable.put("2", prod2);
        productionsTable.put("3", prod3);
        productionsTable.put("4", prod4);
        productionsTable.put("5", prod5);
        productionsTable.put("6", prod6);

        //initialize variables
        String topStack;
        String inputChar;
        String tempStr;
        String tempStr2;
        String tempStr4;
        int r;
        String n;
        //loop through the inputStr
        while (stack.size() >= 1) {

            topStack = stack.peek();
            inputChar = inputStack.peek();
            //check if inputChar is not in alphabet, if so reject string
            if(!terminalList.contains(inputChar)) {
                System.out.println("ERROR: Invalid character!");
                break;
            }
            //assign values to tempVariables from parser table
            tempStr = String.valueOf(parserTable.get(topStack).get(inputChar).charAt(0));
            tempStr2 = String.valueOf(parserTable.get(topStack).get(inputChar).substring(1));

            //perform shift operation
             if (tempStr.equals("s")) {
                stack.add(inputChar);
                stack.add(tempStr2);
                inputStack.pop();
            }
            //perform reduce operation
            else if (tempStr.equals("r")) {
                //get values of r and n from parserTable and productionTable
                r = Integer.parseInt(productionsTable.get(tempStr2).get(1))*2;
                n = productionsTable.get(tempStr2).get(0);
                //pop proper number of elements off of stack
                for(int i = 0; i < r; i++) {
                    stack.pop();
                }
                //push updated values onto stack
                topStack = stack.get(stack.size()-1);
                stack.push(n);
                tempStr4 = String.valueOf(parserTable.get(topStack).get(n).substring(1));
                stack.push(tempStr4);
            }
            else {
                //check if the string is accepted
                if (parserTable.get(topStack).get(inputChar).equals("accept")) {
                    System.out.println("String accepted!");
                } else {
                    System.out.println("String not accepted");
                }
                break;
            }


        }
    }
}
