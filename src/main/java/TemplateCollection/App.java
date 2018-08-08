package TemplateCollection;

import com.google.gson.Gson;
import com.think.webtemp.collection.util.DataFormatHelper;
import gui.ava.html.image.generator.HtmlImageGenerator;
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
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadUrl("http://www.qq.com/");
        imageGenerator.getBufferedImage();
        imageGenerator.saveAsImage("c:/hello-world.png");
       // imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
    }



}
