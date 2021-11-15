package ru.online.shop.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "draft")
public class DraftCurrencyConverter {
    public static final String DEFAULT_CURRENCY = "RUBLE";

    private Map<String, String> currencyMap;

    public String convertRawCurrency(String currency) {
        return currencyMap.getOrDefault(currency, DEFAULT_CURRENCY);
    }


}
