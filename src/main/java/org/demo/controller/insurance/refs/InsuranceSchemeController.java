package org.demo.controller.insurance.refs;

import org.demo.dto.refs.SchemeLookupDto;
import org.demo.dto.refs.SchemeRiskLookupDto;
import org.demo.entity.insurance.refs.InsuranceScheme;
import org.demo.service.insurance.InsuranceSchemeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class InsuranceSchemeController {
    @Autowired
    InsuranceSchemeService schemeService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/insurancescheme", method = RequestMethod.GET)
    @ResponseBody
    public List<InsuranceScheme> getAll() {
        System.out.println("********* InsuranceSchemeController.getAll");
        return schemeService.getAll();
    }

    @RequestMapping(value = "/lookup/insurancescheme", method = RequestMethod.GET)
    @ResponseBody
    public List<SchemeLookupDto> getListForLookup() {
        List<InsuranceScheme> schemes = schemeService.getAll();
        return schemes.stream()
                .map(item -> convertToDto(item)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/lookup/insurancescheme/{insuranceschemeid}/risk", method = RequestMethod.GET)
    @ResponseBody
    public List<SchemeRiskLookupDto> getRiskForScheme (@PathVariable("insuranceschemeid") int insuranceSchemeId){
        return schemeService.getRiskForScheme(insuranceSchemeId);
    }

    private SchemeLookupDto convertToDto(InsuranceScheme scheme) {
        return modelMapper.map(scheme, SchemeLookupDto.class);
    }
}
