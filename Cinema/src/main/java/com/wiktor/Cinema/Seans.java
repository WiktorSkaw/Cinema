package com.wiktor.Cinema;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Seans {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    public Date time;
    public boolean[][] seats;
    @ManyToOne //wiele seansow do jednego filmu
    public Film film;

    public Integer getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public boolean[][] getSeats() {
        return seats;
    }

    public Film getFilm() {
        return film;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setSeats(boolean[][] seats) {
        this.seats = seats;
    }

    public void setFilm(Film film) {
        this.film = film;
    }



    Seans(){
        seats = new boolean[7][7];
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                seats[i][j] = true;
            }
        }
    }
}
