package ATM.demo.mapper;

import ATM.demo.domain.Banknote;
import ATM.demo.domain.dto.BanknoteResponseDto;
import ATM.demo.domain.enumeration.BanknoteType;
import ATM.demo.domain.enumeration.Currency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BanknoteMapper {

    public Banknote toEntity(BanknoteResponseDto banknoteResponseDto) {
        return new Banknote(BanknoteType.valueOf(banknoteResponseDto.getBanknoteType()),
                Currency.valueOf(banknoteResponseDto.getCurrency()),
                banknoteResponseDto.getQuantity());
    }
}
