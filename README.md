# Pixel-Battle
Данный репозиторий содержит в себе проект Pixel Battle.

Проект представляет из себя веб приложение, в котором имеется веб страница, содержащая игровое поле с возможностью выбора цвета и положения. Также присутсвует регистрация для пользователя.

Суть приложения:
```
  Пользователь может выбрать цвет и положение пикселя(точки) на игровом поле, затем он должен зафиксировать свое положение с помощью кнопки save для сохранения результата.    
  Пользователь может зарегистрироваться, чтобы сохранять свою игровую сессия.
```

Зависимости проекта:
```
  org.postgresql:postgresql:jar:42.2.18:compile    
  org.springframework.boot:spring-boot-starter-web:jar:2.3.9.RELEASE:compile    
  org.springframework.data:spring-data-jpa:jar:2.3.7.RELEASE:compile    
  org.springframework.boot:spring-boot-starter-data-jpa:jar:2.3.9.RELEASE:compile    
  org.hibernate:hibernate-core:jar:5.4.27.Final:compile    
  org.springframework.boot:spring-boot-starter-test:jar:2.3.9.RELEASE:test    
  org.springframework.boot:spring-boot:jar:2.3.9.RELEASE:compile    
  org.springframework.boot:spring-boot-starter-security:jar:2.3.9.RELEASE:compile    
  org.springframework.session:spring-session-jdbc:jar:2.3.2.RELEASE:compile    
  org.springframework.security:spring-security-test:jar:5.3.8.RELEASE:test    
  org.thymeleaf:thymeleaf-spring5:jar:3.0.12.RELEASE:compile
```

Команда для запуска проекта:
    `mvn clean compile exec:java`
