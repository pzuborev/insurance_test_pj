package org.demo.dto.calculator;

import org.demo.dto.lookup.*;

import java.sql.Date;
import java.util.List;

public class CalcData {
    private String insuredGender;
    private CurrencyLookupDto currency;
    private Date arcDate;
    private SchemeLookupDto insuranceScheme;
    private SchemeRuleLookupDto insuranceSchemeRule;
    private Date insuredBirthDate;
    private FrequencyLookupDto paymentFrequency;
    private RegionLookupDto region;
    private List<RiskForCalc> risks;


    public String getInsuredGender() {
        return insuredGender;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public CurrencyLookupDto getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyLookupDto currency) {
        this.currency = currency;
    }

    public Date getArcDate() {
        return arcDate;
    }

    public void setArcDate(Date arcDate) {
        this.arcDate = arcDate;
    }

    public SchemeLookupDto getInsuranceScheme() {
        return insuranceScheme;
    }

    public void setInsuranceScheme(SchemeLookupDto insuranceScheme) {
        this.insuranceScheme = insuranceScheme;
    }

    public SchemeRuleLookupDto getInsuranceSchemeRule() {
        return insuranceSchemeRule;
    }

    public void setInsuranceSchemeRule(SchemeRuleLookupDto insuranceSchemeRule) {
        this.insuranceSchemeRule = insuranceSchemeRule;
    }

    public Date getInsuredBirthDate() {
        return insuredBirthDate;
    }

    public void setInsuredBirthDate(Date insuredBirthDate) {
        this.insuredBirthDate = insuredBirthDate;
    }

    public FrequencyLookupDto getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(FrequencyLookupDto paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public RegionLookupDto getRegion() {
        return region;
    }

    public void setRegion(RegionLookupDto region) {
        this.region = region;
    }

    public List<RiskForCalc> getRisks() {
        return risks;
    }

    public void setRisks(List<RiskForCalc> risks) {
        this.risks = risks;
    }

    @Override
    public String toString() {
        return "CalcData{" +
                "insuredGender='" + insuredGender + '\'' +
                ", currency=" + currency +
                ", arcDate=" + arcDate +
                ", insuranceScheme=" + insuranceScheme +
                ", insuranceSchemeRule=" + insuranceSchemeRule +
                ", insuredBirthDate=" + insuredBirthDate +
                ", paymentFrequency=" + paymentFrequency +
                ", region=" + region +
                ", risks=" + risks +
                '}';
    }
}
