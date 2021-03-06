package runner.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runner.AppRunner;
import runner.enums.AccountType;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppRunner.class)
@TestPropertySource(properties = {"DB_USER=newuser",
        "DB_PASS=password",
        "DB_URL=jdbc:mysql://localhost:3306/moneymanagement"})
public class CustomerEntityTest {

    Account account1;
    Account account2;
    Account account3;
    Set<Account> testAccounts;
    Login login;
    Customer customer;
    Transaction transaction;
    Set<Account> transactionAccount;
    Transaction withdrawalTransaction;
    Transaction depositTransaction;
    ArrayList<Transaction> myTransactionList;
    Address address;
    @Before
    public void setup(){
        account1 = new Account(1L,"12345", AccountType.CHECKING,100.00,"abcdefg", new ArrayList<Transaction>());
        account2 = new Account(2L,"54321", AccountType.SAVINGS,0.00,"gfedcba", new ArrayList<Transaction>());
        account3 =  new Account(2L,"56789", AccountType.SAVINGS,100.00,"qwerty", new ArrayList<Transaction>());
        testAccounts = new HashSet<Account>();
        testAccounts.add(account1);
        testAccounts.add(account2);
        login = new Login(1L,"user","password",customer); //customer would be null here due to order of code;
        address = new Address(1L,"Address Line 1", "Address Line 2", "Bear","DE","19701");
        customer = new Customer(1L,"John","Doe",address,login,testAccounts);
    }

    @Test
    public void testClassSignatureAnnotations() {
        Assert.assertTrue(Customer.class.isAnnotationPresent(Entity.class));
    }
    @Test
    public void testCreateJson() throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
     //   Customer expectedCustomer = new Customer( 1L, "Radha" , "Ramnik","Patel","234324");
        String json = objectMapper.writeValueAsString(customer);
        Customer actualCustomer =objectMapper.readValue(json, Customer.class);
        Assert.assertEquals(customer.getId(), actualCustomer.getId());

    }

//    @Before
//    public void startUp()
//    {
//        user= new UserBuilder().createUser();
//    }
//
//    @Test
//    public void setAndGetIdTest()
//    {
//        //Given
//        Long expected =1L;
//        //When
//        user.setId(expected);
//        //Then
//        Long actual = user.getId();
//        Assert.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void setAndGetFirstNameTest()
//    {
//        //Given
//        String expected = "FirstName";
//        //When
//        user.setFirstName(expected);
//        //Then
//        String actual = user.getFirstName();
//        Assert.assertEquals(expected,actual);
//    }
//
//
//    @Test
//    public void setAndGetLastNameTest()
//    {
//        //Given
//        String expected = "LastName";
//        //When
//        user.setLastName(expected);
//        //Then
//        String actual = user.getLastName();
//        Assert.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void setAndGetMiddleNameTest()
//    {
//        //Given
//        String expected = "MiddleName";
//        //When
//        user.setMiddleName(expected);
//        //Then
//        String actual = user.getMiddleName();
//        Assert.assertEquals(expected,actual);
//    }
//    @Test
//    public void setAndGetDOBTest()
//    {
//        //Given
//        LocalDate expected = LocalDate.now();
//        //When
//        user.setDateOfBirth(expected);
//        //Then
//        LocalDate actual = user.getDateOfBirth();
//        Assert.assertEquals(expected,actual);
//    }
//    @Test
//    public void setAndGetSSNTest()
//    {
//        //Given
//        String expected ="234-333-444";
//        //When
//        user.setSocialSecurity(expected);
//        //Then
//        String actual = user.getSocialSecurity();
//        Assert.assertEquals(expected,actual);
//    }
//    @Test
//    public void setAndGetPhoneNumberTest()
//    {
//        //Given
//        String expected ="234-333-4544";
//        //When
//        user.setPhoneNumber(expected);
//        //Then
//        String actual = user.getPhoneNumber();
//        Assert.assertEquals(expected,actual);
//    }
//    @Test
//    public void setAndGetEmailTest()
//    {
//        //Given
//        String expected ="test@gmail.com";
//        //When
//        user.setEmail(expected);
//        //Then
//        String actual = user.getEmail();
//        Assert.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void setAndGetLoginTest()
//    {
//        //Given
//         login = new Login();
//         login.setId(1l);;
//         login.setPassword("test");
//         login.setUsername("user");
//
//        //When
//        user.setLogin(login);
//        //Then
//        Login actual = user.getLogin();
//        Assert.assertEquals(login,actual);
//    }
//
//    @Test
//    public void setAndGetAccountTest()
//    {
//       //Given
//       account1 = new Account();
//       account1.setId(1L);
//       account1.setAccountType(AccountType.INVESTMENT);
//       expectedAccount = new HashSet<Account>() ;
//       expectedAccount.add(account1);
//       //When
//       user.setAccounts(expectedAccount);
//       //Then
//       Set<Account> actualAccount = user.getAccounts();
//       Assert.assertEquals(expectedAccount.size(),actualAccount.size());
//    }
//
//@Test
// public void setAndGetAddressTest()
//  {
//      //Given
//       expectedAddress = new Address();
//       expectedAddress.setFirstLine("First Line of Address");
//       expectedAddress.setSecondLIne("Second Line of Address");
//
//      //When
//      customer.setAddress(expectedAddress);
//     //Then
//     Address actualAddress = customer.getAddress();
//     Assert.assertEquals(expectedAddress,actualAddress);
//  }
}
