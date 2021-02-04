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

    @RequestMapping(path="/CreateSeans", params = {"date","time","filmID"}) //ta funkcja jesli sa te trzy parametry
    public String createSeans(@RequestParam(required = true) String date,@RequestParam(required = true) String time ,@RequestParam(required = true) Integer filmID, Model model){ //dostajemy date i czas z formularza i przeksztalcamy w cala date z godzina

        Seans seans = new Seans();

        String year = date.substring(6); //wyciaganie okreslonych lancuchow ze stringa
        String month = date.substring(3,5);
        String day = date.substring(0,2);
        String hour = time.substring(0,2);
        String minute = time.substring(3,5);

        int yearInt = Integer.parseInt(year); //zamiana string na int
        int monthInt = Integer.parseInt(month) - 1; //XD
        int dayInt = Integer.parseInt(day);
        int hourInt = Integer.parseInt(hour);
        int minuteInt = Integer.parseInt(minute);

        Calendar calendar = new Calendar.Builder().setDate(yearInt,monthInt,dayInt).setTimeOfDay(hourInt,minuteInt,0).build(); //builder

        Date timeStamp = calendar.getTime(); //pobieramy date z calendar i wpisujemy jako Data
        //System.out.println("Brakepoint"+ year + " " + month + " " + day + " " + hour + " " + minute + " " + timeStamp.toString() + " " + calendar.toString());

        Film film = null;
        try {
            film = filmRepository.findById(filmID).orElseThrow(() -> new Exception("Film not found")); //tworzymy seans dla konkretnego filmu
            seans.setFilm(film);
            seans.setTime(timeStamp);
            seansRepository.save(seans);
            model.addAttribute("message","Seans Created" );
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message",e.getMessage());
        }

        return "seans";
    }

    @RequestMapping(path="/CreateSeans") //jezlei nie podajemy paramterow to wywola sie ta funkcja ktora wysietla filmy dla ktorych mozemy stworzyc seans (to nie powinno byc w filmController)
    public String createSeansStart(Model model){ //jesli nie dostanie paramtrow wywola sie ta funkcja

        List<Film> allFilms = (List<Film>) filmRepository.findAll(); //pobieramy wszytskie filmy z bazy
        model.addAttribute("allFilms", allFilms);

        return "seans";
    }
}
