import org.demo.controller.insurance.calculator.CalculatorController;
import org.demo.dto.calculator.CalcDataDto;
import org.demo.dto.calculator.RiskForCalcDto;
import org.demo.dto.refs.*;
import org.demo.entity.insurance.PolicyEventRisk;
import org.demo.entity.insurance.refs.EventRiskType;
import org.demo.entity.insurance.refs.ForIndividualType;
import org.demo.service.insurance.calculator.CalcData;
import org.junit.Assert;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

public class TestMapper {

    private RiskForCalcDto getRiskDto(double amount) {


        RiskForCalcDto riskDto = new RiskForCalcDto();

        riskDto.setRowNo(1);

        riskDto.setRiskType(new EventRiskTypeLookupDto(1, "survive"));
        riskDto.setForIndividualType(new ForIndividualTypeDto(1, "insured person"));
        riskDto.setRiskAmount(amount);
        riskDto.setPayAmount(20.01);
        riskDto.setPayCount(10);
        riskDto.setPayTerm(10);
        riskDto.setTerm(10);
        riskDto.setNettoTariff(new BigDecimal(amount / 10000));

        return riskDto;
    }

    private PolicyEventRisk getRisk() {
        EventRiskType riskType = new EventRiskType();
        riskType.setId(1);
        riskType.setName("Surv");
        ForIndividualType forIndividualType = new ForIndividualType();
        forIndividualType.setId(1);
        forIndividualType.setName("insured person");

        PolicyEventRisk risk = new PolicyEventRisk();


        risk.setRiskType(riskType);
        risk.setForIndividualType(forIndividualType);
        risk.setRiskAmount(9999999999999L);
        risk.setPayAmount(2001);
        risk.setPayCount(10);
        risk.setPayTerm(10);
        risk.setTerm(10);

        return risk;
    }


    public ModelMapper getModelMapper() {
        CalculatorController calculatorController = new CalculatorController();
        return calculatorController.getModelMapper();
    }

    @Test
    public void testConvertToRiskEntity() throws Exception {
        double amount = 0;
        for (int i = 0; i < 1000; i++) {
            RiskForCalcDto riskDto = getRiskDto(amount);

            PolicyEventRisk risk = getModelMapper().map(riskDto, PolicyEventRisk.class);

            Assert.assertNotNull("risk is null", risk);
            Assert.assertEquals("amount not equal", risk.getRiskAmount(), (int) (riskDto.getRiskAmount() * 100));
            Assert.assertEquals("riskType not equal", risk.getRiskType().getId(), riskDto.getRiskType().getId());
            Assert.assertEquals("forIndividualType not equal", risk.getForIndividualType().getId(), riskDto.getForIndividualType().getId());
            Assert.assertEquals("payamount not equal", risk.getPayAmount(), (int) (riskDto.getPayAmount() * 100));
            Assert.assertEquals("payterm not equal", risk.getPayTerm(), riskDto.getPayTerm());
            Assert.assertEquals("term not equal", risk.getTerm(), riskDto.getTerm());
            Assert.assertEquals("payCount not equal", risk.getPayCount(), riskDto.getPayCount());
            Assert.assertEquals("nt not equal", risk.getNettoTariff().doubleValue(), riskDto.getNettoTariff().doubleValue(), 10e-15);

//            System.out.println(risk.toString());
            RiskForCalcDto riskDto2 = getModelMapper().map(risk, RiskForCalcDto.class);
            Assert.assertEquals("dto1 != dto2, payAmount", riskDto.getPayAmount(), riskDto2.getPayAmount(), 10e-15);

            amount = amount + 0.01;
        }


    }

    private CalcDataDto getCalcDataDto() {
        java.util.Date now = new java.util.Date();

        CalcDataDto calcDataDto = new CalcDataDto();
        calcDataDto.setArcDate(new Date(now.getTime()));
        calcDataDto.setCurrency(new CurrencyLookupDto("USD", "Доллар"));
        calcDataDto.setInsuranceScheme(new SchemeLookupDto(1, "Программа 1"));
        calcDataDto.setInsuranceSchemeRule(new SchemeRuleLookupDto(2, "Правило 2"));

        Calendar date = Calendar.getInstance();
        date.setTime(new java.util.Date());
        date.add(Calendar.YEAR, -65);
        calcDataDto.setInsuredBirthDate(new Date(date.getTimeInMillis()));

        calcDataDto.setInsuredGender("F");
        calcDataDto.setPaymentFrequency(new FrequencyLookupDto("Y", "Ежегодно"));
        calcDataDto.setRegion(new RegionLookupDto(2, "second region"));

        return calcDataDto;
    }

