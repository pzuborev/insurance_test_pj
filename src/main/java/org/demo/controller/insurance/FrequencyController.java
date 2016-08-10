package org.demo.controller.insurance;

import org.demo.dto.lookup.FrequencyLookupDto;
import org.demo.entity.insurance.Frequency;
import org.demo.service.insurance.FrequencyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class FrequencyController {
    @Autowired
    FrequencyService service;

    @Autowired
    protected ModelMapper modelMapper;

    protected FrequencyLookupDto convertToDto(Object entity) {
        return modelMapper.map(entity, FrequencyLookupDto.class);
    }

    @RequestMapping(value = "/lookup/frequency", method = RequestMethod.GET)
    @ResponseBody
    public List<FrequencyLookupDto> getListForLookup() {
        List<Frequency> list = service.getAll();
        return list.stream()
                .map(item -> convertToDto(item)).collect(Collectors.toList());
    }
}
