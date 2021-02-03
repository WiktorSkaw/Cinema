package com.wiktor.Cinema;

import javax.persistence.*;
import java.util.List;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) // automatycznie dobiera strategie wybierania indentyfikatora
    private Integer id;

    @OneToMany
    List<Seans> seans;

    String name;


    public Integer getId() { return id; }

    public List<Seans> getSeans() {
        return seans;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSeans(List<Seans> seans) {
        this.seans = seans;
    }

    public void setName(String name) {
        this.name = name;
    }

    Film(){}

    public void main(String[] arg){

    }
}
