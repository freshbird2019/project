package com.example.demo.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainpic")
public class Trainpic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainpicid;
    @Column(name = "size")
    private String size;
    @Column(name = "local")
    private String local;

    @OneToMany(mappedBy = "trainpic",fetch = FetchType.LAZY)
    private List<UserTrainpic> userTrainpicList;

    public Trainpic() {
    }

    public Trainpic(Integer trainpicid, String size,String local) {
        this.trainpicid = trainpicid;
        this.local=local;
        this.size=size;
    }

    public void setTrainpicid(Integer trainpicid) {
        this.trainpicid = trainpicid;
    }

    public Integer getTrainpicid() {
        return trainpicid;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<UserTrainpic> getUserTrainpicList() {
        return userTrainpicList;
    }

    @JsonBackReference
    public void setUserTrainpicList(List<UserTrainpic> userTrainpicList) {
        this.userTrainpicList = userTrainpicList;
    }
}
