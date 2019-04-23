package com.example.demo.Domain;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    @Column(name="username")
    private String username;
    @Column(name = "password")
    private String pwd;

    public User(){

    }

    public User(Integer id,String username,String pwd){
        this.userid=id;
        this.username=username;
        this.pwd=pwd;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
