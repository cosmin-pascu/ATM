package ATM.demo.repository;

import ATM.demo.domain.Banknote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanknoteRepository extends JpaRepository<Banknote, Long> {
}
