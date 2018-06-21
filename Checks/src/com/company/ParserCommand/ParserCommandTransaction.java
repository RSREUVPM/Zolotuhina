package com.company.ParserCommand;

import java.util.Arrays;

public class ParserCommandTransaction {
    String command;
    String[] array;

    public ParserCommandTransaction(String command) {
        this.command = command;
    }

    public String[] parser () {

        array = command.split(";");
        array = Arrays.copyOf(array,10);
        array[9] = "false";

        return array;
    }


}
