package com.example.piratebayfrontend.Model;

public class KardexInformationModel {
    private String productCode;
    private String productName;
    private String format;
    private String warehouseAddress;
    private String providerName;

    public KardexInformationModel() {

    }

    public KardexInformationModel(String productCode, String productName, String format, String warehouseAddress, String providerName) {
        this.productCode = productCode;
        this.productName = productName;
        this.format = format;
        this.warehouseAddress = warehouseAddress;
        this.providerName = providerName;
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

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
