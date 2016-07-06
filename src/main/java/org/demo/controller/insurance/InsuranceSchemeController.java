package org.demo.controller.insurance;

import org.demo.dto.lookup.SchemeLookupDto;
import org.demo.entity.insurance.InsuranceScheme;
import org.demo.service.insurance.InsuranceSchemeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "*")
public class InsuranceSchemeController {
    @Autowired
    InsuranceSchemeService schemeService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(path = "/insurancescheme", method = RequestMethod.GET)
    @ResponseBody
    public List<InsuranceScheme> getAll() {
        System.out.println("********* InsuranceSchemeController.getAll");
        return schemeService.getAll();
    }

    @RequestMapping(path = "lookup/insurancescheme", method = RequestMethod.GET)
    @ResponseBody
    public List<SchemeLookupDto> getListForLookup() {
        List<InsuranceScheme> schemes = schemeService.getAll();
        return schemes.stream()
                .map(item -> convertToDto(item)).collect(Collectors.toList());
    }

    private SchemeLookupDto convertToDto(InsuranceScheme scheme) {
        return modelMapper.map(scheme, SchemeLookupDto.class);
    }
}
