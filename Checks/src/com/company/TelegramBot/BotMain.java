package com.company.TelegramBot;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;

public class BotMain  {
    private static String BOT_NAME = "ReadFinance_bot";
    private static String BOT_TOKEN = "554417311:AAFqPD8tmyJ79RpUJmwSrafLn63CBPt_f3I";

    private static String PROXY_HOST = "66.70.255.195";
    private static Integer PROXY_PORT = 3128;


    public static void main (String[] args) {

        try {
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();

            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

            HttpHost httpHost = new HttpHost(PROXY_HOST, PROXY_PORT);

            RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(false).build();
            botOptions.setRequestConfig(requestConfig);
            botOptions.setHttpProxy(httpHost);

            Bot bot = new Bot(BOT_TOKEN, BOT_NAME, botOptions);

            botsApi.registerBot(bot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        System.out.println("CheckBot successfully started");
    }
}
