import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int principle = (int) readNumber("Principle: ", 1000, 1_0000_000);
        float annualInterest = (float) readNumber("Annual interest Rate: ", 1,30);
        byte years = (byte) readNumber("Period(Years): ", 1, 30);
        printMortgage(principle, annualInterest, years);
        printPaymentSchedule(principle, annualInterest, years);
        // Print total interest Paid
    }

    private static void printMortgage(int principle, float annualInterest, byte years) {
        double mortgage = calculateMortgage(principle, annualInterest, years);

        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Your Monthly Payment : " + NumberFormat.getCurrencyInstance().format(mortgage));
    }

    private static void printPaymentSchedule(int principle, float annualInterest, byte years) {
        System.out.println();
        System.out.println("Payment Schedule");
        System.out.println("----------------");
        for(short month = 1; month <= years * MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principle, annualInterest, years, month);
            System.out.println (month + " : " + NumberFormat.getCurrencyInstance().format(balance));

        }
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.println(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter the value between " + min + "and" + max);
        }
        return value;
    }
    public static double calculateBalance (int principle, float annualInterest, byte years, short numbersOfPaymentsMade){
         short numbersOfPayments = (short) (years * MONTHS_IN_YEAR);
         float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        double balance = principle
                * (Math.pow(1 + monthlyInterest, numbersOfPayments) - Math.pow(1 + monthlyInterest, numbersOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numbersOfPayments)-1);
        return balance;
    }
    public static double calculateMortgage(int principle, float annualInterest, byte years){
         short numbersOfPayments = (short) (years * MONTHS_IN_YEAR);
         float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        double mortgage = principle
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numbersOfPayments))
                / (Math.pow(1 + monthlyInterest, numbersOfPayments)-1);
        return mortgage;
    }
}
