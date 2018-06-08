package TemplateCollection;

import com.google.gson.Gson;
import com.think.webtemp.collection.util.DataFormatHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
               //test("http://www.mycodes.net/154/");
        String downUrl = "http://www.mycodes.net/do/job.php?job=down_encode&fid=154&id=9944&rid=9974&i_id=7087&mid=115&field=softurl&ti=0";
              String saveDir = "C:\\temp\\";
        try {
            downLoadFromUrl(downUrl,"a.zip",saveDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test(String  url){
        List<Map> list = new LinkedList<Map>();
        try {
//            Document document = Jsoup.connect(url).get();
//            Elements tables = document.getElementsByClass("Main");
//
//
//            if(tables.size()>0){
//                Elements trs = tables.get(0).getElementsByTag("tr");
//                Element tr  = trs.get(3);
//                tr.getElementsByClass("page").remove();
//                Elements  as = tr.getElementsByTag("a");
//
//                for(Element a:as){
//                    outString("----------------------------------------------");
//                    String title = a.text();
//                    if(title.equals("") || title.contains("演示大图")){
//                        continue;
//                    }
//                    outString(a.text());
//                    outString(a.attr("href"));
//                    String link = a.attr("href");
//                    if(link.toLowerCase().startsWith("http")){
//                        Map<String,String>  map = new HashMap<String,String>();
//                        map.put("title",title);
//                        map.put("link",link);
//                        list.add(map);
//
//                     }
//                }
//            }
            Map<String,String>  maps = new HashMap<String,String>();
            maps.put("title","紫色店铺商家信息后台管理模板");
            maps.put("link","http://www.mycodes.net/154/9944.htm");
            list.add(maps);

            //提取页面下载链接
            for(Map<String,String> map:list){
                String linkurl = map.get("link");
                Document doc = Jsoup.connect(linkurl).get();

                Elements b4s = doc.getElementsByClass("b4");
                if(b4s.size()>0){
                    Element b4 = b4s.get(0);
                   String  ziplink =  b4.getElementsByTag("a").get(0).attr("href");
                    map.put("downUrl",ziplink);
                }
            }


            // out(tables.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void out(Object obj){
        System.out.println(new Gson().toJson(obj));
    }

    public static void outString(String obj){
        System.out.println(obj);
    }



    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        int fileSize =  conn.getContentLength();

        outString(DataFormatHelper.FormetFileSize(fileSize));
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }


        System.out.println("info:"+url+" download success");

    }



    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


}
