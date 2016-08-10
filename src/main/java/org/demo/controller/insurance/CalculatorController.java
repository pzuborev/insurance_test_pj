package org.demo.controller.insurance;

import org.demo.dto.calculator.CalcData;
import org.demo.dto.calculator.RiskForCalc;
import org.demo.service.insurance.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/calculator")
@CrossOrigin(value = "*")
public class CalculatorController {
    @Autowired
    CalculatorService calculatorService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CalcData> performCalc(@RequestBody CalcData calcData) {
        System.out.println("*** performCalc");
        System.out.println(calcData.getRisks().toString());

        for (RiskForCalc risk : calcData.getRisks()) {
            risk.setNettoTariff(risk.getNettoTariff() + 0.1);
        }
        return new ResponseEntity(calcData, HttpStatus.OK);
    }
}
