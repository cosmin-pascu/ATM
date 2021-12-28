package ATM.demo.controller;

import ATM.demo.domain.Banknote;
import ATM.demo.domain.dto.BanknoteResponseDto;
import ATM.demo.domain.enumeration.Currency;
import ATM.demo.service.BanknoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BanknoteController {

    private final BanknoteService banknoteService;

    @PostMapping("/supply")
    public ResponseEntity<Banknote> supplyAtm(@RequestBody BanknoteResponseDto banknoteResponseDto) {
        return ResponseEntity.of(Optional.of(banknoteService.addBanknote(banknoteResponseDto)));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<List<Banknote>> withdrawMoney(@RequestParam Currency currency, @RequestParam Long sum) {
        return ResponseEntity.of(banknoteService.withdrawSum(currency, sum));
    }
}
