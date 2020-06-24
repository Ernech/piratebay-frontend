package com.example.piratebayfrontend.Model;


import java.sql.Date;

public class MovieModel {

    private int productId;
    private String productCode;
    private String productName;
    private String format;
    private java.sql.Date creationDate;
    private String providerName;
    private int qttyReceived;

    public MovieModel() {
    }

    public MovieModel(int productId, String productCode, String productName, String format, Date creationDate, String providerName, int qttyReceived) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.format = format;
        this.creationDate = creationDate;
        this.providerName = providerName;
        this.qttyReceived = qttyReceived;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public int getQttyReceived() {
        return qttyReceived;
    }

    public void setQttyReceived(int qttyReceived) {
        this.qttyReceived = qttyReceived;
    }
}
