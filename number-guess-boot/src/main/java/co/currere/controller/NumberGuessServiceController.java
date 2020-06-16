package co.currere.controller;

import co.currere.domain.Win;
import co.currere.service.NumberGuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
final class NumberGuessServiceController {

    private final NumberGuessService numberGuessService;

    @Autowired
    public NumberGuessServiceController(final NumberGuessService numberGuessService) {
        this.numberGuessService = numberGuessService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    void registerWin(@RequestBody Win win) {
        System.out.println("/registerWin: " + win);
        numberGuessService.registerWin(win);
    }

    @GetMapping("/average")
    int getAverageGuesses() {
        System.out.println("/getAverageGuesses");
        return numberGuessService.getAverageGuesses();
    }

}
