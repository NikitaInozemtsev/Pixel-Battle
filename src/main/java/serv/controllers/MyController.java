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

/** ����������� �����*/
@Controller
public class MyController {
    /** ������ ��������*/
    @Autowired
    private PixelService pixelService;

    /** ������ �������������*/
    @Autowired
    private UserService userService;

    /** ����� ������������ ��������� �������
     * @return ��������� ��������*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String startPage() {
        return "index";
    }


    /** ����� ������������ ������� �����������
     * @return �������� �����������*/
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /** ����� ������������ ������� �����������
     * @param user ������������
     * @return �������� �����������*/
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(User user) {
        return "registration";
    }

    /** ����� ����������� ������������ � �������
     * @param user ������������ � ������������ ������
     * @return ��������������� �� �������� �����������*/
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String signUp(@ModelAttribute User user) {
        userService.signUpUser(user);
        return "redirect:/login";
    }

    /** ����� ����������� ������� ������������ � �������
     * @param username ��� ������������
     * @return ���������� true ���� ������������ �������� � �������*/
    @RequestMapping(value = "/isusername", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isUsernameExist(@RequestParam String username) {
        userService.loadUserByUsername(username);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    /** ����� ������������ ������� �� �������
     * @return �������*/
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ResponseEntity<String> select() {
        return new ResponseEntity<String>(pixelService.getPixels().getColor(), HttpStatus.OK);
    }

    /** ����� ����������� ������� � �������
     * @param color �������*/
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
