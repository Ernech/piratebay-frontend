package com.example.piratebayfrontend.Model;


public class OrderModel {
    private Integer providerProductId;
    private Integer orderId;
    private String providerName;
    private String dateReceived;
    private String dateRequested;
    private String receipt;
    private Integer qttyOrdered;
    private Integer qttyCommit;
    private Integer qttyReceived;

    public OrderModel() {
    }

    public OrderModel(Integer providerProductId, Integer orderId, String providerName, String dateReceived, String dateRequested, String receipt, Integer qttyOrdered, Integer qttyCommit, Integer qttyReceived) {
        this.providerProductId = providerProductId;
        this.orderId = orderId;
        this.providerName = providerName;
        this.dateReceived = dateReceived;
        this.dateRequested = dateRequested;
        this.receipt = receipt;
        this.qttyOrdered = qttyOrdered;
        this.qttyCommit = qttyCommit;
        this.qttyReceived = qttyReceived;
    }

    public Integer getProviderProductId() {
        return providerProductId;
    }

    public void setProviderProductId(Integer providerProductId) {
        this.providerProductId = providerProductId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(String dateRequested) {
        this.dateRequested = dateRequested;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Integer getQttyOrdered() {
        return qttyOrdered;
    }

    public void setQttyOrdered(Integer qttyOrdered) {
        this.qttyOrdered = qttyOrdered;
    }

    public Integer getQttyCommit() {
        return qttyCommit;
    }

    public void setQttyCommit(Integer qttyCommit) {
        this.qttyCommit = qttyCommit;
    }

    public Integer getQttyReceived() {
        return qttyReceived;
    }

    public void setQttyReceived(Integer qttyReceived) {
        this.qttyReceived = qttyReceived;
    }
}
