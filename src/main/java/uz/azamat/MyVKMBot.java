package uz.azamat;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static uz.azamat.Service.*;

public class MyVKMBot extends TelegramLongPollingBot {
    private final String username;

    public MyVKMBot(DefaultBotOptions options, String botToken, String username) {
        super(options, botToken);
        this.username = username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            runOnClickInlineButtons(update);
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

        if (text.equals("/start")) {
            consumer.accept(sendMessage(chatId, "Assalomu aleykum, hurmatli foydalanuvchi.\n Qo'shiq nomnini kiriting:"));
        } else if (!text.startsWith("/")) {
            String allMusicsBySearchingString = getAllMusicsBySearchingString(text);
            List<String> allMusicsBySearchingList = getAllMusicsBySearchingList(text);
            InlineKeyboardMarkup inlineKeyboardMarkup = getInlineKeyboardMarkup(allMusicsBySearchingList);
            consumer.accept(sendMessage(chatId, allMusicsBySearchingString, inlineKeyboardMarkup));
        } else {
            consumer.accept(sendMessage(chatId, "Iltimos qo'shiq nomini kiriting: "));
        }
    }

    private static InlineKeyboardMarkup getInlineKeyboardMarkup(List<String> allMusicsBySearchingList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (int i = 0; i < allMusicsBySearchingList.size(); i++) {
            if (i % 5 == 0) {
                allButtons.add(row);
                row = new ArrayList<>();
                InlineKeyboardButton button = new InlineKeyboardButton(i + 1 + "");
                button.setCallbackData(i + "");
                row.add(button);
                continue;
            }
            InlineKeyboardButton button = new InlineKeyboardButton(i + 1 + "");
            button.setCallbackData(i + "");
            row.add(button);
        }
        allButtons.add(row);
        inlineKeyboardMarkup.setKeyboard(allButtons);
        return inlineKeyboardMarkup;
    }

    private static void runOnClickInlineButtons(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        Long chatId = callbackQuery.getMessage().getChatId();
        String date = callbackQuery.getData();


    }

    @Override
    public String getBotUsername() {
        return username;
    }

}
