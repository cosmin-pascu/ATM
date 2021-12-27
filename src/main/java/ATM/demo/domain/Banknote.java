package ATM.demo.domain;

import ATM.demo.domain.enumeration.BanknoteType;
import ATM.demo.domain.enumeration.Currency;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 *  Represents the banknotes that can be found in the ATM
 */

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "banknote")
public class Banknote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BanknoteType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @NotNull
    @Column(name = "quantity")
    private Long quantity;

    public Banknote(BanknoteType type, Currency currency, Long quantity) {
        this.type = type;
        this.currency = currency;
        this.quantity = quantity;
    }
}
