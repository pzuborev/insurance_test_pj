package org.demo.dto.lookup;

public class SchemeRiskDto {
    int insuranceschemeid;
    int insurancerisktypeid;
    int forindividualtypeid;
    String insurancerisktypename;
    String insurancerisktypecode;
    String forindividualtypename;

    public int getInsuranceschemeid() {
        return insuranceschemeid;
    }

    public void setInsuranceschemeid(int insuranceschemeid) {
        this.insuranceschemeid = insuranceschemeid;
    }

    public int getInsurancerisktypeid() {
        return insurancerisktypeid;
    }

    public void setInsurancerisktypeid(int insurancerisktypeid) {
        this.insurancerisktypeid = insurancerisktypeid;
    }

    public int getForindividualtypeid() {
        return forindividualtypeid;
    }

    public void setForindividualtypeid(int forindividualtypeid) {
        this.forindividualtypeid = forindividualtypeid;
    }

    public String getInsurancerisktypename() {
        return insurancerisktypename;
    }

    public void setInsurancerisktypename(String insurancerisktypename) {
        this.insurancerisktypename = insurancerisktypename;
    }

    public String getInsurancerisktypecode() {
        return insurancerisktypecode;
    }

    public void setInsurancerisktypecode(String insurancerisktypecode) {
        this.insurancerisktypecode = insurancerisktypecode;
    }

    public String getForindividualtypename() {
        return forindividualtypename;
    }

    public void setForindividualtypename(String forindividualtypename) {
        this.forindividualtypename = forindividualtypename;
    }

    @Override
    public String toString() {
        return "SchemeRiskDto{" +
                "insuranceschemeid=" + insuranceschemeid +
                ", insurancerisktypeid=" + insurancerisktypeid +
                ", forindividualtypeid=" + forindividualtypeid +
                ", insurancerisktypename='" + insurancerisktypename + '\'' +
                ", insurancerisktypecode='" + insurancerisktypecode + '\'' +
                ", forindividualtypename='" + forindividualtypename + '\'' +
                '}';
    }
}
