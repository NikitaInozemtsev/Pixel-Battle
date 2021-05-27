package serv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import serv.LZW;
import serv.models.Pixel;
import serv.models.User;
import serv.services.PixelService;
import serv.services.UserService;


import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

/** Управляющий класс*/
@Controller
public class MyController {
    /** Сервис пикселей*/
    @Autowired
    private PixelService pixelService;

    /** Сервис пользователей*/
    @Autowired
    private UserService userService;

    /** Метод возвращающий начальную сраницу
     * @return начальная страница*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String startPage() {
        return "index";
    }


    /** Метод возвращающий сраницу авторизации
     * @return страница авторизации*/
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /** Метод возвращающий сраницу регистрации
     * @param user пользователь
     * @return страница регистрации*/
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(User user) {
        return "registration";
    }

    /** Метод добавляющий пользователя в таблицу
     * @param user пользователь с заполненными полями
     * @return перенаправление на страницу авторизации*/
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String signUp(@ModelAttribute User user) {
        userService.signUpUser(user);
        return "redirect:/login";
    }

    /** Метод проверяющий наличие пользователя в таблице
     * @param username имя пользователя
     * @return возвращает true если пользователь имееется в таблице*/
    @RequestMapping(value = "/isusername", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isUsernameExist(@RequestParam String username) {
        userService.loadUserByUsername(username);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    /** Метод возвращаюший пиксели из таблицы
     * @return пиксели*/
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ResponseEntity<String> select() {
        return new ResponseEntity<String>(pixelService.getPixels().getColor(), HttpStatus.OK);
    }

    /** Метод обновляющий пиксели в таблице
     * @param color пиксели*/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void insert(@RequestBody String color) {
        if (color.length() > 100) {
            pixelService.savePixels(new Pixel(color));
        } else {
            Pixel pixel = pixelService.getPixels();
            String px = pixel.getColor();
            List<Integer> arr =  Arrays.asList(px.split(" "))
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            String decom = LZW.decompress(arr);

            String[] pixels = decom.split(" ");
            String[] temp = color.split(" ");
            pixels[Integer.parseInt(temp[0])] = temp[1];
            String c = String.join(" ", pixels);
            c = Arrays.stream(LZW.compress(c).toArray())
                    .map(String::valueOf)
                    .reduce((a, b) -> a.concat(" ").concat(b))
                    .get();
            pixel.setColor(c);
            pixelService.setPixels(pixel);
            pixel = null;
            px = null;
            arr = null;
            decom = null;
            pixels = null;
            temp = null;
            c = null;
        }
    }
}
