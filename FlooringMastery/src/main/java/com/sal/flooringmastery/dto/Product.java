
package com.sal.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author vicmaia
 */
public class Product {

    private String productType;
    private BigDecimal costPerSqFt;
    private BigDecimal LaborCostPerSqFt;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return LaborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal LaborCostPerSqFt) {
        this.LaborCostPerSqFt = LaborCostPerSqFt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.productType);
        hash = 41 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 41 * hash + Objects.hashCode(this.LaborCostPerSqFt);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.LaborCostPerSqFt, other.LaborCostPerSqFt)) {
            return false;
        }
        return true;
    }


}
