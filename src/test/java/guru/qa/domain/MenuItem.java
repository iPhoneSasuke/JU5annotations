package guru.qa.domain;

public enum MenuItem {
    COMPANY("Компания"), CATALOGUE("Каталог"), PRICE("Цены"),
    PUBLICATIONS("Публикации"), SHOW("Шоу-Рум"), CONTACTS("Контакты");
    public final String rusName;

    MenuItem(String rusName) {
        this.rusName = rusName;
    }
}
