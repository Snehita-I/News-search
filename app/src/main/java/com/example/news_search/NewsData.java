package com.example.news_search;


public class NewsData {

    String title1, url;
    public NewsData(){}

    public NewsData(String title, String description) {
        title1 = title;
        url = description;

    }

    public String getString(){ return title1;}
    public String getUrl()  {return url;}
}
