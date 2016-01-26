import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.project.controller.AppConfig;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(getServletHandler());
        server.start();

        /*
        org.h2.tools.Server webServer = org.h2.tools.Server.createWebServer("-webAllowOthers","-webPort","8082").start();
        System.out.println("H2 WebServer started and connection is open");
        System.out.println("URL:" + webServer.getURL());
        */
    }

    private static ServletContextHandler getServletHandler() throws IOException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        //AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


        ServletHolder mvcServletHolder = new ServletHolder("mvcServlet", new DispatcherServlet(ctx));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.addServlet(mvcServletHolder, "/");
        /*
        context.setResourceBase(//new ClassPathResource("webapp").getURI().toString()
                "file:/D:/dev/servlet-xml/servlet/src/main/webapp/"
        );
        */
        return context;
    }

}
