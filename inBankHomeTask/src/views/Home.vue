<template>
  <v-sheet>
    <v-form>
      <v-container>
        <header class="niceFont">InBankBeta</header>
        <v-row class="my-2">
          <v-col>
            <v-select
              v-model="personalCode"
              class="my-2"
              :label="personalCodeString"
              :items="['49002010965', '49002010976', '49002010987', '49002010998']"
              :color="inBankPurple"
              variant="outlined"
              :menu-props="{ contentClass: 'custom-menu' }"
            ></v-select>
            {{ loanAmountString }}
            <v-row class="my-3 mb-5">
              <v-slider
                v-model="requestedLoanAmount"
                class="ml-5"
                :min="2000"
                :max="10000"
                step="1"
                thumb-label
                thumb-size="15"
                required
                :color="inBankPurple"
              />
              <v-text-field
                v-model="requestedLoanAmount"
                class="shrinkTextField"
                :rules="[isNumeric, loanAmountTooLowOrTooHigh]"
                :label="loanAmountString"
                required
                variant="outlined"
                :color="inBankPurple"
                suffix="€"
              ></v-text-field>
            </v-row>
            {{ loanPeriodString }}
            <v-row class="my-3">
              <v-slider
                v-model="loanPeriod"
                class="ml-5"
                :min="12"
                :max="60"
                thumb-label
                thumb-size="15"
                step="1"
                required
                :color="inBankPurple"
                prepend-icon=""
              />
              <v-text-field
                v-model="loanPeriod"
                class="shrinkTextField"
                :label="loanPeriodString"
                :rules="[isNumeric, loanPeriodTooLowOrTooHigh]"
                required
                variant="outlined"
                :color="inBankPurple"
                suffix="months"
              ></v-text-field>
            </v-row>
            <v-btn
              class="text-white"
              :color="inBankPurple"
              :disabled="!isFormValid"
              @click="sendPersonalCodeToServer"
            > {{ submitButtonString }}</v-btn>
          </v-col>
        </v-row>
        <div class="text-center">
          <v-dialog
            v-model="openDialog"
            v-if="loanResponse"
            width="600px"
          >
            <v-card>
              <v-card-text>
                <v-row v-if="loanResponse.loanResult === false">
                  <v-col>
                  Your loan application has been rejected.
                  </v-col>
                </v-row>
                <v-row v-if="loanResponse.loanResult === true">
                  <v-col class="ml-4">
                    Congratulations! Your loan application has been approved. We are pleased to offer you a
                    maximum loan amount of <span class="bold">{{ loanResponse.approvedLoanAmount }}</span>€
                    with a loan period of <span class="bold">{{ loanResponse.approvedLoanPeriod }}</span> months.
                    <br>
                    <br>
                    <span class="lighter">The calculation is approximate and may differ from the conditions offered to you.
                    </span>
                  </v-col>

                </v-row>
              </v-card-text>
              <v-card-actions>
                <v-btn
                  :color="inBankPurple"
                  block
                  @click="openDialog = false"
                >Close offer </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
        <v-snackbar
        v-model="snackBar"
        > {{ snackBarMessage }}

        </v-snackbar>
      </v-container>
    </v-form>
  </v-sheet>
</template>

<script>
import {validationMixin} from "@/mixins/validationMixin"
import loanCalculatorService from "@/service/loanCalculatorService";
import {mdiCash} from "@mdi/js";

export default {
  name: 'App',
  mixins: [validationMixin],
  data: () => ({
    personalCode: '49002010965',
    requestedLoanAmount: 2000,
    loanPeriod: 12,
    loanCalculatorService: loanCalculatorService,
    creditModifier: 0,
    personalCodeString: "Personal Code",
    loanAmountString: "Loan Amount",
    loanPeriodString: "Loan Period",
    submitButtonString: "Submit application",
    mdiCash: mdiCash,
    inBankPurple: "#6f42c1",
    openDialog: false,
    loanResponse: null,
    snackBar: false,
    snackBarMessage: 'Oops! Something went wrong. Please try again later.',

  }),
  computed: {
    isFormValid() {
      return this.requestedLoanAmount >= 2000 && this.loanPeriod >= 12 && this.loanPeriod <= 60;
    },
  },
  methods: {
    sendPersonalCodeToServer() {
      let loanRequest = this.generateJson()
      this.loanCalculatorService.sendPersonalInfoToBackEnd(loanRequest)
        .then(response => {
          this.snackBar = false
          this.loanResponse = response
          this.openDialog = true
        })
        .catch(error => {
          this.snackBar = true
          console.error('Error:', error);
        });
    },
    generateJson() {
      return {
        personalCode: this.personalCode,
        requestedLoanAmount: this.requestedLoanAmount,
        requestedLoanPeriod: this.loanPeriod,
        creditModifier: this.generateCreditModifier(this.personalCode)
      }
    },
    generateCreditModifier(personalCode) {
      let creditModifier = 0
      if (personalCode === '49002010976') {
        creditModifier = 100
      } else if (personalCode === '49002010987') {
        creditModifier = 300
      } else if (personalCode === '49002010998') {
        creditModifier = 1000
      }
      return creditModifier
    },
  },
}
</script>

<style>
.niceFont {
  font-family: 'Montserrat', sans-serif;
  font-size: 50px;
  color: #2b0055eb;
}

.shrinkTextField{
  width: 25px;
}
.bold {
  font-weight: bold;
}
.custom-menu .v-list-item {
  background-color: #6f42c1;
  color: white;
}
.lighter {
  font-weight: lighter;
  font-size: 13px;
}
</style>
