package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.LoanRequest;
import service.LoanResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
public class LoanController {
    @PostMapping("/api/inBankApi/approval")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<LoanResponse> processLoanRequest(@RequestBody LoanRequest request) {
        request.setRequestedLoanAmount(request.getRequestedLoanAmount());
        request.setRequestedLoanPeriod(request.getRequestedLoanPeriod());
        request.setCreditModifier(request.getCreditModifier());

        LoanResult result = calcMaxLoanAmount(request);
        LoanResponse response = generateLoanResponse(result);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    private LoanResult calcMaxLoanAmount(LoanRequest request) {
        double creditModifier = request.getCreditModifier();
        double loanAmount = request.getRequestedLoanAmount();
        double loanPeriod = request.getRequestedLoanPeriod();
        double creditScore = calcCreditScore(creditModifier, loanAmount, loanPeriod);
        double finalLoanAmount = 0;
        double finalLoanPeriod = 0;
        double copyOfLoanAmount = loanAmount;
        double copyOfLoanPeriod = loanPeriod;
        LoanResult result = new LoanResult();

        if(creditScore == 1) {
            result.setLoanResult(false);
            return result;
        }
        if (creditScore > 1) {
            for(double i = 0; i < 8000; i++) {
                copyOfLoanAmount++;
                double newCreditScore = calcCreditScore(creditModifier, copyOfLoanAmount, loanPeriod);
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
                double newCreditScore = calcCreditScore(creditModifier, loanAmount, copyOfLoanPeriod);
                if (newCreditScore >= 1) {
                    break;
                } else if (copyOfLoanPeriod > 60) {
                    break;
                }
            }
        }
        if (creditScore < 1 && copyOfLoanPeriod >= 60 && copyOfLoanAmount <= 10000 ) {
            copyOfLoanPeriod = 60;
            for(double i = 0; i < 8000; i++) {
                copyOfLoanAmount--;
                double newCreditScore = calcCreditScore(creditModifier, copyOfLoanAmount, copyOfLoanPeriod);
                if (newCreditScore == 1) {
                    break;
                } else if (copyOfLoanAmount == 1999) {
                    copyOfLoanAmount = 0;
                    copyOfLoanPeriod = 0;
                    break;
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
    private double calcCreditScore(double creditModifier, double loanAmount, double loanPeriod) {
        return (creditModifier / loanAmount) * loanPeriod;
    }

    public LoanResponse generateLoanResponse(LoanResult loanResult) {
        return new LoanResponse(loanResult.getApprovedLoanAmount(), loanResult.getApprovedLoanPeriod(), loanResult.getLoanResult());
    }
}

