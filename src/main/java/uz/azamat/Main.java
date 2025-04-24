package uz.azamat;

import com.github.javafaker.Faker;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ResourceBundle;

import static uz.azamat.Service.musics;

public class Main {

    static {
        Faker faker = new Faker();
        for (int i = 0; i < 1000; i++) {
            String name = faker.artist().name();
            musics.add(name);
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("settings");
        try {
            TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
            DefaultBotOptions options = new DefaultBotOptions();
            bot.registerBot(
                    new MyVKMBot(
                            options,
                            bundle.getString("bot.token"),
                            bundle.getString("bot.username")
                    )
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


    }
}