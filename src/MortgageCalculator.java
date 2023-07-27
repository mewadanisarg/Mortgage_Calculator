public class MortgageCalculator {

    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;
    private int principle;
    private float annualInterest;
    private byte years;

    /**
     * Constructor MortgageCalculator
     * Initialising
     */

    public MortgageCalculator(int principle, float annualInterest, byte years) {
        this.principle = principle;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateBalance(short numbersOfPaymentsMade) {
        short numbersOfPayments = (short) getNumbersOfPayments();
        float monthlyInterest = getMonthlyInterest();
        double balance;
        balance = principle
                * (Math.pow(1 + monthlyInterest, numbersOfPayments) - Math.pow(1 + monthlyInterest, numbersOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numbersOfPayments) - 1);
        return balance;
    }

    public double calculateMortgage() {
        short numbersOfPayments = (short) (years * MONTHS_IN_YEAR);
        float monthlyInterest = getMonthlyInterest();
        double mortgage;
        mortgage = principle
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numbersOfPayments))
                / (Math.pow(1 + monthlyInterest, numbersOfPayments) - 1);
        return mortgage;
    }

    public double [] getRemainingBalances() {
        var balances = new double[getNumbersOfPayments()];
        for (short month = 1; month <= balances.length; month++)
            balances[month-1] = calculateBalance(month);
        return balances;
    }

    //All Getter

    private int getNumbersOfPayments() {
        return years * MONTHS_IN_YEAR;
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }
}
