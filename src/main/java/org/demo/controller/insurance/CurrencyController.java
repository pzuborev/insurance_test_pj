package org.demo.controller.insurance;

import org.demo.dto.lookup.CurrencyLookupDto;
import org.demo.entity.insurance.Currency;
import org.demo.service.insurance.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "*")
public class CurrencyController {

    @Autowired
    CurrencyService service;

    @Autowired
    protected ModelMapper modelMapper;

    protected CurrencyLookupDto convertToDto(Object entity) {
        return modelMapper.map(entity, CurrencyLookupDto.class);
    }

    @RequestMapping(path = "lookup/currency", method = RequestMethod.GET)
    @ResponseBody
    public List<CurrencyLookupDto> getListForLookup() {
        List<Currency> list = service.getAll();
        return list.stream()
                .map(item -> convertToDto(item)).collect(Collectors.toList());
    }

}