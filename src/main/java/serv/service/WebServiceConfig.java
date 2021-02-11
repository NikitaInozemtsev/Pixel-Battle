package serv.service;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private PBMain basetalker;

    /**
     *
     * @return Создание cxfServlet на localhost
     */
    @Bean
    public ServletRegistrationBean<CXFServlet> wsDispatcherServlet() {
        CXFServlet cxfServlet = new CXFServlet();
        return new ServletRegistrationBean<CXFServlet>(cxfServlet, "/PB/*");
    }

    /**
     *
     * @return Развертывание микросервиса по пути localhost/PB/PBService
     */
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new PBSOAPService(basetalker));
        endpoint.publish("/PBService");
        return endpoint;
    }
}