package ru.text.nastya.dto.mapper.collection.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class UpdateCollectionStrategyFactory {

    private final Map<String, UpdateCollectionStrategy> strategies;

    @Autowired
    public UpdateCollectionStrategyFactory(List<UpdateCollectionStrategy> strategyList) {
        this.strategies = new HashMap<>();
        for (UpdateCollectionStrategy strategy : strategyList) {
            this.strategies.put(strategy.getName(), strategy);
        }
    }

    public UpdateCollectionStrategy getStrategy(String name) {
        return strategies.get(name);
    }

    public Set<String> getAvailableStrategies() {
        return strategies.keySet();
    }

}
