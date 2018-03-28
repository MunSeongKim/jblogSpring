package com.cafe24.jblog.vo;

public class CategoryVO {
    private Long no;
    private String name;
    private String description;
    private Integer postCount;
    private String regDate;
    private String userId;

    public Long getNo() {
	return no;
    }

    public void setNo( Long no ) {
	this.no = no;
    }

    public String getName() {
	return name;
    }

    public void setName( String name ) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription( String description ) {
	this.description = description;
    }

    public Integer getPostCount() {
	return postCount;
    }

    public void setPostCount( Integer postCount ) {
	this.postCount = postCount;
    }

    public String getRegDate() {
	return regDate;
    }

    public void setRegDate( String regDate ) {
	this.regDate = regDate;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId( String userId ) {
	this.userId = userId;
    }

    @Override
    public String toString() {
	return "CategoryVO [no=" + no + ", name=" + name + ", description=" + description + ", postCount=" + postCount
		+ ", regDate=" + regDate + ", userId=" + userId + "]";
    }

}
