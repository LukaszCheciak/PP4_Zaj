package pl.jkanclerz.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    String cardNumber;
    private BigDecimal balance;
    private BigDecimal credit;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void assignCredit(BigDecimal creditAmount) {
        if(isAlreadyAssigned()){
            throw new CantAssignCreditTwiceException();
        }
        if (isBelowThreshold(creditAmount)){
            throw new CreditLimitBelowThresholdException();
        }
        this.credit= creditAmount;
        this.balance = creditAmount;
    }

    private boolean isAlreadyAssigned() {
        if(credit != null){
            return true;
        } return false;
    }

    public void withdraw(BigDecimal withdrawnAmount){
        if(withdrawingOverLimit(withdrawnAmount)){
            throw new CantWithdrawMoreThanLimitException();
        }
        balance = balance.subtract(withdrawnAmount);

    }

    private boolean withdrawingOverLimit(BigDecimal withdrawnAmount) {
        if(withdrawnAmount.compareTo(credit.add(balance))<0){
            return false;
        } return true;
    }

    private boolean isBelowThreshold(BigDecimal creditAmount) {
        return creditAmount.compareTo(BigDecimal.valueOf(100))<0;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
