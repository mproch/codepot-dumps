package pl.touk.codepot.stack;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import pl.touk.codepot.Sleep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class State {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8083);


        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(Foo.class, "/foo");
        handler.addServletWithMapping(Bar.class, "/bar");

        server.start();
        server.join();
    }


    public static class Foo extends HttpServlet {


        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int counter = Integer.parseInt(req.getParameter("value"));
            Sleep.sleep(counter);
            resp.getOutputStream().println("<html><body><p>OOOOK</p></body></html>");
        }
    }

    public static class Bar extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int counter = Integer.parseInt(req.getParameter("value"));

            Sleep.sleep(counter);
            resp.getOutputStream().println("<html><body><p>OOOOK</p></body></html>");
        }
    }
}
