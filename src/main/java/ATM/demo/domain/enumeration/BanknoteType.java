package ATM.demo.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  Represents types of banknotes that can be found
 */

@Getter
@AllArgsConstructor
public enum BanknoteType {
    ONE(1),
    FIVE(5),
    TEN(10),
    FIFTY(50),
    ONE_HUNDRED(100);

    final int value;
}
