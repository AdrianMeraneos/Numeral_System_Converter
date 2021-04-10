package converter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numeralSystem;
        String number;
        int newNumeralSystem;
        try {
            numeralSystem = sc.nextInt();
            number = sc.next();
            newNumeralSystem = sc.nextInt();
        } catch (Exception e) {
            System.out.print("Error: At least one of the strings you want to convert isn't a number! "); 
            System.out.print("You may also need to check if entered integers for the numeral systems.");
            return;
        }
        if (numeralSystem < 0 || numeralSystem > 36 || newNumeralSystem < 0 || newNumeralSystem > 36) {
            System.out.println("Error: At least one of the numeral systems you chose doesn't exist!");
            return;
        }
        if (number.contains(".")) {
            String[] sArray = number.replace(".", ":").split(":");
            String newNumberPart1 = convertToNewNumeralSystem(sArray[0], numeralSystem, newNumeralSystem);
            String newNumberPart2 = convertFractionalPart(sArray[1], numeralSystem, newNumeralSystem);
            System.out.print(newNumberPart1 + "." + newNumberPart2.replace(".", ":").split(":")[1]);
        } else {
            System.out.print(convertToNewNumeralSystem(number, numeralSystem, newNumeralSystem));
        }

        
    }
    
    public static String convertFractionalPart (String startNum, int numeralSystem, int newNumeralSystem) {
        double decimalFractionalValue = 0.00000000;
        if (numeralSystem != 10) {
            String[] array = new String[startNum.length()];
            for (int i = 0; i < startNum.length(); i++) {
                array[i] = String.valueOf(startNum.charAt(i));
                if ("abcdefghijklmnopqrstuvwxyz".contains(array[i])) {
                    array[i] = convertToNewNumeralSystem(array[i], numeralSystem, 10);
                }
            }
            for (int i = 1; i <= array.length; i++) {
                decimalFractionalValue += Double.parseDouble(array[i - 1]) / Math.pow(numeralSystem, i);
            }
        } else {
            decimalFractionalValue = Double.parseDouble("0." + startNum);
        }
        if (decimalFractionalValue > 1.00) {
            return "You did something wrong with the conversion of the fractional part to decimal!";
        }
        String s = "0.";
        int num;
        for (int i = 0; i < 5; i++) {
            num = (int)(decimalFractionalValue * (double) newNumeralSystem);
            if (num < 10) {
                s += num;
            } else {
                s += convertToNewNumeralSystem(String.valueOf(num), 10, newNumeralSystem);
            }
            decimalFractionalValue = (decimalFractionalValue * (double) newNumeralSystem) - num;
        }
        return s;
    }
    
    public static String convertToNewNumeralSystem(String number, int numeralSystem, int newNumeralSystem) {
        if (numeralSystem > 1 && newNumeralSystem > 1) {
            int tempNum = Integer.parseInt(number, numeralSystem);
            return Integer.toString(tempNum, newNumeralSystem);
        } else {
            if (numeralSystem == 1) {
                int tempNum = 0; 
                for (int i = 0; i < number.length(); i++) {
                    tempNum++;
                }
                return Integer.toString(tempNum, newNumeralSystem);
            } else if (newNumeralSystem == 1) {
                String endNum = "";
                for (int i = 0; i < Integer.parseInt(number); i++) {
                    endNum += 1;
                }
                return endNum;
            }
        }
        return "Error: There was a problem with your input!";
    }
}
