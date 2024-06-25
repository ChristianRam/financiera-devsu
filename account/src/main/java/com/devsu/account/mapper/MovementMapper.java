package com.devsu.account.mapper;

import com.devsu.account.model.Account;
import com.devsu.account.model.Movement;
import com.devsu.account.model.dto.MovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.number", target = "accountNumber")
    MovementDto toDto(Movement movement);

    @Mapping(source = "accountId", target = "account", qualifiedByName = "mapAccountIdToAccount")
    Movement toEntity(MovementDto movementDto);


    @Named("mapAccountIdToAccount")
    default Account mapAccountIdToAccount(Long accountId) {
        if (accountId == null) {
            return null;
        }
        Account account = new Account();
        account.setId(accountId);
        return account;
    }

}
