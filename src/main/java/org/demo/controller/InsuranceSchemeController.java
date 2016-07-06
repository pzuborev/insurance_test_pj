package org.demo.controller;

import org.demo.dto.refs.SchemeLookupDto;
import org.demo.entity.InsuranceScheme;
import org.demo.service.InsuranceSchemeService;
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
        return schemeService.getAllSchemes();
    }

    @RequestMapping(path = "/refs/scheme", method = RequestMethod.GET)
    @ResponseBody
    public List<SchemeLookupDto> getListForLookup() {
        List<InsuranceScheme> schemes = schemeService.getAllSchemes();
        return schemes.stream()
            .map(post -> convertToDto(post)).collect(Collectors.toList());
    }

    private SchemeLookupDto convertToDto (InsuranceScheme scheme) {
       return  modelMapper.map(scheme, SchemeLookupDto.class);
    }
}
