package com.think.webtemp.collection.tempweb.mobanwang;

import com.think.webtemp.collection.tempweb.mobanwang.helper.MobanwangHelper;
import com.think.webtemp.collection.tempweb.mycodes.helper.MyCodesHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainAPP {

    private static final String URLTEMP = "http://sc.chinaz.com/tag_moban/css3_%s.html";//"http://sc.chinaz.com/tag_moban/houtai_%s.html";
    private static final String SAVEDIR = "C:\\temp\\";
    public static void main(String[] args) {

       // System.out.println(URLTEMP.replaceAll("_%s",""));

        List<String> pages = getUrls(20);
        for(String url:pages){
            List<Map>  list =  MobanwangHelper.getPageLinks(url);
            MobanwangHelper.getDownUrlLink(list);
            for(Map map:list){
                MobanwangHelper.down(map,SAVEDIR);
            }
        }
    }

    public static List<String> getUrls(int max) {
        List<String> list = new LinkedList<String>();
       // list.add("http://sc.chinaz.com/tag_moban/houtai.html");
        for(int i=1;i<max;i++){
            String url ="";
            if(i==1){
               url=    URLTEMP.replaceAll("_%s","");
            }else{
                 url = String.format(URLTEMP,i);
            }
            list.add(url);
        }
        return list;
    }
}
