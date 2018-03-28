package com.cafe24.jblog.vo;

public class CommentVO {
    private Long no;
    private String body;
    private Long postNo;
    private String regDate;

    public Long getNo() {
	return no;
    }

    public void setNo( Long no ) {
	this.no = no;
    }

    public String getBody() {
	return body;
    }

    public void setBody( String body ) {
	this.body = body;
    }

    public Long getPostNo() {
	return postNo;
    }

    public void setPostNo( Long postNo ) {
	this.postNo = postNo;
    }

    public String getRegDate() {
	return regDate;
    }

    public void setRegDate( String regDate ) {
	this.regDate = regDate;
    }

    @Override
    public String toString() {
	return "CommentVO [no=" + no + ", body=" + body + ", postNo=" + postNo + ", regDate=" + regDate + "]";
    }

}
