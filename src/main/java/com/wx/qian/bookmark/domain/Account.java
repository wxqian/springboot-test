package com.wx.qian.bookmark.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/10/8
 */
@Entity
@Table(name = "t_account")
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @Column
    @JsonIgnore
    private String userName;

    @Column
    private String passWord;

    @OneToMany
    private Set<BookMark> bookMarks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Set<BookMark> getBookMarks() {
        return bookMarks;
    }

    public void setBookMarks(Set<BookMark> bookMarks) {
        this.bookMarks = bookMarks;
    }

    public Account() {
    }

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}
