import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.project.controller.AppConfig;

import javax.inject.Inject;
import java.io.IOException;

public class Launcher {

    private static ApplicationContext ctx;

    @Inject
    public Launcher(ApplicationContext ctx) {
        this.ctx = ctx;
    }

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
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");

        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(AppConfig.class);
        webCtx.setParent(ctx);

        contextHandler.addServlet(new ServletHolder("mvcServlet", new DispatcherServlet(webCtx)), "/*");
        contextHandler.addEventListener(new ContextLoaderListener(webCtx));

        return contextHandler;
    }

}
