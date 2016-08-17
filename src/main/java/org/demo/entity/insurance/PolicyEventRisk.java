package org.demo.entity.insurance;

import org.demo.entity.insurance.refs.EventRiskType;
import org.demo.entity.insurance.refs.ForIndividualType;

import java.math.BigDecimal;

public class PolicyEventRisk {

    private EventRiskType riskType;
    private ForIndividualType forIndividualType;
    private long riskAmount;
    private long payAmount;
    private BigDecimal nettoTariff;
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

    public long getRiskAmount() {
        return riskAmount;
    }

    public void setRiskAmount(long riskAmount) {
        this.riskAmount = riskAmount;
    }

    public long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(long payAmount) {
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
