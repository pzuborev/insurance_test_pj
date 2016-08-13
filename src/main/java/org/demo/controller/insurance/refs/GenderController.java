package org.demo.controller.insurance.refs;

import org.demo.dto.refs.GenderLookupDto;
import org.demo.entity.insurance.refs.Gender;
import org.demo.service.insurance.GenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class GenderController {
    @Autowired
    private GenderService service;

    @Autowired
    protected ModelMapper modelMapper;

    protected GenderLookupDto convertToDto(Object entity) {
        return modelMapper.map(entity, GenderLookupDto.class);
    }

    @RequestMapping(value = "/lookup/gender", method = RequestMethod.GET)
    @ResponseBody
    public List<GenderLookupDto> getListForLookup() {
        List<Gender> list = service.getAll();
        return list.stream()
                .map(item -> convertToDto(item)).collect(Collectors.toList());
    }
}
