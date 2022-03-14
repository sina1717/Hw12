package repository;

import Entity.Account;
import Entity.BankBranch;

public class BranchRepository implements BaseRepository<BankBranch> {
    @Override
    public BankBranch findById(Integer id) {
        try (var session = sessionFactory.openSession()){
            return session.find(BankBranch.class,id);
        }
    }
}
