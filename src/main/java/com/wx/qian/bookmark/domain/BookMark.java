package com.wx.qian.bookmark.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/10/8
 */
@Entity
@Table(name = "t_bookmark")
public class BookMark {

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    private Account account;

    private String uri;

    private String description;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookMark() {
    }

    public BookMark(Account account, String uri, String description) {
        this.account = account;
        this.uri = uri;
        this.description = description;
    }
}
