package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class BankDetails {

    private String bankName;
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private Boolean addedBankAccount = false;

    // ===== MANUAL GETTERS & SETTERS =====

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Boolean getAddedBankAccount() {
        return addedBankAccount;
    }

    public void setAddedBankAccount(Boolean addedBankAccount) {
        this.addedBankAccount = addedBankAccount;
    }
}
