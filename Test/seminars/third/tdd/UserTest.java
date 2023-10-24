package seminars.third.tdd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    static UserRepository repository;

    @BeforeAll
    static void setup() {
        repository = new UserRepository();
    }

    /*
    3.6
    Разработайте класс User с методом аутентификации по логину и паролю.
    Метод должен возвращать true, если введенные логин и пароль корректны, иначе false.
    Протестируйте все методы
 */
    @Test
    void testAuthenticationCorrectUserAndPassword() {
        String username = "user";
        String password = "password";

        User user = new User(username, password, false);
        boolean authResult = user.authenticate(username, password);
        assertTrue(authResult);
    }

    @Test
    void testAuthenticationWrongUserCorrectPassword() {
        String username = "user";
        String password = "password";
        String wrongUser = "wronguser";
        String wrongPassword = "wrongpassword";

        User user = new User(username, password, false);
        boolean authResult = user.authenticate(wrongUser, password);
        assertFalse(authResult);
    }

    @Test
    void testAuthenticationCorrectUserWrongPassword() {
        String username = "user";
        String password = "password";
        String wrongUser = "wronguser";
        String wrongPassword = "wrongpassword";

        User user = new User(username, password, false);
        boolean authResult = user.authenticate(username, wrongPassword);
        assertFalse(authResult);
    }

    @Test
    void testAddRepositoryCorrectUserAndPassword() {
        String username = "user";
        String password = "password";
        String wrongUser = "wronguser";
        String wrongPassword = "wrongpassword";

        User user = new User(username, password, false);
        user.authenticate(username, password);
        repository.addUser(user);

        User addedUser = repository.data.get(repository.data.size() - 1);
        assertEquals(user, addedUser);

    }

    @Test
    void testNotAddRepositoryWrongUserAndCorrectPassword() {
        String username = "user";
        String password = "password";
        String wrongUser = "wronguser";
        String wrongPassword = "wrongpassword";

        User user = new User(username, password, false);
        user.authenticate(wrongUser, password);
        int currentSize = repository.data.size();
        repository.addUser(user);

        assertThat(repository.data.size()).isEqualTo(currentSize);

    }

    /*
        HW 3.3
        Добавьте функцию в класс UserRepository, которая разлогинивает всех пользователей,
        кроме администраторов. Для этого, вам потребуется расширить класс User новым свойством,
        указывающим, обладает ли пользователь админскими правами. Протестируйте данную функцию.
     */
    @Test
    void testRepositoryDataUserDeleteIfNotAdmin() {
        String username = "username";
        String password = "password";
        boolean isAdmin = false;

        User user = new User(username, password, isAdmin);
        user.authenticate(username, password);
        repository.addUser(user);

        repository.deleteUser(user);

        assertFalse(repository.data.contains(user));
    }

    @Test
    void testRepositoryDataUserDeleteIfAdmin() {
        String username = "username";
        String password = "password";
        boolean isAdmin = true;

        User user = new User(username, password, isAdmin);
        user.authenticate(username, password);
        repository.addUser(user);

        repository.deleteUser(user);

        assertTrue(repository.data.contains(user));
    }

    @Test
    void testIsAuthenticationUserAfterUserDeleteIfNotAdmin() {
        String username = "username";
        String password = "password";
        boolean isAdmin = false;

        User user = new User(username, password, isAdmin);
        user.authenticate(username, password);
        repository.addUser(user);
        repository.deleteUser(user);

        assertFalse(user.isAuthenticate);
    }

    @Test
    void testIsAuthenticationUserAfterUserDeleteIfAdmin() {
        String username = "username";
        String password = "password";
        boolean isAdmin = true;

        User user = new User(username, password, isAdmin);
        user.authenticate(username, password);
        repository.addUser(user);
        repository.deleteUser(user);

        assertTrue(user.isAuthenticate);
    }


}