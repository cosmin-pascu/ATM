package ATM.demo.domain.dto;

import lombok.Data;

@Data
public class BanknoteResponseDto {

    private String currency;

    private String banknoteType;

    private Long quantity;
}
