export const validationMixin = {

  methods: {
    noPersonalCode(value) {
      return !!value || 'Personal code is required'
    },
    personalCodeTooShort(value) {
      if(value) {
        return value.length > 9 || 'Personal code is too short'
      }
    },
    isNumeric(value) {
      if(value) {
        return /^\d+$/.test(value) || 'Personal code must be numeric'
      }
    },
    loanAmountTooLowOrTooHigh(value) {
      if(value) {
        return value >= 2000 && value <= 10000 || 'Loan amount must be between 2000 and 10000'
      }
    },
    loanPeriodTooLowOrTooHigh(value) {
      if(value) {
        return value >= 12 && value <= 60 || 'Loan period must be between 12 and 60 months'
      }
    }
  }
}
