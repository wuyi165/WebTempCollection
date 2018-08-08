package com.think.webtemp.collection.util;

import java.text.DecimalFormat;

public class DataFormatHelper {


    /**
     * 文件大小格式转换
     * @param file
     * @return
     */
    public  static  String FormetFileSize(int  file) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (file < 1024) {
            fileSizeString = df.format((double) file) + "byte";
        } else if (file < 1048576) {
            fileSizeString = df.format((double) file / 1024) + "KB";
        } else if (file < 1073741824) {
            fileSizeString = df.format((double) file / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) file / 1073741824) + "GB";
        }
        return fileSizeString;
    }


    /**
     * 判断url后缀
     * @param url
     * @return  ".zip"
     */
    public  static  String urlEndType(String   url) {
        if(url==null){
            return null;
        }
       int index= url.lastIndexOf(".");
        if(index>0){
            return url.substring(index);
        }
        return "zip";
    }


    public static void main(String[] args) {
        String url="http://fjdx.sc.chinaz.com/Files/DownLoad/moban/201605/moban1265.rar";
        String type=  urlEndType(url);
        System.out.println(type);
    }

}
