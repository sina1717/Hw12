package service;

import Entity.BankBranch;
import repository.BranchRepository;

public class BranchService {

    private BranchRepository branchRepository = new BranchRepository();

    public BankBranch save(BankBranch branch){
        return branchRepository.save(branch);
    }

    public void update(BankBranch branch){
        branchRepository.update(branch);
    }

    public void delete(BankBranch branch){
        branchRepository.delete(branch);
    }

    public BankBranch findById(Integer id){
        return branchRepository.findById(id);
    }
}
