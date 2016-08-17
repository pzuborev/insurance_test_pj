package org.demo.service.insurance.calculator;

import org.demo.dao.insurance.tariff.TableTariffDao;
import org.demo.entity.insurance.PolicyEventRisk;
import org.demo.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CalculatorService {
    @Autowired
    TableTariffDao tableTariffDao;

    private CalcRiskRule getCalcRules(CalcData calcData) {
        Assert.notNull(tableTariffDao, "tableTariffDao is null");
        CalcRiskRule riskRule = new CalcRiskRule(tableTariffDao);

        return riskRule;
    }

    public void perform(CalcData calcData, List<PolicyEventRisk> policyEventRisks) {
        CalcRiskRule calcRiskRule = getCalcRules(calcData);
        HashMap<Integer, BigDecimal> risksTariff = calcRiskRule.getNetTariff(calcData, policyEventRisks);

        BigDecimal defaultTariff = new BigDecimal(1);
        for (PolicyEventRisk risk : policyEventRisks) {

            BigDecimal tariff = risksTariff.get(risk.getRiskType().getId());

            if (tariff == null) tariff = defaultTariff;
            risk.setNettoTariff(tariff);

            if (risk.getPayAmount() > 0) {
                long riskAmount = Math.round(risk.getPayAmount() / tariff.doubleValue());
                risk.setRiskAmount(riskAmount);
            } else if (risk.getRiskAmount() > 0) {
                long payAmount = Math.round(risk.getRiskAmount() * tariff.doubleValue());
                risk.setPayAmount(payAmount);
            } else
                throw new ApiException("Не достаточно данных для расчета условий страхования. Задайте сумму взноса или страховую сумму");
        }
    }

}
