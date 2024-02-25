import java.util.Scanner;

public class CalculatorCataGit {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два арабских или два римских числа: ");
        String expression = scanner.nextLine().replaceAll(" ", "");
        System.out.println(calculator(expression));
    }

    public static String calculator(String input) throws Exception {
        int number1;
        int number2;
        String operation;
        String result;
        boolean isRooman;

        String[] operands = input.split("[+\\-*/]");
        if(operands.length < 2) throw new Exception("Строка не является математической операцией, должно быть два операнда!");
        if(operands.length > 2) throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        operation = detectOper(input);
        if (operation == null) {
            throw new Exception("Неподдерживаемая математическая операция");
        }

        if(Rooman.isRooman(operands[0]) && Rooman.isRooman(operands[1])){
            number1 = Rooman.convertToArabiam(operands[0]);
            number2 = Rooman.convertToArabiam(operands[1]);
            isRooman = true;
        }
        else if(!Rooman.isRooman(operands[0]) && !Rooman.isRooman(operands[1])){
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            isRooman = false;
        }
        else {
            throw new Exception("используются одновременно разные системы счисления, числа должны быть в одном формате");
        }
        if(number1 > 10 || number2 > 10){
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabian = calculatorItog(number1, number2, operation);
        if(isRooman){
            if(arabian <= 0){
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            result = Rooman.convertToRooman(arabian);
        }
        else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    public static String detectOper(String expression) {
        if (expression.contains("+")) {
            return "+";
        }
        if (expression.contains("-")) {
            return "-";
        }
        if (expression.contains("*")) {
            return "*";
        }
        if (expression.contains("/")) {
            return "/";
        } else {
            return null;
        }
    }

    public static int calculatorItog(int a, int b, String oper) throws ArithmeticException{
        if(oper.equals("+")){
            return a + b;
        }  if(oper.equals("-")){
            return a - b;
        }  if(oper.equals("*")){
            return a * b;
        }  else {
            if( b == 0){
                throw new ArithmeticException("Деление на ноль невозможно");
            }
            return a / b;
        }
    }
}

class Rooman {
    public static String[] roomanArray = new String[]{"0", "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV",
            "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV",
            "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV",
            "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV",
            "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV",
            "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV",
            "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
            "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV",
            "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static boolean isRooman(String value) {
        for (int i = 0; i < roomanArray.length; i++) {
            if (value.equals(roomanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabiam (String rooman){
        for (int i = 0; i < roomanArray.length; i++) {
            if(rooman.equals(roomanArray[i])){
                return i;
            }
        }
        return -1;
    }

    public static String convertToRooman(int arabian){
        return roomanArray[arabian];
    }
}
