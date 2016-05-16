package com.main.androiddemo.bean;

/**
 * Created by justme on 15/8/24.
 */
public class IntroduceBean {

    private String content;

    private String title;

    /**
     * @return The content
     */
    public String getContent() {
        if (null != content) {
            return content;
        }
        return "";
    }

    /**
     * @param content The content
     */
    public void setContent(String content) {
        if (null != content) {
            this.content = content;
        }
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }



}
