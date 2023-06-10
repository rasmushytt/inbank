package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.LoanCalculatorService;
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

        LoanResult result = LoanCalculatorService.calcMaxLoanAmount(request);
        LoanResponse response = generateLoanResponse(result);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    public LoanResponse generateLoanResponse(LoanResult loanResult) {
        return new LoanResponse(loanResult.getApprovedLoanAmount(), loanResult.getApprovedLoanPeriod(), loanResult.getLoanResult());
    }
}

