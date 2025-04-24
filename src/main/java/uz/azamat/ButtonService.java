package uz.azamat;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonService {
    public static InlineKeyboardMarkup getInlineButtonsAfterSearched(String query, int page) {
        List<String> allMusicsBySearchingList = Service.getAllMusicsBySearchingList(query);
        List<List<String>> musicGroups = getMusicGroups(allMusicsBySearchingList);
        List<String> currentMusics = musicGroups.get(page);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (int i = 0; i < currentMusics.size(); i++) {
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

    public static List<List<String>> getMusicGroups(List<String> musics) {
        List<List<String>> musicGroups = new ArrayList<>();
        int size = musics.size();
        for (int i = 0; i < size; i += 10) {
            int end = Math.min(i + 10, size);
            List<String> tempMusics = new ArrayList<>();
            for (int j = 0; j < end; j++) {
                tempMusics.add(musics.get(i));
            }
            musicGroups.add(tempMusics);
        }
        return musicGroups;
    }
}
