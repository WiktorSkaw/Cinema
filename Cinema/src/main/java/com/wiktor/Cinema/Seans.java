package com.wiktor.Cinema;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Seans {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    public Date time;
    public Integer[][] seats;
    @ManyToOne
    public Film film;

    public Integer getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public Integer[][] getSeats() {
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

    public void setSeats(Integer[][] seats) {
        this.seats = seats;
    }

    public void setFilm(Film film) {
        this.film = film;
    }



    Seans(){
        seats = new Integer[7][7];
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                seats[i][j] = 0;
            }
        }
    }
}
