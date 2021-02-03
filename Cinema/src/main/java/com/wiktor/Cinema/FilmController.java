package com.wiktor.Cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping(path = "/CreateFilm")
    public String createFilm(@RequestParam(required = false) String name, Model model) {
        if (name != null) {
            Film film = new Film();
            film.setName(name);

            filmRepository.save(film); //film zostaje zapisany w bazie

            model.addAttribute("message", "Film Created ");
        }
        return "film";
    }
}