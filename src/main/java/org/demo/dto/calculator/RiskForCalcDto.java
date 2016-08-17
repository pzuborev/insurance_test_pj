package org.demo.dto.calculator;

import org.demo.dto.refs.EventRiskTypeLookupDto;
import org.demo.dto.refs.ForIndividualTypeDto;

import java.math.BigDecimal;


public class RiskForCalcDto {
    private int rowNo;
    private EventRiskTypeLookupDto riskType;
    private ForIndividualTypeDto forIndividualType;
    private double riskAmount;
    private double payAmount;
    private BigDecimal nettoTariff;
    private int payCount;
    private int payTerm;
    private int term;

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public EventRiskTypeLookupDto getRiskType() {
        return riskType;
    }

    public void setRiskType(EventRiskTypeLookupDto riskType) {
        this.riskType = riskType;
    }

    public ForIndividualTypeDto getForIndividualType() {
        return forIndividualType;
    }

    public void setForIndividualType(ForIndividualTypeDto forIndividualType) {
        this.forIndividualType = forIndividualType;
    }

    public double getRiskAmount() {
        return riskAmount;
    }

    public void setRiskAmount(double riskAmount) {
        this.riskAmount = riskAmount;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getNettoTariff() {
        return nettoTariff;
    }

    public void setNettoTariff(BigDecimal nettoTariff) {
        this.nettoTariff = nettoTariff;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    public int getPayTerm() {
        return payTerm;
    }

    public void setPayTerm(int payTerm) {
        this.payTerm = payTerm;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "RiskForCalcDto{" +
                "rowNo=" + rowNo +
                ", riskType=" + riskType +
                ", forIndividualType=" + forIndividualType +
                ", riskAmount=" + riskAmount +
                ", payAmount=" + payAmount +
                ", nettoTariff=" + nettoTariff +
                ", payCount=" + payCount +
                ", payTerm=" + payTerm +
                ", term=" + term +
                '}';
    }
}
