package com.ashokn.model;

import org.hibernate.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

/**
 * Created by ashok on 6/20/17.
 */
@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = { "authority", "username" }))
public class Authority extends Model{

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    public Authority(User user,String authority){
        this.user = user;
        this.authority = authority;
    }

    public Authority(){}

    public Person getPerson() {
        return user;
    }

    public void setPerson(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
