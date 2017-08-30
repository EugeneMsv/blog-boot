package ru.text.nastya.filters;

/**
 * Фильтр для поиска тегов
 */
public class TagFilter implements Filter {

    private String code;

    public TagFilter() {
    }

    public TagFilter(String code) {
        this.code = code;
    }

    /**
     * Код
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
