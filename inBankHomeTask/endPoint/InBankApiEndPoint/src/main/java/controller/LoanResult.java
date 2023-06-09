package controller;

public class LoanResult {
    private boolean loanResult;
    private double approvedLoanAmount;
    private double approvedLoanPeriod;

    public void setApprovedLoanAmount(double approvedLoanAmount) {
        this.approvedLoanAmount = approvedLoanAmount;
    }
    public void setApprovedLoanPeriod(double approvedLoanPeriod) {
        this.approvedLoanPeriod = approvedLoanPeriod;
    }
    public void setLoanResult(boolean loanResult) {
        this.loanResult = loanResult;
    }
    public boolean getLoanResult() {
        return loanResult;
    }
    public double getApprovedLoanAmount() {
        return approvedLoanAmount;
    }
    public double getApprovedLoanPeriod() {
        return approvedLoanPeriod;
    }
}
