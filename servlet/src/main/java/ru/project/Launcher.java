package ru.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class Launcher {

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(Launcher.class, args);
        /*
        Server server = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");

        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(AppConfig.class);
        webCtx.setParent(ctx);

        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(webCtx)), "/*");
        contextHandler.addEventListener(new ContextLoaderListener(webCtx));

        server.setHandler(contextHandler);
        server.start();
        */

        org.h2.tools.Server webServer = org.h2.tools.Server.createWebServer("-webAllowOthers","-webPort","8082").start();
        System.out.println("H2 WebServer started and connection is open");
        System.out.println("URL: " + webServer.getURL());
    }
}
