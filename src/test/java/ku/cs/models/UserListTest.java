package ku.cs.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest {

    @Test
    @DisplayName("User should be found in UserList")
    public void testUserListFindUser() {
        // TODO: add 3 users to UserList
        UserList userList = new UserList();
        userList.addUser("user01", "plain-p@ssw0rd");
        userList.addUser("user02", "plain-p@ssw0rd");
        userList.addUser("user03", "plain-p@ssw0rd");

        // TODO: find one of them
        User user = userList.findUserByUsername("user02");

        // TODO: assert that UserList found User
        // String expected = "<one of username>";
        // String actual = user.getUsername();
        // assertEquals(expected, actual);
        String expectedUser = "user02";
        String actualUser = user.getUsername();
        assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("User can change password")
    public void testUserCanChangePassword() {
        // TODO: add 3 users to UserList
        UserList userList = new UserList();
        userList.addUser("user01", "plain-p@ssw0rd");
        userList.addUser("user02", "plain-p@ssw0rd");
        userList.addUser("user03", "plain-p@ssw0rd");

        // TODO: change password of one user
        boolean actual = userList.changePassword("user02", "plain-p@ssw0rd", "P@ssW0rdZazaza");

        // TODO: assert that user can change password
        // assertTrue(actual);
        assertTrue(actual);
    }

    @Test
    @DisplayName("User with correct password can login")
    public void testUserListShouldReturnObjectIfUsernameAndPasswordIsCorrect() {
        // TODO: add 3 users to UserList
        UserList userList = new UserList();
        userList.addUser("user01", "plain-p@ssw0rd");
        userList.addUser("user02", "plain-p@ssw0rd");
        userList.addUser("user03", "plain-p@ssw0rd");

        // TODO: call login() with correct username and password
        User expected = userList.findUserByUsername("user02");
        User actual = userList.login("user02", "plain-p@ssw0rd");

        // TODO: assert that User object is found
        // assertEquals(expected, actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("User with incorrect password cannot login")
    public void testUserListShouldReturnNullIfUsernameAndPasswordIsIncorrect() {
        // TODO: add 3 users to UserList
        UserList userList = new UserList();
        userList.addUser("user01", "plain-p@ssw0rd");
        userList.addUser("user02", "plain-p@ssw0rd");
        userList.addUser("user03", "plain-p@ssw0rd");

        // TODO: call login() with incorrect username or incorrect password
        User actual = userList.login("user02", "this-is-wrong-pass");

        // TODO: assert that the method return null
        // assertNull(actual);
        assertNull(actual);
    }

}