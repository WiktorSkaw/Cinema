package com.wiktor.Cinema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //adnotacja encji, klasa ktora ma byc mapowana
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //strategia indeksowania
    private Integer id;

    String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
