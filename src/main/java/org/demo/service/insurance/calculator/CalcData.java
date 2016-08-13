package org.demo.service.insurance.calculator;


import org.demo.entity.insurance.PolicyEventRisk;
import org.demo.entity.insurance.refs.*;

import java.sql.Date;
import java.util.List;

public class CalcData {

    private String insuredGender;
    private Currency currency;
    private Date arcDate;
    private InsuranceScheme insuranceScheme;
    private InsuranceSchemeRule insuranceSchemeRule;
    private Date insuredBirthDate;
    private Frequency paymentFrequency;
    private Region region;
    private List<PolicyEventRisk> risks;

    public String getInsuredGender() {
        return insuredGender;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getArcDate() {
        return arcDate;
    }

    public void setArcDate(Date arcDate) {
        this.arcDate = arcDate;
    }

    public InsuranceScheme getInsuranceScheme() {
        return insuranceScheme;
    }

    public void setInsuranceScheme(InsuranceScheme insuranceScheme) {
        this.insuranceScheme = insuranceScheme;
    }

    public InsuranceSchemeRule getInsuranceSchemeRule() {
        return insuranceSchemeRule;
    }

    public void setInsuranceSchemeRule(InsuranceSchemeRule insuranceSchemeRule) {
        this.insuranceSchemeRule = insuranceSchemeRule;
    }

    public Date getInsuredBirthDate() {
        return insuredBirthDate;
    }

    public void setInsuredBirthDate(Date insuredBirthDate) {
        this.insuredBirthDate = insuredBirthDate;
    }

    public Frequency getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(Frequency paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<PolicyEventRisk> getRisks() {
        return risks;
    }

    public void setRisks(List<PolicyEventRisk> risks) {
        this.risks = risks;
    }
}
