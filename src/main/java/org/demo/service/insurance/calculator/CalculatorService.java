package org.demo.service.insurance.calculator;

import org.demo.entity.insurance.PolicyEventRisk;
import org.demo.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CalculatorService {

    private CalcRiskRule getCalcRules(CalcData calcData, PolicyEventRisk risk) {
        return new CalcRiskRule();
    }

    public void perform(CalcData calcData, List<PolicyEventRisk> policyEventRisks) {
        for (PolicyEventRisk risk : policyEventRisks) {
            CalcRiskRule calcRiskRule = getCalcRules(calcData, risk);
            double tariff = calcRiskRule.getNetTariff(calcData, risk);

            risk.setNettoTariff(tariff);

            if (risk.getPayAmount() > 0) {
                Long riskAmount = Math.round(risk.getPayAmount() / tariff);
                risk.setRiskAmount(riskAmount.intValue());
            } else if (risk.getRiskAmount() > 0) {
                Long payAmount = Math.round(risk.getRiskAmount() * tariff);
                risk.setPayAmount(payAmount.intValue());
            } else
                throw new ApiException("Не достаточно данных для расчета условий страхования. Задайте сумму взноса или страховую сумму");
        }
    }

}
