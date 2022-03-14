package repository;

import Entity.Account;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.Singleton;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    private AccountRepository accountRepository;
    private Account a1;



    @BeforeAll
    public static void beforeAll(){
        Singleton.getInstance();


    }

    @BeforeEach
    public void beforeEach(){
        accountRepository = new AccountRepository();
        a1 = new Account(null , 100l,null);

    }

    @Test
    void save() {
        // act
        accountRepository.save(a1);
        //assert
        Account loaded = accountRepository.findById(a1.getId());
        assertEquals(a1,loaded);
    }

    @Test
    void update() {
        //arrange
        accountRepository.save(a1);
        a1.setAmount(2000l);
        //act
        accountRepository.update(a1);
        //assert
        Account loaded = accountRepository.findById(a1.getId());
        assertEquals(2000,loaded.getAmount());
    }

    @Test
    void delete() {
        // arrange
        accountRepository.save(a1);
        //act
        accountRepository.delete(a1);
        //assert
        assertNull(accountRepository.findById(a1.getId()));
    }

    @Test
    void findById() {
        //arrange
        accountRepository.save(a1);
        //act
        Account loaded = accountRepository.findById(a1.getId());
        //assert
        assertEquals(a1,loaded);
    }
}