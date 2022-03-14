package repository;

import entity.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminRepositoryTest {
    private AdminRepository adminRepository ;
    private Admin admin1;
    private Admin admin2;
    private Admin admin3;

    @BeforeAll
    public static void beforeAll(){

    }

    @BeforeEach
    void beforeEach(){
        adminRepository = new AdminRepository();
        admin1 = new Admin(-1,"admin","admin_1","1234567891");
        admin2 = new Admin(-1,"admin2","admin_2","1234567892");
        admin3 = new Admin(-1,"admin3","admin_3","1234567893");
    }
    @Test
    void save() {
        // act
        adminRepository.save(admin1);
        //assert
        Admin loaded = adminRepository.findById(admin1.getId());
        assertEquals(admin1,loaded);
    }

    @Test
    void update() {
        // arrange
        adminRepository.save(admin1);
        admin1.setUsername("admin_22");
        //act
        adminRepository.update(admin1);
        //assert
        Admin loaded = adminRepository.findById(admin1.getId());
        assertEquals("admin_22",loaded.getUsername());
    }

    @Test
    void findAll() {
        //arrange
        List<Admin> adminList = Arrays.asList(admin1,admin2,admin3);
        adminList.forEach(o->adminRepository.save(o));
        //act
        List<Admin> adminList1 = adminRepository.findAll();
        //assert
        assertEquals(adminList,adminList1);
    }

    @Test
    void delete() {
        // arrange
        adminRepository.save(admin1);
        //act
        adminRepository.delete(admin1.getId());
        //assert
        assertNull(adminRepository.findById(admin1.getId()));
    }

    @Test
    void findById() {
        //arrange
        adminRepository.save(admin1);
        //act
        Admin loaded = adminRepository.findById(admin1.getId());
        //assert
        assertEquals(admin1,loaded);
    }

    @AfterEach
    public void afterEach(){
        adminRepository.delete(admin1.getId());
//        adminRepository.delete(admin2.getId());
//        adminRepository.delete(admin3.getId());
    }
    @Test
    void login() {
        //arrange
        adminRepository.save(admin1);
        //act
        Admin loaded = adminRepository.login(admin1.getUsername(),admin1.getPassword());
        //assert
        assertEquals(admin1,loaded);
    }
}