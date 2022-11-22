package com.sal.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
/**
 *
 * @author vicmaia
 */
public class Order {

    Tax tax = new Tax();
    Product product = new Product();

    private int orderNumber;
    private String customerName;
    private String state = tax.getState();
    private BigDecimal taxRate = tax.getTaxRate();
    private String productType = product.getProductType();
    private BigDecimal area;
    private BigDecimal costPerSqFt = product.getCostPerSqFt();
    private BigDecimal laborCostPerSqFt = product.getLaborCostPerSqFt();
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    private LocalDate timeStamp;

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.tax);
        hash = 53 * hash + Objects.hashCode(this.product);
        hash = 53 * hash + Objects.hashCode(this.orderNumber);
        hash = 53 * hash + Objects.hashCode(this.customerName);
        hash = 53 * hash + Objects.hashCode(this.state);
        hash = 53 * hash + Objects.hashCode(this.taxRate);
        hash = 53 * hash + Objects.hashCode(this.productType);
        hash = 53 * hash + Objects.hashCode(this.area);
        hash = 53 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 53 * hash + Objects.hashCode(this.laborCostPerSqFt);
        hash = 53 * hash + Objects.hashCode(this.materialCost);
        hash = 53 * hash + Objects.hashCode(this.laborCost);
        hash = 53 * hash + Objects.hashCode(this.totalTax);
        hash = 53 * hash + Objects.hashCode(this.totalCost);
        hash = 53 * hash + Objects.hashCode(this.timeStamp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSqFt, other.laborCostPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        if (!Objects.equals(this.timeStamp, other.timeStamp)) {
            return false;
        }
        return true;
    }



}
