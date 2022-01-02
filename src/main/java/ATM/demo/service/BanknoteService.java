package ATM.demo.service;

import ATM.demo.domain.Banknote;
import ATM.demo.domain.dto.BanknoteResponseDto;
import ATM.demo.domain.enumeration.Currency;
import ATM.demo.mapper.BanknoteMapper;
import ATM.demo.repository.BanknoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BanknoteService {

    private final BanknoteMapper banknoteMapper;

    private final BanknoteRepository banknoteRepository;

    private final NotificationService notificationService;

    public Banknote addBanknote(BanknoteResponseDto banknoteResponseDto) {
        return banknoteRepository.save(createOrUpdateBanknote(banknoteResponseDto));
    }

    public Banknote createOrUpdateBanknote(BanknoteResponseDto banknoteResponseDto) {
        Banknote banknoteToSave = banknoteMapper.toEntity(banknoteResponseDto);

        return banknoteRepository.findByCurrencyAndType(banknoteToSave.getCurrency(), banknoteToSave.getType())
                .map(existingBanknote -> {
                    existingBanknote.setQuantity(existingBanknote.getQuantity() + banknoteToSave.getQuantity());
                    return existingBanknote;
                }).orElseGet(() -> banknoteToSave);
    }

    public Banknote updateBanknoteQuantity(Banknote banknote) {
        return banknoteRepository.findByCurrencyAndType(banknote.getCurrency(), banknote.getType())
                .map(existingBanknote -> {
                    existingBanknote.setQuantity(banknote.getQuantity());
                    return existingBanknote;
                }).orElseGet(() -> null);
    }

    public Optional<List<Banknote>> withdrawSum(Currency currency, Long sum) {
        List<Banknote> withdrawnBanknotes = new ArrayList<>();
        List<Banknote> banknotesToUpdate = new ArrayList<>();

        notificationService.notifyUserIfNecessarily(currency, sum);

        for (Banknote banknote : getDescendingListOfBanknotes(currency)) {
            Banknote withdrawnBanknote = new Banknote(banknote.getType(), banknote.getCurrency(), 0L);

            while (sum > 0 && sum >= banknote.getType().getValue() && banknote.getQuantity() > 0) {
                sum -= banknote.getType().getValue();
                banknote.decrementQuantity();
                withdrawnBanknote.incrementQuantity();
            }

            if (withdrawnBanknote.getQuantity() != 0) {
                withdrawnBanknotes.add(withdrawnBanknote);
                banknotesToUpdate.add(banknote);
            }
        }

        if (sum == 0) {
            updateBanknotes(banknotesToUpdate);
            notificationService.sendNotificationsIfNecessarily(currency);
            return Optional.of(withdrawnBanknotes);
        }

        return Optional.empty();
    }

    public List<Banknote> getDescendingListOfBanknotes(Currency currency) {
        List<Banknote> sortedBanknotes = banknoteRepository.findAllByCurrency(currency).stream()
                .sorted(Comparator.comparing(banknote -> banknote.getType().getValue())).collect(Collectors.toList());

        Collections.reverse(sortedBanknotes);

        return sortedBanknotes;
    }

    public void updateBanknotes(List<Banknote> banknotes) {
        banknotes.forEach(banknote -> banknoteRepository.save(updateBanknoteQuantity(banknote)));
    }

    public List<Banknote> getAllBanknotes() {
        return banknoteRepository.findAll();
    }
}
