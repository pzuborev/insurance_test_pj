package org.demo.dto.refs;

public class ForIndividualTypeDto {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForIndividualTypeDto() {
    }

    public ForIndividualTypeDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ForIndividualTypeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
