package com.example.piratebayfrontend.Model;

public class KardexModel {

    private String dateReceived;
    private String concept;
    private String receipt;
    private Double unitValue;
    private Integer inputQuantity;
    private Double inputValue;
    private Integer balanceQuantity;
    private Double balanceValue;

    public KardexModel() {
    }

    public KardexModel(String dateReceived, String concept, String receipt, Double unitValue, Integer inputQuantity, Double inputValue, Integer balanceQuantity, Double balanceValue) {
        this.dateReceived = dateReceived;
        this.concept = concept;
        this.receipt = receipt;
        this.unitValue = unitValue;
        this.inputQuantity = inputQuantity;
        this.inputValue = inputValue;
        this.balanceQuantity = balanceQuantity;
        this.balanceValue = balanceValue;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }

    public Integer getInputQuantity() {
        return inputQuantity;
    }

    public void setInputQuantity(Integer inputQuantity) {
        this.inputQuantity = inputQuantity;
    }

    public Double getInputValue() {
        return inputValue;
    }

    public void setInputValue(Double inputValue) {
        this.inputValue = inputValue;
    }

    public Integer getBalanceQuantity() {
        return balanceQuantity;
    }

    public void setBalanceQuantity(Integer balanceQuantity) {
        this.balanceQuantity = balanceQuantity;
    }

    public Double getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(Double balanceValue) {
        this.balanceValue = balanceValue;
    }
}
