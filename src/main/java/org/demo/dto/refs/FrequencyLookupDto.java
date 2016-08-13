package org.demo.dto.refs;

public class FrequencyLookupDto {
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

    public FrequencyLookupDto() {
    }

    public FrequencyLookupDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "FrequencyLookupDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
