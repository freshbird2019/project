package com.example.demo.Domain;

import javax.persistence.*;

@Entity
@Table(name = "medicalpic")
public class Medicalpic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicalpicid;
    @Column(name = "description")
    private String desc;
    @Column(name = "size")
    private String size;
    @Column(name = "local")
    private String local;
    @Column(name = "state")
    private int state;
    @Column(name = "flag")
    private int flag;

    public Medicalpic(){}

    public Medicalpic(Integer medicalpicid,String desc,String sizeofmed,
                      int flag,int state,String local){
        this.medicalpicid=medicalpicid;
        this.desc=desc;
        this.size=sizeofmed;
        this.local=local;
        this.state=state;
        this.flag=flag;
    }

    public Integer getMedicalpicid() {
        return medicalpicid;
    }

    public void setMedicalpicid(Integer medicalpicid) {
        this.medicalpicid = medicalpicid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSize(String sizeofmed) {
        this.size= sizeofmed;
    }

    public String getSize() {
        return size;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
