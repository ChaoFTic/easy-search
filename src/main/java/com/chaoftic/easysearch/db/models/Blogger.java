package com.chaoftic.easysearch.db.models;

import io.swagger.annotations.ApiModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@ApiModel("Blogger")
@Entity
public class Blogger extends BaseEntity {
    private int id;
    private String gender;
    private Integer age;
    private String astrologicalSign;
    private String industry;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "astrological_sign")
    public String getAstrologicalSign() {
        return astrologicalSign;
    }

    public void setAstrologicalSign(String astrologicalSign) {
        this.astrologicalSign = astrologicalSign;
    }

    @Basic
    @Column(name = "industry")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blogger blogger = (Blogger) o;
        return id == blogger.id &&
                Objects.equals(gender, blogger.gender) &&
                Objects.equals(age, blogger.age) &&
                Objects.equals(astrologicalSign, blogger.astrologicalSign) &&
                Objects.equals(industry, blogger.industry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, age, astrologicalSign, industry);
    }
}
