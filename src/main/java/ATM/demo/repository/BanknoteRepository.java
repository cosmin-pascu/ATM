package ATM.demo.repository;

import ATM.demo.domain.Banknote;
import ATM.demo.domain.enumeration.BanknoteType;
import ATM.demo.domain.enumeration.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BanknoteRepository extends JpaRepository<Banknote, Long> {

    Optional<Banknote> findByCurrencyAndType(Currency currency, BanknoteType type);

    List<Banknote> findAllByCurrency(Currency currency);
}
