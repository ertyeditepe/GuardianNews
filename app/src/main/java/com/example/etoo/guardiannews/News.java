package com.example.etoo.guardiannews;

public class News {
    private String mSectionName, mTime, mUrl, mTitle;

    public News (String sectionName, String time, String url, String title) {

        mSectionName = sectionName;
        mTime = time;
        mUrl = url;
        mTitle = title;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getTime() {
        return mTime;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }
}
