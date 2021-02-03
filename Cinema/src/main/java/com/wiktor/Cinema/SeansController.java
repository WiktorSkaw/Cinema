package com.wiktor.Cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class SeansController {

    @Autowired
    private SeansRepository seansRepository;
    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping(path="/CreateSeans", params = {"date","time","filmID"}) //ta funkcja jesli sa te dwa parametry
    public String createSeans(@RequestParam(required = true) String date,@RequestParam(required = true) String time ,@RequestParam(required = true) Integer filmID, Model model){

        Seans seans = new Seans();

        String year = date.substring(6);
        String month = date.substring(3,5);
        String day = date.substring(0,2);
        String hour = time.substring(0,2);
        String minute = time.substring(3,5);

        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month) - 1; //XD
        int dayInt = Integer.parseInt(day);
        int hourInt = Integer.parseInt(hour);
        int minuteInt = Integer.parseInt(minute);

        Calendar calendar = new Calendar.Builder().setDate(yearInt,monthInt,dayInt).setTimeOfDay(hourInt,minuteInt,0).build();

        Date timeStamp = calendar.getTime();
        //System.out.println("Brakepoint"+ year + " " + month + " " + day + " " + hour + " " + minute + " " + timeStamp.toString() + " " + calendar.toString());

        Film film = null;
        try {
            film = filmRepository.findById(filmID).orElseThrow(() -> new Exception("Film not found"));
            seans.setFilm(film);
            seans.setSeats(new Seats[7][7]);
            seans.setTime(timeStamp);
            seansRepository.save(seans);
            model.addAttribute("message","Seans Created" );
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message",e.getMessage());
        }

        return "index";
    }

    @RequestMapping(path="/CreateSeans")
    public String createSeansStart(Model model){

        List<Film> allFilms = (List<Film>) filmRepository.findAll();
        model.addAttribute("allFilms", allFilms);

        return "seans";
    }
}
