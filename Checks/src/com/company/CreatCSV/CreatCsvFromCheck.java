package com.company.CreatCSV;
import java.io.File;
import java.io.FileReader;
import java.lang.Exception;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;
import com.opencsv.CSVWriter;
//import sun.java2d.pipe.SpanShapeRenderer;

import java.lang.String;
import java.util.Date;


public class CreatCsvFromCheck {
    //параметры класса
    //public  String inFile = "json/31_05_2018_02_30_141518942659.json";

    public void  creatMyCsv (File fromFile, String fileCsvName) {

        JSONParser parser = new JSONParser();
        String[] check = new String[10];

        try {

            //String csv = "data/data.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(fileCsvName, true),';');

            JSONObject object = (JSONObject) parser.parse(new FileReader(fromFile)); //весь json в объект(асс массив)
            JSONArray items = (JSONArray) object.get("items"); //сами товары

            Long date = (Long) object.get("dateTime");
            date = date*1000;
            Date d = new Date (date);

            SimpleDateFormat d2 = new SimpleDateFormat("dd.MM.yyyy");
            String dateEnd = d2.format(d);

            String shop = (String) object.get("user");



            check[0] = dateEnd;
            check[5]= check[6]=check[7]= "0";
            check[9] = "true";
            check[8] = shop;


            for (int i=0; i<items.size();i++){
                JSONObject item = (JSONObject) items.get(i);
                check[1] = (String) item.get("name");
                check[2] =  Float.toString( conv((Long) item.get("price")));
                check[3] =  Double.toString( (Double) item.get("quantity"));
                check[4] = Float.toString( conv((Long) item.get("sum")));
                writer.writeNext(check);
            }

            writer.close();

        } catch (Exception ex) {
            Logger.getLogger(CreatCsvFromCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static float conv (Long l){
        String str = Long.toString(l);
        int length = str.length();
        String n = str.substring(0,length-2);
        String k = str.substring(length-2);
        String result = n + "." + k;
        Float f = Float.valueOf(result);
        return f;
    }



}
