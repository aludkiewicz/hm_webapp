package se.hm.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller returning the UIs
 */
@Controller
@RequestMapping(value = "/ui", method = RequestMethod.GET)
public class UiController {

    @RequestMapping(method = RequestMethod.GET)
    public String getUI() throws IOException {
        return "ui/ui";
    }
}