package org.demo.service;

import org.demo.dao.InsuranceSchemeDao;
import org.demo.entity.InsuranceScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InsuranceSchemeService {
    @Autowired
    InsuranceSchemeDao schemeDao;

    @Transactional(readOnly = true)
    public List<InsuranceScheme> getAllSchemes () {
        return schemeDao.getAll(InsuranceScheme.class);
    }
}
