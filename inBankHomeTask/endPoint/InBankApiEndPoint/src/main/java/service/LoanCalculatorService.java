package service;

import controller.LoanResult;

public class LoanCalculatorService {
    public static LoanResult calcMaxLoanAmount(LoanRequest request) {
        double creditModifier = request.getCreditModifier();
        double loanAmount = request.getRequestedLoanAmount();
        double loanPeriod = request.getRequestedLoanPeriod();
        double creditScore = calcCreditScore(creditModifier, loanAmount, loanPeriod);
        double finalLoanAmount = 0;
        double finalLoanPeriod = 0;
        double copyOfLoanAmount = loanAmount;
        double copyOfLoanPeriod = loanPeriod;
        double newCreditScore = 0;
        LoanResult result = new LoanResult();

        if(creditScore == 1) {
            result.setLoanResult(true);
            result.setApprovedLoanPeriod(loanPeriod);
            result.setApprovedLoanAmount(loanAmount);
            return result;
        }
        if (creditScore > 1) {
            for(double i = 0; i < 8000; i++) {
                copyOfLoanAmount++;
                newCreditScore = calcCreditScore(creditModifier, copyOfLoanAmount, loanPeriod);
                if (newCreditScore <= 1) {
                    break;
                } else if(copyOfLoanAmount >= 10000) {
                    copyOfLoanAmount = 10000;
                    break;
                }
            }
        }
        if (creditScore < 1) {
            copyOfLoanPeriod = 12;
            for(double i = 12; i < 60; i++) {
                copyOfLoanPeriod++;
                newCreditScore = calcCreditScore(creditModifier, loanAmount, copyOfLoanPeriod);
                if (newCreditScore >= 1) {
                    break;
                } else if (copyOfLoanPeriod > 60) {
                    break;
                }
            }
        }

        if (creditScore < 1 && copyOfLoanPeriod >= 60 && copyOfLoanAmount <= 10000 ) {
            if(newCreditScore != 1) {
                copyOfLoanPeriod = 60;
                for(double i = 0; i < 8000; i++) {
                    copyOfLoanAmount--;
                    newCreditScore = calcCreditScore(creditModifier, copyOfLoanAmount, copyOfLoanPeriod);
                    if (newCreditScore == 1) {
                        break;
                    } else if (copyOfLoanAmount == 1999) {
                        copyOfLoanAmount = 0;
                        copyOfLoanPeriod = 0;
                        break;
                    }
                }
            }
        }

        finalLoanAmount = copyOfLoanAmount;
        finalLoanPeriod = copyOfLoanPeriod;

        if (finalLoanAmount == 0 || finalLoanPeriod == 0) {
            result.setLoanResult(false);
        } else {
            result.setLoanResult(true);
            result.setApprovedLoanAmount(finalLoanAmount);
            result.setApprovedLoanPeriod(finalLoanPeriod);
        }
        return result;
    }

    //Suurenda/vähenda laenu summat/perioodi kuni creditScore väärtus on 1.
    private static double calcCreditScore(double creditModifier, double loanAmount, double loanPeriod) {
        return (creditModifier / loanAmount) * loanPeriod;
    }
}
