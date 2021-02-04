package com.wiktor.Cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class indexController {

    @Autowired
    private SeansRepository seansRepository;

    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping(path="/") //mapowanie żądań webowych na metody Spring Controller
    public String indexControll(Model model, @RequestParam(required = false) String filmID,@RequestParam(required = false) String seansID,@RequestParam(required = false) String rowID,@RequestParam(required = false) String seatID){ //musimy podac model w parametrach, inne parametry nie sa wymagane


        List<Film> allFilms = (List<Film>) filmRepository.findAll(); //wszystkie filmy z bazy
        model.addAttribute("allFilms", allFilms);


        if(filmID != null){
            try {
                int filmIDInt = Integer.parseInt(filmID); //zamiana string na int
                Film film = filmRepository.findById(filmIDInt).orElseThrow(() -> new Exception("Film not found"));
                List<Seans> allSeans = seansRepository.findSeansByFilm(film); //seanse dla danego filmu (List<Seans>)
                model.addAttribute("filmID", filmID);
                model.addAttribute("allSeans", allSeans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
           if(seansID != null){
               try {
                   int seansIDInt = Integer.parseInt(seansID);
                   Seans seans = seansRepository.findById(seansIDInt).orElseThrow(() -> new Exception("Seans not found"));
                   model.addAttribute("seats", seans.getSeats());
                   model.addAttribute("seansID", seansID);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }

        if(rowID != null && seatID !=null && seansID != null){
            try {
                int rowIDInt = Integer.parseInt(rowID);
                int seatIDInt = Integer.parseInt(seatID);
                int seansIDInt = Integer.parseInt(seansID);
                Seans seans = seansRepository.findById(seansIDInt).orElseThrow(() -> new Exception("Seans not found"));
                seans.seats[rowIDInt][seatIDInt] = false;
                seans.setSeats(seans.seats);
                seansRepository.save(seans);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "index";
    }

}
