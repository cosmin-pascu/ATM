package ATM.demo.mapper;

import ATM.demo.domain.enumeration.Currency;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CurrencyConverter implements AttributeConverter<Currency, String> {

    @Override
    public String convertToDatabaseColumn(Currency currency) {
        if (currency == null) {
            return null;
        }
        return currency.name();
    }

    @Override
    public Currency convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Currency.values())
                .filter(c -> c.name().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
