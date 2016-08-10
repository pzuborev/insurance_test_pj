package org.demo.dto.calculator;

import org.demo.dto.lookup.EventRiskTypeLookupDto;
import org.demo.dto.lookup.ForIndividualType;

public class RiskForCalc {
    private int rowNo;
    private EventRiskTypeLookupDto riskType;
    private ForIndividualType forIndividualType;
    private double riskAmount;
    private double payAmount;
    private double nettoTariff;
    private double payCount;
    private double payTerm;
    private double term;

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

    public ForIndividualType getForIndividualType() {
        return forIndividualType;
    }

    public void setForIndividualType(ForIndividualType forIndividualType) {
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

    public double getNettoTariff() {
        return nettoTariff;
    }

    public void setNettoTariff(double nettoTariff) {
        this.nettoTariff = nettoTariff;
    }

    public double getPayCount() {
        return payCount;
    }

    public void setPayCount(double payCount) {
        this.payCount = payCount;
    }

    public double getPayTerm() {
        return payTerm;
    }

    public void setPayTerm(double payTerm) {
        this.payTerm = payTerm;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "RiskForCalc{" +
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
