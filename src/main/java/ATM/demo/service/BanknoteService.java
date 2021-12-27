package ATM.demo.service;

import ATM.demo.domain.Banknote;
import ATM.demo.domain.dto.BanknoteResponseDto;
import ATM.demo.mapper.BanknoteMapper;
import ATM.demo.repository.BanknoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BanknoteService {

    private final BanknoteMapper banknoteMapper;

    private final BanknoteRepository banknoteRepository;

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
}
