package serv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.sql.Array;

@Controller
public class PBSOAPService {

	private PBMain basetalker;

	/**
	 *
	 * @param basetalker Установка сервиса
	 */

	public PBSOAPService(@Autowired PBMain basetalker) {
		this.basetalker = basetalker;
	}

	/**
	 *
	 * @param color Команда сервису
	 * @return Возврат признака успеха
	 */
//	@WebMethod
//	@WebResult(name="Serv")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public boolean insert(String color, int x, int y) {
		return basetalker.insert(color,x,y);
	}

	/**
	 *
	 * @return Обновление изображения
	 */
//	@WebMethod
//	@WebResult(name="Serv")
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public Array select() {
		return basetalker.select();
	}
}
