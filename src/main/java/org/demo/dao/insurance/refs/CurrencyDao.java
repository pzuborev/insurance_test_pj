package org.demo.dao.insurance.refs;

import org.demo.dao.AbstractDao;
import org.demo.entity.insurance.refs.Currency;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyDao extends AbstractDao<Currency> {

    @Override
    public Class<Currency> getEntityType() {
        return Currency.class;
    }
}
