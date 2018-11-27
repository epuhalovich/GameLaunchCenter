package fall2018.csc2017.slidingtiles;

import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.content.Context;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the user manager.
 *
 * Following methods at:
 * - http://d.android.com/tools/testing
 * - https://developer.android.com/training/testing/unit-testing/local-unit-tests
 */
public class UserManagerTest {

    /** The user manager to be test.*/
    private UserManager userManager;

    private void makePopulatedUserManager(){
        userManager = new UserManager();
        ArrayList<User> testUsers = new ArrayList<>();
        testUsers.add(new User("player1", "123"));
        testUsers.add(new User("player2", "password"));
        testUsers.add(new User("player3", "crepes"));
        userManager.setAllUsers(testUsers);
    }

    @Test
    public void testHasAccount(){
        makePopulatedUserManager();
        Assert.assertEquals(0, userManager.hasAccount("player1"));
        Assert.assertEquals(-1, userManager.hasAccount("player4"));
    }

    @Test
    public void testSignUpTakenAccount(){
        makePopulatedUserManager();
        boolean thrownDuplicate = false;
        boolean otherExceptions = false;
        try {
            userManager.signUp("player1", "hi");
        } catch (DuplicateException e) {
            thrownDuplicate = true;
        }
        catch(Exception e){
            otherExceptions = true;
        }
        Assert.assertTrue(thrownDuplicate);
    }

    @Test
    public void testSuccesfulSignUp(){
        makePopulatedUserManager();
        boolean exceptions = false;
        try{
            User player = new User("ellen", "hi");
            userManager.signUp(player.getAccount(), player.getPassword());
            Assert.assertTrue(userManager.getAllUsers().contains(player));
        } catch (Exception e) {
            exceptions = true;
        }
        Assert.assertFalse(exceptions);
    }
    @Test
    public void testSignUpNoPassword(){
        makePopulatedUserManager();
        boolean thrownNoPassword =false;
        boolean otherExcpetions = false;
        try {
            userManager.signUp("hi", "");
        }catch(NoPassWordException e){
            thrownNoPassword = true;
        }
        catch(Exception e){
            otherExcpetions = true;
        }
        Assert.assertTrue(thrownNoPassword);
        Assert.assertFalse(otherExcpetions);
    }

    @Test
    public void testSignUpNoAccount(){
        makePopulatedUserManager();
        boolean otherExceptions = false;
        boolean thrownAccount = false;
        try {
            userManager.signUp("", "hi");
        }catch(AccountsException e){
            thrownAccount = true;
        }
        catch(Exception e){
            otherExceptions = true;
        }
        Assert.assertTrue(thrownAccount);
        Assert.assertFalse(otherExceptions);
    }

    @Test
    public void testSignInNoAccount(){
        makePopulatedUserManager();
        boolean thrownNoAccount = false;
        try {
            userManager.signIn("bob", "joe");
        }catch (AccountsException e){
            thrownNoAccount = true;}
            Assert.assertTrue(thrownNoAccount);
    }

    @Test
    public void testSignInWrongPassword(){
        makePopulatedUserManager();
        boolean thrownWrongPassword = false;
        try{
            userManager.signIn("player1", "1234");
        }catch (AccountsException e){
            thrownWrongPassword = true;
        }
        Assert.assertTrue(thrownWrongPassword);
    }

    @Test
    public void testSuccesfulSignIn(){
        makePopulatedUserManager();
        boolean excpetionThrown = false;
        try{
            User expected = new User ("player1","123");
            User actual = userManager.signIn("player1", "123");
            Assert.assertEquals(expected, actual);
        }catch(AccountsException e){
            excpetionThrown = true;
        }
        Assert.assertFalse(excpetionThrown);
    }

    @Test
    public void testRegister(){
        userManager = new UserManager();
        boolean thrownNull = false;
        PhaseTwoObserver observer = mock(PhaseTwoObserver.class);
        try{
            userManager.register(observer);
            Assert.assertTrue(UserManager.getObservers().contains(observer));
            userManager.register(null);
        }catch(NullPointerException e){
            thrownNull = true;
        }
        Assert.assertTrue(thrownNull);
    }

}
