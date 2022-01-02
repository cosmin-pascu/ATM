package ATM.demo.controller;

import ATM.demo.domain.Banknote;
import ATM.demo.domain.dto.BanknoteResponseDto;
import ATM.demo.domain.enumeration.Currency;
import ATM.demo.service.BanknoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BanknoteController {

    private final BanknoteService banknoteService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/supply")
    public ResponseEntity<Banknote> supplyAtm(@RequestBody BanknoteResponseDto banknoteResponseDto) {
        return ResponseEntity.of(Optional.of(banknoteService.addBanknote(banknoteResponseDto)));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/withdraw")
    public ResponseEntity<List<Banknote>> withdrawMoney(@RequestParam Currency currency, @RequestParam Long sum) {
        return ResponseEntity.of(banknoteService.withdrawSum(currency, sum));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all-banknotes")
    public ResponseEntity<List<Banknote>> getAllPresentBanknotes() {
        return ResponseEntity.of(Optional.of(banknoteService.getAllBanknotes()));
    }
}
