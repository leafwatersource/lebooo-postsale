package com.api.lebooo.master.service;

import com.api.lebooo.model.Account;
import com.api.lebooo.model.AccountExample;
import me.fishlord.common.result.ResultEntity;

import java.util.List;

/**
 * @Date 2022-05-31 15:58
 */
public interface AccountService {

    List<Account> accountList(AccountExample accountExample);

    ResultEntity loginAccount(String account, String password);

    Account getAccount(Long accountId);

    ResultEntity saveAccount(Account newAccount, Account acc);
}
