package com.think.webtemp.collection.tempweb.mobanwang.helper;

import com.google.gson.Gson;
import com.think.webtemp.collection.net.NetURLDownHelper;
import com.think.webtemp.collection.util.DataFormatHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MobanwangHelper {

    public static void main(String[] args) {
      String url="http://sc.chinaz.com/tag_moban/houtai_2.html";

        List<Map> list =    getPageLinks(url);
        getDownUrlLink(list);

        System.out.println(new Gson().toJson(list));
    }

    public static List<Map> getPageLinks(String url){
        List<Map> list = new LinkedList<Map>();
        try {
            Document document = Jsoup.connect(url).get();
            Element container = document.getElementById("container");
            Elements pics=container.getElementsByClass("picblock");

            if(pics.size()>0){
                for(Element pic:pics){
                    Elements as=   pic.getElementsByTag("a");
                    Element a = as.get(0);
                    String title = a.attr("alt");
                    String link = a.attr("href");
                    Map<String,String>  map = new HashMap<String,String>();
                    map.put("title",title);
                    map.put("link",link);
                    list.add(map);
                }
            }
        } catch (IOException e) {
            System.err.println(url);
            e.printStackTrace();
        }
        return list;
    }


    public static void  getDownUrlLink(List<Map> list){
        //提取页面下载链接
        for(Map<String,String> map:list){
            String linkurl = map.get("link");
            try {
                Document doc =  Jsoup.connect(linkurl).timeout(5000).get();
                Elements bodys = doc.getElementsByClass("downbody");

                if(bodys.size()>0){
                    Element body = bodys.get(0);
                    Elements dians =  body.getElementsByClass("dian");
                    if(dians.size()>1){
                        Element dian = dians.get(1);
                        Elements as =  dian.getElementsByTag("a");
                        if(as.size()>0){
                            Element a = as.get(0);
                            String ziplink =  a.attr("href");
                            map.put("downUrl", ziplink);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println(linkurl);
                e.printStackTrace();
            }

        }
    }

    public static void  down(Map<String,String> map,String saveDir){
        String downLink = map.get("downUrl");
        String endType = DataFormatHelper.urlEndType(downLink);
        String fileName = map.get("title")+endType;
        try {
            NetURLDownHelper.downLoadFromUrl(downLink,fileName,saveDir);
        } catch (IOException e) {
            System.err.println(downLink);
            e.printStackTrace();
        }
    }

}
