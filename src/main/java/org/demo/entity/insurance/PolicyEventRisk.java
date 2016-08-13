package org.demo.entity.insurance;

import org.demo.entity.insurance.refs.EventRiskType;
import org.demo.entity.insurance.refs.ForIndividualType;

public class PolicyEventRisk {

    private EventRiskType riskType;
    private ForIndividualType forIndividualType;
    private int riskAmount;
    private int payAmount;
    private double nettoTariff;
    private int payCount;
    private int payTerm;
    private int term;

    public EventRiskType getRiskType() {
        return riskType;
    }

    public void setRiskType(EventRiskType riskType) {
        this.riskType = riskType;
    }

    public ForIndividualType getForIndividualType() {
        return forIndividualType;
    }

    public void setForIndividualType(ForIndividualType forIndividualType) {
        this.forIndividualType = forIndividualType;
    }

    public int getRiskAmount() {
        return riskAmount;
    }

    public void setRiskAmount(int riskAmount) {
        this.riskAmount = riskAmount;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public double getNettoTariff() {
        return nettoTariff;
    }

    public void setNettoTariff(double nettoTariff) {
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
        return "PolicyEventRisk{" +
                "riskType=" + riskType +
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
