package pl.jkanclerz.creditcard;

import com.sun.imageio.plugins.common.BogusColorSpace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCreditLimit(){
        //Arrange
        CreditCard card = new CreditCard("12343-5465");
        //Act
        card.assignCredit(BigDecimal.valueOf(1000));
        //Assert
        assertEquals(BigDecimal.valueOf(1000),card.getBalance());
    }

    @Test
    void itAllowsToAssignDifferentCreditLimit(){
        //Arrange
        CreditCard card = new CreditCard("12343-5465");
        CreditCard card2 = new CreditCard("12343-5466");
        //Act
        card.assignCredit(BigDecimal.valueOf(1000));
        card2.assignCredit(BigDecimal.valueOf(1100));

        //Assert
        assertEquals(BigDecimal.valueOf(1000),card.getBalance());
        assertEquals(BigDecimal.valueOf(1100),card2.getBalance());
    }

    @Test
    void  itCantAssignLimitBelowCertainThreshold(){
        CreditCard card = new CreditCard("4324-2343");

        try{
            card.assignCredit(BigDecimal.valueOf(10));
        } catch (CreditLimitBelowThresholdException e){
            assertTrue(true);
        }
        assertThrows(CreditLimitBelowThresholdException.class,
                () -> card.assignCredit(BigDecimal.valueOf(10)));

        assertThrows(CreditLimitBelowThresholdException.class,
                ()-> card.assignCredit(BigDecimal.valueOf(99)));

        assertDoesNotThrow(
                () -> card.assignCredit(BigDecimal.valueOf(100)));
    }
    @Test
    void itDenyToAssignCreditTwice(){
        CreditCard card = new CreditCard("1236-4323");
        card.assignCredit(BigDecimal.valueOf(1000));

        assertThrows(
                CantAssignCreditTwiceException.class,
                () -> card.assignCredit(BigDecimal.valueOf(1000))
        );
    }
    @Test
    void itDenyToWithdrawBelowCLimit(){
        //Arrange
        CreditCard card = new CreditCard("3213-4324");
        card.assignCredit(BigDecimal.valueOf(1000));
        //Act
        card.withdraw(BigDecimal.valueOf(100));
        //Assert
        assertThrows(
                CantWithdrawMoreThanLimitException.class,
                ()-> card.withdraw(BigDecimal.valueOf(1000))
        );

    }

    @Test
    void checkDoublesAndFloats(){
        double x1 =0.01f;
        double x2 = 0.03f;
        double result = x2-x1;
    }

    @Test
    void checkDoublesAndFloats2(){
        float y1 =0.01f;
        float y2 = 0.03f;
        float yresult = y2-y1;
    }
}
