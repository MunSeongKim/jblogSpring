package com.cafe24.jblog.vo;

public class BlogVO {
    private String userId;
    private String title;
    private String imagePath;

    public String getUserId() {
	return userId;
    }

    public void setUserId( String userId ) {
	this.userId = userId;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle( String title ) {
	this.title = title;
    }

    public String getImagePath() {
	return imagePath;
    }

    public void setImagePath( String imagePath ) {
	this.imagePath = imagePath;
    }

    @Override
    public String toString() {
	return "BlogVO [userId=" + userId + ", title=" + title + ", imagePath=" + imagePath + "]";
    }

}
