package repository;

import Entity.Account;

public class AccountRepository implements BaseRepository<Account> {
    @Override
    public Account findById(Integer id) {
        try (var session = sessionFactory.openSession()){
            return session.find(Account.class,id);
        }
    }
}
