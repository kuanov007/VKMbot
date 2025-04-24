package uz.azamat;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

public interface Service {
    List<String> musics = new ArrayList<>();

    static List<String> getAllMusicsBySearchingList(String query) {
        List<String> musicsBySearching = new ArrayList<>();
        for (String music : musics) {
            if (music.contains(query)) {
                musicsBySearching.add(music);
            }
        }
        return musicsBySearching;
    }

    static String getAllMusicsBySearchingString(String query, int page) {
        List<String> allMusicsBySearchingList = getAllMusicsBySearchingList(query);
        StringBuilder sb = new StringBuilder();

        sb.append("Natija ( ").append(page).append(" - ").append(page + 10).append(" ) ")
                .append("/").append(allMusicsBySearchingList.size()).append("\n");
        for (int i = 0; i < allMusicsBySearchingList.size(); i++) {
            sb.append(i + 1).append(") ").append(allMusicsBySearchingList.get(i)).append("\n");
        }
        return sb.toString();
    }

    static String getAllMusicsBySearchingString(List<String> allMusicsBySearchingList, String query, int page) {
        int size = getAllMusicsBySearchingList(query).size();
        StringBuilder sb = new StringBuilder();

        sb.append("Natija ( ").append(page).append(" - ").append(page + allMusicsBySearchingList.size()).append(" ) ")
                .append("/").append(size).append("\n");
        for (int i = 0; i < allMusicsBySearchingList.size(); i++) {
            sb.append(i + 1).append(") ").append(allMusicsBySearchingList.get(i)).append("\n");
        }
        return sb.toString();
    }


    static SendMessage sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        return sendMessage;
    }

    static SendMessage sendMessage(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
