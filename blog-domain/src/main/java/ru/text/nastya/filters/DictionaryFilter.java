package ru.text.nastya.filters;

/**
 * Общий фильтр для всех справочников
 */
public abstract class DictionaryFilter implements Filter {

    private String code;

    public DictionaryFilter() {
    }

    public DictionaryFilter(String code) {
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
