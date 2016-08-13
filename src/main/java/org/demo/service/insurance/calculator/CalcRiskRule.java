package org.demo.service.insurance.calculator;

import org.demo.entity.insurance.PolicyEventRisk;
import org.demo.entity.insurance.refs.InsuranceScheme;

public class CalcRiskRule {

    public double getNetTariff(CalcData calcData, PolicyEventRisk risk) {
        return 1;
    }
}
