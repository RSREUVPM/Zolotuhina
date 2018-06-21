package com.company.TelegramBot;

import com.company.CreatCSV.CreatCsvFromCheck;
import com.company.CreatCSV.CreatCsvFromCommand;
import com.company.ParserCommand.ParserCommandTransaction;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.File;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Bot extends AbilityBot {

    protected Bot (String botToken, String botUsername, DefaultBotOptions botOptions) {
        super (botToken, botUsername, botOptions);
    }

    public int creatorId() {
        return 0;
    }

    public void onUpdateReceived(Update update) {

//        if (update.hasMessage() && update.getMessage().hasText()) {
//            String received_massage_text = update.getMessage().getText();
//            long chat_id = update.getMessage().getChatId();
//
//            if (received_massage_text.equals("/start")) {
//
//                SendMessage send_message = new SendMessage();
//                send_message.setChatId(chat_id).setText("lol");
//
//                try {
//                    sendMessage(send_message);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//                SendMessage send_message = new SendMessage();
//                send_message.setChatId(chat_id).setText("херь какая-то!");
//
//                try {
//                    sendMessage(send_message);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        if (update.hasMessage() &&  update.getMessage().hasDocument()) {
            String fileId = update.getMessage().getDocument().getFileId();
            long chatId = update.getMessage().getChatId();

            DownloadFileFromURL downloadFileFromURL = new DownloadFileFromURL();
            try {

                GetFile f = new GetFile();
                f.setFileId(fileId);
                File f2 = getFile(f);
                String filePath = f2.getFilePath();
                System.out.println(filePath);

                java.io.File filejson = new java.io.File("json/check.json");

                filejson = downloadFileFromURL.downloadFile(filePath, "json/check2.json");

                SendMessage send_message = new SendMessage();
                send_message.setChatId(chatId).setText("файл json получен");
                sendMessage(send_message);

                CreatCsvFromCheck creatCsvFromCheck = new CreatCsvFromCheck();
                creatCsvFromCheck.creatMyCsv(filejson,"data/dataChechs.csv");

                send_message.setChatId(chatId).setText("файл csv записан");
                sendMessage(send_message);


            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String reseivedMessageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            try {

                ParserCommandTransaction parserCommandTransaction = new ParserCommandTransaction(reseivedMessageText);

                CreatCsvFromCommand creatCsvFromCommand = new CreatCsvFromCommand(parserCommandTransaction.parser());

                creatCsvFromCommand.creatMyCsv("data/dataManual.csv");

                SendMessage send_message = new SendMessage();
                send_message.setChatId(chatId).setText("информация о транзакции получена");
                sendMessage(send_message);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }




    public String getBotUsername () {
        return "ReadFinance_bot";
    }

    public String getBotToken() {
        return "554417311:AAFqPD8tmyJ79RpUJmwSrafLn63CBPt_f3I";
    }
}
