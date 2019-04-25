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
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private  String phone;
    @Column(name = "sex")
    private  String sex;
    @Column(name = "address")
    private  String address;
    @Column(name = "hospital")
    private  String hospital;

    public User(){

    }

    public User(Integer id,String username,String pwd,String email,String phone,
                String sex,String address,String hospital){
        this.userid=id;
        this.username=username;
        this.pwd=pwd;
        this.email=email;
        this.phone=phone;
        this.sex=sex;
        this.address=address;
        this.hospital=hospital;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospital() {
        return hospital;
    }
}
