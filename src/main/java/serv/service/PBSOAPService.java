package serv.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PBSOAPService {


	private PBMain basetalker = null;

	/**
	 *
	 * @param basetalker Установка сервиса
	 */

	public PBSOAPService(@Value("${user.db_url}") String DB_URL,
						 @Value("${user.name}") String user,
						 @Value("${user.password}") String password){
		this.basetalker = new PBMain(DB_URL, user, password);
	}

	/**
	 *
	 * @param color Команда сервису
	 * @return Возврат признака успеха
	 */

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void insert(String color) {
		basetalker.insert(color);
	}

	/**
	 *
	 * @return Обновление изображения
	 */

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ResponseEntity<String> select() {
		return basetalker.select();
	}
}
