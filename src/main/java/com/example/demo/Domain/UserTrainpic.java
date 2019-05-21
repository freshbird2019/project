package com.example.demo.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "train")
public class UserTrainpic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainid;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trainpicid")
    private Trainpic trainpic;

    @Column(name = "traintime",nullable = false)
    private Timestamp traintime;

    @Column(name = "usersign",nullable = false)
    private String usersign;

    @Column(name = "accuracy")
    private double accuracy;

    public Integer getTrainid() {
        return trainid;
    }

    public void setTrainid(Integer trainid) {
        this.trainid = trainid;
    }

   // @JsonBackReference
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    //@JsonBackReference
    public void setTrainpic(Trainpic trainpic) {
        this.trainpic = trainpic;
    }

    public Trainpic getTrainpic() {
        return trainpic;
    }

    public void setUsersign(String usersign) {
        this.usersign = usersign;
    }

    public String getUsersign() {
        return usersign;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setTraintime(Timestamp traintime) {
        this.traintime = traintime;
    }

    public Timestamp getTraintime() {
        return traintime;
    }
}