    @Test
    public void testConvertCalcData() throws Exception {
        CalcDataDto calcDataDto = getCalcDataDto();

        CalcData calcData = getModelMapper().map(calcDataDto, CalcData.class);

        Assert.assertEquals("not equal currency code", calcDataDto.getCurrency().getCode(), calcData.getCurrency().getCode());
        Assert.assertEquals("not equal currency name", calcDataDto.getCurrency().getName(), calcData.getCurrency().getName());
        Assert.assertEquals("not equal arcdate", calcDataDto.getArcDate(), calcData.getArcDate());
        Assert.assertEquals("not equal scheme id", calcDataDto.getInsuranceScheme().getId(), calcData.getInsuranceScheme().getId());
        Assert.assertEquals("not equal scheme name", calcDataDto.getInsuranceScheme().getName(), calcData.getInsuranceScheme().getName());
        Assert.assertEquals("not equal rule id", calcDataDto.getInsuranceSchemeRule().getId(), calcData.getInsuranceSchemeRule().getId());
        Assert.assertEquals("not equal rule name", calcDataDto.getInsuranceSchemeRule().getName(), calcData.getInsuranceSchemeRule().getName());
        Assert.assertEquals("not equal birthdate", calcDataDto.getInsuredBirthDate(), calcData.getInsuredBirthDate());
        Assert.assertEquals("not equal gender code", calcDataDto.getInsuredGender(), calcData.getInsuredGender());
        Assert.assertEquals("not equal payment frequency", calcDataDto.getPaymentFrequency().getCode(), calcData.getPaymentFrequency().getCode());
        Assert.assertEquals("not equal payment frequency name", calcDataDto.getPaymentFrequency().getName(), calcData.getPaymentFrequency().getName());
        Assert.assertEquals("not equal region id", calcDataDto.getRegion().getId(), calcData.getRegion().getId());
        Assert.assertEquals("not equal region name", calcDataDto.getRegion().getName(), calcData.getRegion().getName());
        Assert.assertEquals("not equal region code", calcDataDto.getCurrency().getCode(), calcData.getCurrency().getCode());

//        calcDataDto = getModelMapper().map(calcData, CalcDataDto.class);
//
//        Assert.assertEquals("not equal currency code", calcDataDto.getCurrency().getCode(), calcData.getCurrency().getCode());
//        Assert.assertEquals("not equal currency name", calcDataDto.getCurrency().getName(), calcData.getCurrency().getName());
//        Assert.assertEquals("not equal arcdate", calcDataDto.getArcDate(), calcData.getArcDate());
//        Assert.assertEquals("not equal scheme id", calcDataDto.getInsuranceScheme().getId(), calcData.getInsuranceScheme().getId());
//        Assert.assertEquals("not equal scheme name", calcDataDto.getInsuranceScheme().getName(), calcData.getInsuranceScheme().getName());
//        Assert.assertEquals("not equal rule id", calcDataDto.getInsuranceSchemeRule().getId(), calcData.getInsuranceSchemeRule().getId());
//        Assert.assertEquals("not equal rule name", calcDataDto.getInsuranceSchemeRule().getName(), calcData.getInsuranceSchemeRule().getName());
//        Assert.assertEquals("not equal birthdate", calcDataDto.getInsuredBirthDate(), calcData.getInsuredBirthDate());
//        Assert.assertEquals("not equal gender code", calcDataDto.getInsuredGender().getCode(), calcData.getInsuredGender().getCode());
//        Assert.assertEquals("not equal gender name", calcDataDto.getInsuredGender().getName(), calcData.getInsuredGender().getName());
//        Assert.assertEquals("not equal payment frequency", calcDataDto.getPaymentFrequency().getCode(), calcData.getPaymentFrequency().getCode());
//        Assert.assertEquals("not equal payment frequency name", calcDataDto.getPaymentFrequency().getName(), calcData.getPaymentFrequency().getName());
//        Assert.assertEquals("not equal region id", calcDataDto.getRegion().getId(), calcData.getRegion().getId());
//        Assert.assertEquals("not equal region name", calcDataDto.getRegion().getName(), calcData.getRegion().getName());
//        Assert.assertEquals("not equal region code", calcDataDto.getCurrency().getCode(), calcData.getCurrency().getCode());



    }
}
