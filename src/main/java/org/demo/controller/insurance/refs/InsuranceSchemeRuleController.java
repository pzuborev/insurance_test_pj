package org.demo.controller.insurance.refs;

import org.demo.dto.refs.SchemeRuleLookupDto;
import org.demo.entity.insurance.refs.InsuranceSchemeRule;
import org.demo.service.insurance.InsuranceSchemeRuleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class InsuranceSchemeRuleController {
    @Autowired
    private InsuranceSchemeRuleService service;

    @Autowired
    protected ModelMapper modelMapper;

    protected SchemeRuleLookupDto convertToDto(Object entity) {
        return modelMapper.map(entity, SchemeRuleLookupDto.class);
    }

    @RequestMapping(value = "/lookup/insuranceschemerule", method = RequestMethod.GET)
    @ResponseBody
    public List<SchemeRuleLookupDto> getListForLookup() {
        List<InsuranceSchemeRule> list = service.getAll();
        return list.stream()
                .map(item -> convertToDto(item)).collect(Collectors.toList());
    }
}
