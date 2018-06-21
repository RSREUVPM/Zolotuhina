package com.company.TelegramBot;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.print.DocFlavor;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadFileFromURL {

    public DownloadFileFromURL() {
    }

    //    public static void main (String[] args) {
////        String url = "https://api.telegram.org/file/bot554417311:AAFqPD8tmyJ79RpUJmwSrafLn63CBPt_f3I/documents/file_2.json";
//
//        try {
//
//            String fileName = "json/check4.json";
//            java.io.File myFile = new java.io.File(fileName);
//
//            CloseableHttpClient httpclient = HttpClients.createDefault();
//            HttpHost target = new HttpHost("api.telegram.org", 443, "https");
//            HttpHost proxy = new HttpHost("66.70.255.195", 3128, "http");
//            RequestConfig config = RequestConfig.custom()
//                    .setProxy(proxy)
//                    .build();
//            HttpGet request = new HttpGet("/file/bot554417311:AAFqPD8tmyJ79RpUJmwSrafLn63CBPt_f3I/documents/file_13.json");
//            request.setConfig(config);
//
//            System.out.println("Executing request " + request.getRequestLine() + " to " + target + " via " + proxy);
//
//            CloseableHttpResponse response = httpclient.execute(target, request);
//            HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    try {
//                        FileOutputStream outstream = new FileOutputStream(myFile);
//                        entity.writeTo(outstream);
//                        outstream.close();
//                    } finally {
//                        response.close();
//                    }
//                }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


    public File downloadFile (String filePath, String fileName) {

        java.io.File myFile = new java.io.File(fileName);
        try {
            String url = "/file/bot554417311:AAFqPD8tmyJ79RpUJmwSrafLn63CBPt_f3I/" + filePath;



            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpHost target = new HttpHost("api.telegram.org", 443, "https");
            HttpHost proxy = new HttpHost("66.70.255.195", 3128, "http");
            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            HttpGet request = new HttpGet(url);
            request.setConfig(config);

            System.out.println("Executing request " + request.getRequestLine() + " to " + target + " via " + proxy);

            CloseableHttpResponse response = httpclient.execute(target, request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try {
                    FileOutputStream outstream = new FileOutputStream(myFile);
                    entity.writeTo(outstream);
                    outstream.close();
                } finally {
                    response.close();
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return myFile;

    }

}
