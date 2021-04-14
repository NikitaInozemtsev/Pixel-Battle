package serv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import serv.models.Pixel;
import serv.models.User;
import serv.services.PixelService;
import serv.services.UserService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
        return "newCanvasFrame";
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
        pixelService.setPixels(new Pixel(color));
    }
}
