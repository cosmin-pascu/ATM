package ATM.demo.controller;

import ATM.demo.domain.Banknote;
import ATM.demo.domain.dto.BanknoteResponseDto;
import ATM.demo.service.BanknoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BanknoteController {

    private final BanknoteService banknoteService;

    @PostMapping("/supply")
    public @ResponseBody Banknote supplyAtm(@RequestBody BanknoteResponseDto banknoteResponseDto) {
        return banknoteService.addBanknote(banknoteResponseDto);
    }
}
