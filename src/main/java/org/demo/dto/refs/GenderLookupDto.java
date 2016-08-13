package org.demo.dto.refs;

public class GenderLookupDto {
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

    public GenderLookupDto() {
    }

    public GenderLookupDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenderLookupDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
