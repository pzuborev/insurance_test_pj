package org.demo.service.insurance.calculator;

import org.demo.dao.insurance.tariff.TableTariffDao;
import org.demo.entity.insurance.PolicyEventRisk;
import org.demo.entity.insurance.refs.InsuranceScheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CalcRiskRule {
    private TableTariffDao tableTariffDao;

    public CalcRiskRule(TableTariffDao tableTariffDao) {
        this.tableTariffDao = tableTariffDao;
    }

    public HashMap<Integer, BigDecimal> getNetTariff(CalcData calcData, List<PolicyEventRisk> policyEventRisks) {
        List<Integer> riskIds = policyEventRisks.stream().map(item -> item.getRiskType().getId()).collect(Collectors.toList());


        HashMap<Integer, BigDecimal> risksTariff = tableTariffDao.getTableTariff(
                calcData.getInsuranceScheme(),
                calcData.getRegion(),
                calcData.getArcDate(),
                riskIds,
                calcData.getInsuredGender(),
                60
        );
        return risksTariff;
    }
}
