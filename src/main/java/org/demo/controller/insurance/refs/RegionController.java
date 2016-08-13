package org.demo.controller.insurance.refs;

import org.demo.dto.refs.RegionLookupDto;
import org.demo.entity.insurance.refs.Region;
import org.demo.service.insurance.RegionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class RegionController {
    @Autowired
    RegionService service;

    @Autowired
    protected ModelMapper modelMapper;

    protected RegionLookupDto convertToDto(Object entity) {
        return modelMapper.map(entity, RegionLookupDto.class);
    }

    @RequestMapping(value = "/lookup/region", method = RequestMethod.GET)
    @ResponseBody
    public List<RegionLookupDto> getListForLookup() {
        List<Region> list = service.getAll();
        return list.stream()
                .map(item -> convertToDto(item)).collect(Collectors.toList());
    }
}
