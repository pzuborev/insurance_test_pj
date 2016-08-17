package org.demo.controller.insurance.calculator;

import org.demo.dto.calculator.CalcDataDto;
import org.demo.dto.calculator.RiskForCalcDto;
import org.demo.entity.insurance.PolicyEventRisk;
import org.demo.service.insurance.calculator.CalcData;
import org.demo.service.insurance.calculator.CalculatorService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/calculator")
@CrossOrigin(value = "*")
public class CalculatorController {
    @Autowired
    CalculatorService calculatorService;

    private ModelMapper modelMapper;

    public ModelMapper getModelMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            // конверация из гривны в копейки (double to int)
            Converter<Double, Long> toCurrency = new AbstractConverter<Double, Long>() {
                protected Long convert(Double source) {
                    long intValue = (long) (source * 100);
                    return source == null ? null : Long.valueOf(intValue);
                }
            };
            //конвертация из копеек в гривны (int to double)
            Converter<Long, Double> fromCurrency = new AbstractConverter<Long, Double>() {
                protected Double convert(Long source) {
                    double doubleValue = (double)source / 100;
                    return source == null ? null : Double.valueOf(doubleValue);
                }
            };

            PropertyMap<RiskForCalcDto, PolicyEventRisk> riskMap = new PropertyMap<RiskForCalcDto, PolicyEventRisk>() {
                protected void configure() {
                    using(toCurrency).map().setRiskAmount(
                            (int) source.getRiskAmount()
                    );
                    using(toCurrency).map().setPayAmount(
                            (int) source.getPayAmount()
                    );
                }
            };
            modelMapper.addMappings(riskMap);

            PropertyMap<PolicyEventRisk, RiskForCalcDto> riskDtoMap = new PropertyMap<PolicyEventRisk, RiskForCalcDto>() {
                protected void configure() {
                    using(fromCurrency).map().setRiskAmount(
                            source.getRiskAmount()
                    );
                    using(fromCurrency).map().setPayAmount(
                            source.getPayAmount()
                    );
                }
            };
            modelMapper.addMappings(riskDtoMap);
        }
        return modelMapper;
    }


    private PolicyEventRisk convertToEntity(RiskForCalcDto riskForCalcDto) {
        return getModelMapper().map(riskForCalcDto, PolicyEventRisk.class);
    }
    private CalcData convertToEntity(CalcDataDto calcDataDto){
        return getModelMapper().map(calcDataDto, CalcData.class);
    }
    private RiskForCalcDto convertToDto(PolicyEventRisk policyEventRisk) {
        return getModelMapper().map(policyEventRisk, RiskForCalcDto.class);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalcDataDto> performCalc(@RequestBody CalcDataDto calcDataDto) {
        System.out.println("*** performCalc");

        List<PolicyEventRisk> risks =
                calcDataDto.getRisks().stream()
                        .map(item -> convertToEntity(item)).collect(Collectors.toList());

        CalcData calcData = convertToEntity(calcDataDto);

        calculatorService.perform(calcData, risks);

        List<RiskForCalcDto> risksDto =
                risks.stream()
                    .map(item -> convertToDto(item)).collect(Collectors.toList());

        calcDataDto.setRisks(risksDto);

        return new ResponseEntity(calcDataDto, HttpStatus.OK);
    }
}
