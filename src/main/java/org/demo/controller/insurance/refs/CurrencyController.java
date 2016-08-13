package org.demo.controller.insurance.refs;

import org.demo.dto.refs.CurrencyLookupDto;
import org.demo.entity.insurance.refs.Currency;
import org.demo.service.insurance.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class CurrencyController {

    @Autowired
    CurrencyService service;

    @Autowired
    protected ModelMapper modelMapper;

    protected CurrencyLookupDto convertToDto(Object entity) {
        return modelMapper.map(entity, CurrencyLookupDto.class);
    }

    @RequestMapping(value = "/lookup/currency", method = RequestMethod.GET)
    @ResponseBody
    public List<CurrencyLookupDto> getListForLookup() {
        List<Currency> list = service.getAll();
        return list.stream()
                .map(item -> convertToDto(item)).collect(Collectors.toList());
    }

}
