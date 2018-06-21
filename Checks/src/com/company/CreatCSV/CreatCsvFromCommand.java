package com.company.CreatCSV;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreatCsvFromCommand {
    String[] manual;

    public CreatCsvFromCommand(String[] manual) {
        this.manual = manual;
    }

    public void  creatMyCsv (String fileCsvName) {


        try {
            CSVWriter writer = new CSVWriter(new FileWriter(fileCsvName, true), ';');
            writer.writeNext(manual);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    }
