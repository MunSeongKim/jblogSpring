package com.cafe24.jblog.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVO {
    @NotEmpty
    private String id;
    @NotEmpty
    @Length(min=2, max=5)
    private String name;
    @NotEmpty
    @Pattern(regexp="^[0-9a-zA-z]{4,12}$")
    private String password;
    private String regDate;

    public String getId() {
	return id;
    }

    public void setId( String id ) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName( String name ) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword( String password ) {
	this.password = password;
    }

    public String getRegDate() {
	return regDate;
    }

    public void setRegDate( String regDate ) {
	this.regDate = regDate;
    }

    @Override
    public String toString() {
	return "UserVO [id=" + id + ", name=" + name + ", password=" + password + ", regDate=" + regDate + "]";
    }

}
