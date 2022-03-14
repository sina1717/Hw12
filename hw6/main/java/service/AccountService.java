package service;

import Entity.Account;
import repository.AccountRepository;

public class AccountService {

    private AccountRepository accountRepository =new AccountRepository();
    public Account save(Account account){
        return accountRepository.save(account);
    }

    public void update(Account account){
        accountRepository.update(account);
    }

    public void delete(Account account){
        accountRepository.delete(account);
    }

    public Account findById(Integer id){
        return accountRepository.findById(id);
    }
}
