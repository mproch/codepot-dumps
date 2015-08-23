package pl.touk.codepot.heap;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import pl.touk.codepot.Sleep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Selective {

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

            String person = req.getParameter("name");
            int counter = Integer.parseInt(req.getParameter("value"));

            List<String> list = new ArrayList<>();
            loop(list, counter);


            resp.getOutputStream().println("<html><body><p>OOOOK</p></body></html>");
        }
    }

    public static class Bar extends HttpServlet {

        private Map<String, List<String>> reqs = new ConcurrentHashMap<>();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String person = req.getParameter("name");
            int counter = Integer.parseInt(req.getParameter("value"));

            List<String> list = new ArrayList<>();
            reqs.put(person, list);
            loop(list, counter);

            resp.getOutputStream().println("<html><body><p>OOOOK</p></body></html>");
        }
    }

    private static void loop(List<String> list, int counter) {
        if (counter % 4 == 0) {
            for (int i=0;;i++) {
                Sleep.sleep(1);
                list.add("counter "+i + counter);
            }
        } else {
            list.add("counter " + counter);
        }

    }
}
