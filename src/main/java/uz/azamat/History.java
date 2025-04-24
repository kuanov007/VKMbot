package uz.azamat;

import java.util.Objects;

public class History {
    private Long chatId;
    private String query;
    private Integer date;
    private int page;

    public History() {
    }

    public History(Long chatId, String query, Integer date, int page) {
        this.chatId = chatId;
        this.query = query;
        this.date = date;
        this.page = page;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof History history)) return false;
        return getPage() == history.getPage() && Objects.equals(getChatId(), history.getChatId()) && Objects.equals(getQuery(), history.getQuery()) && Objects.equals(getDate(), history.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChatId(), getQuery(), getDate(), getPage());
    }

    @Override
    public String toString() {
        return "History{" +
               "chatId=" + chatId +
               ", query='" + query + '\'' +
               ", date=" + date +
               ", page=" + page +
               '}';
    }
}
