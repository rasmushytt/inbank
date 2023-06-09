package service;

public class LoanRequest {
    private double requestedLoanAmount;
    private double requestedLoanPeriod;
    private double creditModifier;
    public double getRequestedLoanAmount() {
        return requestedLoanAmount;
    }
    public void setRequestedLoanAmount(double requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }
    public double getRequestedLoanPeriod() {
        return requestedLoanPeriod;
    }
    public void setRequestedLoanPeriod(double requestedLoanPeriod) {
        this.requestedLoanPeriod = requestedLoanPeriod;
    }
    public double getCreditModifier() {
        return creditModifier;
    }
    public double setCreditModifier(double creditModifier) {
        return this.creditModifier = creditModifier;
    }
}