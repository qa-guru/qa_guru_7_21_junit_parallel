package guru.qa.domain;

public enum MenuItem {

    SEARCH("Поиск"),
    IMAGES("Картинки"),
    VIDEO("Видео");

    private String desc;

    MenuItem(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
