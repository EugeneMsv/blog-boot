package ru.text.nastya.dto.mapper.collection;

import ru.text.nastya.dto.mapper.collection.strategy.RemoveOldItemsUpdateCollectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class ListWrapper<D> {

    private String updateStrategyType = RemoveOldItemsUpdateCollectionStrategy.NAME;

    private List<D> values;

    public ListWrapper() {
    }

    public ListWrapper(List<D> values) {
        this.values = values;
    }


    public String getUpdateStrategyType() {
        return updateStrategyType;
    }

    public void setUpdateStrategyType(String updateStrategyType) {
        this.updateStrategyType = updateStrategyType;
    }

    public List<D> getValues() {
        if (values == null) {
            values = new ArrayList<>();
        }
        return values;
    }

    public void setValues(List<D> values) {
        this.values = values;
    }
}
