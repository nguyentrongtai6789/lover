package com.example.loverbackend.mapper;

import com.example.loverbackend.dto.AccountDTO;
import com.example.loverbackend.model.Account;
import org.mapstruct.Mapper;
@Mapper(componentModel = "Spring")
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {
}
