package org.demo.dto.refs;

public class CurrencyLookupDto {
    String code;
    String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrencyLookupDto() {
    }

    public CurrencyLookupDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CurrencyLookupDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
