package com.think.webtemp.collection.tempweb.mycodes.helper;

import com.think.webtemp.collection.net.NetURLDownHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyCodesHelper {


    /**
     * 页面获取详情界面链接集合
     * @param url
     * @return
     */
    public static List<Map> getPageLinks(String url){
        List<Map> list = new LinkedList<Map>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements tables = document.getElementsByClass("Main");
            if(tables.size()>0){
                Elements trs = tables.get(0).getElementsByTag("tr");
                Element tr  = trs.get(3);
                tr.getElementsByClass("page").remove();
                Elements  as = tr.getElementsByTag("a");
                for(Element a:as){
                    String title = a.text();
                    if(title.equals("") || title.contains("演示大图")){
                        continue;
                    }
                    String link = a.attr("href");
                    if(link.toLowerCase().startsWith("http")){
                        Map<String,String>  map = new HashMap<String,String>();
                        map.put("title",title);
                        map.put("link",link);
                        list.add(map);

                     }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void  getDownUrlLink(List<Map> list){
        //提取页面下载链接
        for(Map<String,String> map:list){
            String linkurl = map.get("link");
            try {
                Document doc = Jsoup.connect(linkurl).get();
                Elements b4s = doc.getElementsByClass("b4");
                if (b4s.size() > 0) {
                    Element b4 = b4s.get(0);
                    String ziplink = b4.getElementsByTag("a").get(0).attr("href");
                    map.put("downUrl", ziplink);
                }
            } catch (IOException e) {
                    e.printStackTrace();
             }
        }
    }


      public static void  down(Map<String,String> map,String saveDir){
         String downLink = map.get("downUrl");
            String fileName = map.get("title")+".zip";
          try {
              NetURLDownHelper.downLoadFromUrl(downLink,fileName,saveDir);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }


}
