package com.example.bookstorebackend.mapper;

import com.example.bookstorebackend.dto.AccountDTO;
import com.example.bookstorebackend.model.Account;
import org.mapstruct.Mapper;
@Mapper(componentModel = "Spring")
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {
}
