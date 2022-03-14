package service;

import Entity.CreditCard;
import repository.CreditRepository;

import javax.persistence.criteria.CriteriaBuilder;

public class CreditService {

    private CreditRepository creditRepository = new CreditRepository();
    public AccountService accountService = new AccountService();

    public CreditCard save(CreditCard creditCard){
        return creditRepository.save(creditCard);
    }

    public void update(CreditCard creditCard){
        creditRepository.update(creditCard);
    }

    public void delete(CreditCard creditCard){
        creditRepository.delete(creditCard);
    }

    public CreditCard findById(Integer id){
        return creditRepository.findById(id);
    }

    public CreditCard findBYCardNumber(Long cardNUmber){
        return creditRepository.findByCardNumber(cardNUmber);
    }

    public void deposit(CreditCard card , Integer amount ){
        card.getAccount().setAmount(amount + card.getAccount().getAmount());
        accountService.update(card.getAccount());
        creditRepository.update(card);
    }

    public void withdraw(CreditCard card , Integer amount){
        if(amount < card.getAccount().getAmount()){
            deposit(card,0 - amount);
        }else {
            System.out.println("not many");
        }
    }
}
