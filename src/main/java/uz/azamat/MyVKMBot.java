package uz.azamat;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.function.Consumer;


public class MyVKMBot extends TelegramLongPollingBot {
    private final String username;

    public MyVKMBot(DefaultBotOptions options, String botToken, String username) {
        super(options, botToken);
        this.username = username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            runOnClickInlineButtons(update, editMessageText -> {
                try {
                    execute(editMessageText);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            runGetAnyMessageOrOnClickReplyButtons(update, botApiMethodMessage -> {
                try {
                    execute(botApiMethodMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void runGetAnyMessageOrOnClickReplyButtons(Update update, Consumer<BotApiMethodMessage> consumer) {
        Message message = update.getMessage();
        String text = message.getText();
        Long chatId = message.getChatId();


    }

    private static void runOnClickInlineButtons(Update update, Consumer<EditMessageText> consumer) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        System.out.println(messageId);
        Long chatId = callbackQuery.getMessage().getChatId();
        String data = callbackQuery.getData();


    }

    @Override
    public String getBotUsername() {
        return username;
    }

}
