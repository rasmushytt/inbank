package service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponse {
    @JsonProperty("loanResult")
    private boolean loanResult;
    @JsonProperty("approvedLoanAmount")
    private double approvedLoanAmount;
    @JsonProperty("approvedLoanPeriod")
    private double approvedLoanPeriod;

    public void setApprovedLoanAmount(double approvedLoanPeriod) {
        this.approvedLoanPeriod = approvedLoanPeriod;
    }
    public void setApprovedLoanPeriod(double approvedLoanAmount) {
        this.approvedLoanAmount = approvedLoanAmount;
    }
    public void setLoanResult(boolean loanResult) {
        this.loanResult = loanResult;
    }

    public LoanResponse(double approvedLoanAmount) {
        this.approvedLoanAmount = approvedLoanAmount;
    }
    public LoanResponse(double approvedLoanAmount, double approvedLoanPeriod, boolean loanResult) {
        this.approvedLoanAmount = approvedLoanAmount;
        this.approvedLoanPeriod = approvedLoanPeriod;
        this.loanResult = loanResult;
    }
}
