package com.wiktor.Cinema;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Seans {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    public Date time;
    public Seats[][] seats;
    @ManyToOne
    public Film film;

    public Integer getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public Seats[][] getSeats() {
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

    public void setSeats(Seats[][] seats) {
        this.seats = seats;
    }

    public void setFilm(Film film) {
        this.film = film;
    }



    Seans(){
        seats = new Seats[7][7];
    }
}
