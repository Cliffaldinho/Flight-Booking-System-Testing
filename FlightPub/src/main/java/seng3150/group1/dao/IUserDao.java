package seng3150.group1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seng3150.group1.entities.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer> {

    /**
     * Finds the User with this username
     * @param username - the username of the User you want to retrieve
     * @return the User entity whose username matches the input, null if no match
     */
    User findByUsername(String username);

    /**
     * Finds the User with this username and password
     * @param username - the username of the User you want to retrieve
     * @param password - the password of the User you want to retrieve
     * @return the User entity whose username and password match both of the parameters, or null if no match
     */
    User findByUsernameAndPassword(String username, String password);
}
