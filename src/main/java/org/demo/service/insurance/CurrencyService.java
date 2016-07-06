package org.demo.service.insurance;

import org.demo.dao.insurance.CurrencyDao;
import org.demo.entity.insurance.Currency;
import org.demo.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CurrencyService extends AbstractService<Currency, CurrencyDao> {

}
