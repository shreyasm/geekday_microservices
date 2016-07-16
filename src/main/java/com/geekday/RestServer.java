package com.geekday;

import com.geekday.account.AccountRepository;
import com.geekday.account.AccountService;
import com.geekday.common.Listener;
import com.geekday.customer.CustomerRepository;
import com.geekday.web.AccountResource;
import com.geekday.web.CustomerResource;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;
import java.util.List;


public class RestServer {

    public static void main(String[] args) throws Exception {
        CustomerRepository.initialize();
        AccountRepository.initialise();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames", CustomerResource.class.getCanonicalName() + "," + AccountResource.class.getCanonicalName());

        try {
            System.out.println("jerseyServlet = " + jerseyServlet);
            jettyServer.start();
            Listener.getInstance();
            jettyServer.join();
        } catch(Throwable t) {
            t.printStackTrace();
        }finally {
            jettyServer.destroy();
        }
    }
}