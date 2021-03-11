package serv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import serv.models.Pixel;
import serv.models.User;
import serv.services.PixelService;
import serv.services.UserService;

@Controller
public class MyController {
    @Autowired
    private PixelService pixelService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String startPage() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(User user) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String signUp(@ModelAttribute User user) {
        userService.signUpUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/isusername", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isUsernameExist(@RequestParam String username) {
        userService.loadUserByUsername(username);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);

    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ResponseEntity<String> select() {
        return new ResponseEntity<String>(pixelService.getPixels().getColor(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void insert(@RequestBody String color) {
        pixelService.setPixels(new Pixel(color));
    }
}
