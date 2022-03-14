import Entity.*;
import service.*;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static CustomerService customerService;
    private static EmployeeService employeeService;
    private static AccountService accountService;
    private static BranchService branchService;
    private static CreditService creditService;

    public static void main(String[] args) {

        customerService = new CustomerService();
        employeeService = new EmployeeService();
        accountService = new AccountService();
        branchService = new BranchService();
        creditService = new CreditService();

        Boolean state = true;

        Scanner scanner = new Scanner(System.in);

        Employee e = new Employee(0 , "1233","sina","tabriz",null,"admin","admin");
        employeeService.save(e);

        System.out.println(" 1 : deposit \n 2 : withdraw \n 3 : Card to Card \n 4 : EmployeeLogin \n 5 : exit");

        while (state){
            System.out.println(" please enter number ");
            int n = getNumber();
            switch (n){
                case 1 :
                    System.out.println("enter your card number");
                    CreditCard card = creditService.findBYCardNumber(getCardNumber());
                    System.out.println("enter amount ");
                    creditService.deposit(card,getNumber());
                    break;
                case 2 :
                    System.out.println("enter your card number");
                    CreditCard card1 = creditService.findBYCardNumber(getCardNumber());
                    System.out.println("enter password");
                    if(card1.getPassword().equals(scanner.nextLine())){
                        System.out.println("enter amount ");
                        creditService.withdraw(card1,getNumber());
                    }else {
                        System.out.println("wrong password");
                    }

                    break;
                case 3 :
                    System.out.println("card to card");
                    break;
                case 4 :
                    System.out.println("enter username");
                    String user = scanner.nextLine();
                    System.out.println("enter password");
                    String pass = scanner.nextLine();
                    Employee employee = employeeService.login(user,pass);
                    employeePage(employee);
                    break;
                case 5 :
                    state = false;
                    break;
                default:
                    System.out.println("not correct number");
                    break;
            }
        }

    }

    private static int getNumber(){
        Scanner scanner = new Scanner(System.in);
            while (true){
                try {
                    return scanner.nextInt();
                }catch (Exception e){
                    System.out.println(" enter number !!!!!!!");
                }
            }
    }

    private static void employeePage(Employee employee){
        boolean state = true;
        System.out.println(" 1 : insert Customer \n 2 : add account \n 3 : insert Bank branch \n 4 : exit ");
        Scanner scanner = new Scanner(System.in);
        while (state){
            System.out.println("-----------------------");
            System.out.println(" pleas enter number ");
            int n = getNumber();
            switch (n){
                case 1 :
                    System.out.println("enter national code ");
                    String national = scanner.nextLine();
                    System.out.println("enter full name ");
                    String name= scanner.nextLine();
                    System.out.println("enter address ");
                    String address = scanner.nextLine();
                    System.out.println("enter branch id");
                    Integer bID = getNumber();
                    customerService.save(new Customer(null,national,name,address,branchService.findById(bID),null));
                    break;
                case 2 :
                    System.out.println("customer national code");
                    String code = scanner.nextLine();
                    Account account = accountService.save(new Account(null,0l , customerService.findByNationalCode(code)));
                    CreditCard card = creditService.save(new CreditCard(null,account,1111_1111_0000l + account.getId(),creatPassword()));
                    System.out.println(card);
                    break;
                case 3 :
                    System.out.println("enter bank branch name :");
                    String bName = scanner.nextLine();
                    System.out.println("enter address");
                    String bAddress = scanner.nextLine();
                    branchService.save(new BankBranch(null,bName,bAddress));
                    break;
                case 4 :
                    state = false;
                    break;
            }
        }

    }

    private static String creatPassword(){
        Random random = new Random();
        return random.nextInt(9000)+1000+"";
    }

    private static Long getCardNumber(){
        Scanner scanner = new Scanner(System.in);
        while (true)
            try {
                String s = scanner.nextLine();
                long res = Long.valueOf(s);
                if (s.length() == 12){
                    return res;
                }else {
                    System.out.println("enter correct number");
                    continue;
                }
        }catch (Exception e ){
                System.out.println("enter number!!!!!");
            }
    }

}
