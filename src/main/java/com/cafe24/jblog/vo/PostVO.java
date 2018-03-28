package com.cafe24.jblog.vo;

public class PostVO {
    private Long no;
    private Long categoryNo;
    private String title;
    private String body;
    private String regDate;

    public Long getNo() {
	return no;
    }

    public void setNo( Long no ) {
	this.no = no;
    }

    public Long getCategoryNo() {
	return categoryNo;
    }

    public void setCategoryNo( Long categoryNo ) {
	this.categoryNo = categoryNo;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle( String title ) {
	this.title = title;
    }

    public String getBody() {
	return body;
    }

    public void setBody( String body ) {
	this.body = body;
    }

    public String getRegDate() {
	return regDate;
    }

    public void setRegDate( String regDate ) {
	this.regDate = regDate;
    }

    @Override
    public String toString() {
	return "PostVO [no=" + no + ", categoryNo=" + categoryNo + ", title=" + title + ", body=" + body + ", regDate="
		+ regDate + "]";
    }

}
