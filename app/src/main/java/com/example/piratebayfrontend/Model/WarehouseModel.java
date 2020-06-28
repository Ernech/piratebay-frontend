package com.example.piratebayfrontend.Model;

public class WarehouseModel {

    private Integer warehouseId;
    private String warehouseName;

    public WarehouseModel() {
    }

    public WarehouseModel(Integer warehouseId, String warehouseName) {
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
