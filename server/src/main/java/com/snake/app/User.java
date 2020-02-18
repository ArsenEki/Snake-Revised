package com.snake.app;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * User model and entity for database purposes.
 */
@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.DataflowAnomalyAnalysis"})
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //@Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String userName;

    private String password;

    private double highscore;

    /**
     * Constructor for an empty user.
     */
    public User(){
    }

    /**
     * Constructor for 2 fields.
     * @param userName username.
     * @param password password.
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Constructor for all the fields.
     * @param userName username.
     * @param password password.
     * @param highscore highscore.
     */
    public User(String userName, String password, double highscore) {
        this.userName = userName;
        this.password = password;
        this.highscore = highscore;

    }

    /**
     * Gets username.
     * @return username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets password.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user's Id.
     * @return the Id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets password.
     * @param password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets highscore.
     * @return highscore.
     */
    public double getHighscore() {
        return highscore;
    }

    /**
     * Sets highscore.
     * @param highscore to set.
     */
    public void setHighscore(double highscore) {
        this.highscore = highscore;
    }

    /**
     * Since usernames are unique, if username equals
     * it's the same user.
     * @param o object to compare to.
     * @return if equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userName.equals(user.userName);
    }

    /**
     * Hasher.
     * @return int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }

    /**
     * ToString method.
     * @return a string.
     */
    @Override
    public String toString() {
        return userName + " " + highscore;
    }
}
