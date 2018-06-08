package com.think.webtemp.collection.util;

import java.text.DecimalFormat;

public class DataFormatHelper {

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
}
