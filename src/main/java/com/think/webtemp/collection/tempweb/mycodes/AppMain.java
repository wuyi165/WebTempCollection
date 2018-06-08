package com.think.webtemp.collection.tempweb.mycodes;

import com.google.gson.Gson;
import com.think.webtemp.collection.tempweb.mycodes.helper.MyCodesHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AppMain {

    private static final String URLTEMP = "http://www.mycodes.net/154/%s.htm";
    private static final String SAVEDIR = "C:\\temp\\";
    public static void main(String[] args) {
        List<String> pages = getUrls();
        for(String url:pages){
            List<Map>  list =  MyCodesHelper.getPageLinks(url);
            MyCodesHelper.getDownUrlLink(list);
//            System.out.println(new Gson().toJson(list));
            for(Map map:list){
                MyCodesHelper.down(map,SAVEDIR);
            }
        }

       // MyCodesHelper.getDownUrlLink();

    }


    public static List<String> getUrls() {
        List<String> list = new LinkedList<String>();
        list.add("http://www.mycodes.net/154/");
        for(int i=1;i<21;i++){
            String url = String.format(URLTEMP,i);
            list.add(url);
        }
        return list;
    }
}
