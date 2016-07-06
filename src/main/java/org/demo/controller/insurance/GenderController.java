package org.demo.controller.insurance;

import org.demo.dto.lookup.GenderLookupDto;
import org.demo.entity.insurance.Gender;
import org.demo.service.insurance.GenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "*")
public class GenderController {
    @Autowired
    private GenderService service;

    @Autowired
    protected ModelMapper modelMapper;

    protected GenderLookupDto convertToDto(Object entity) {
        return modelMapper.map(entity, GenderLookupDto.class);
    }

    @RequestMapping(path = "lookup/gender", method = RequestMethod.GET)
    @ResponseBody
    public List<GenderLookupDto> getListForLookup() {
        List<Gender> list = service.getAll();
        return list.stream()
                .map(item -> convertToDto(item)).collect(Collectors.toList());
    }
}
