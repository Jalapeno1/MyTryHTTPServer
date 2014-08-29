/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firsthttpserverexcercisejonas;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jonas
 */
public class FirstHTTPServerExcerciseJonas
{

    static int port = 2020; //8080 - !!!!!!!!!port 8080 virker ikke på min PC!!!!!!!!!
    static String ip = "127.0.0.1";

    public static void main(String[] args) throws Exception
    {
        if (args.length == 2)
        {
            port = Integer.parseInt(args[0]);
            ip = args[0];
        }
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/welcome", new RequestHandler());
        server.createContext("/headers", new RequestHandler1());
        server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }

    static class RequestHandler1 implements HttpHandler {
        
        @Override
        public void handle(HttpExchange he) throws IOException
        {
            String response = "Welcome to my very second almost home made Web Server :-)";
            
            StringBuilder sb = new StringBuilder();
            
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html>\n");
            sb.append("<head>\n");
            sb.append("<title>My fancy Web Site</title>\n");
            sb.append("<meta charset='UTF-8'>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
            
            
            //table
            sb.append("<table border=\"1\">");
            
            Map<String, List<String>> headers = he.getRequestHeaders();
            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
            for(Map.Entry<String, List<String>> e : entrySet){
//                e.getKey();
//                e.getValue();
                sb.append("<tr><td>").append(e.getKey()).append("</td><td>").append(e.getValue()).append("</td></tr>");
                    
            }
//            sb.append("<tr>");
//            sb.append("<th>Header</th>");
//            sb.append("<th>Value</th>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td>Cache-control</td>");
//            sb.append("<td>[max-age=0]</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td>Host</td>");
//            sb.append("<td>[localhost:8080]</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td>Accept-encoding</td>");
//            sb.append("<td>[gzip,deflate,sdch]</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td>Connection</td>");
//            sb.append("<td>[keep-alive]</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td>Accept-language</td>");
//            sb.append("<td>[da,en-US;q=0.8,en;q=0.6,en-GB;q=0.4]</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td>User-agent</td>");
//            sb.append("<td>[Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like \nGecko) Chrome/36.0.1985.143 Safari/537.36]</td>");
//            sb.append("</tr>");
//            sb.append("<tr>");
//            sb.append("<td>Accept</td>");
//            sb.append("<td>[text/html,capplication/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8]</td>");
//            sb.append("</tr>");
            
            sb.append("</body>\n");
            sb.append("</html>\n");
            
            response = sb.toString();
            
            
            
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", "text/html");
            he.sendResponseHeaders(200, response.length());
            try (PrintWriter pw = new PrintWriter(he.getResponseBody()))
            {
                pw.print(response); //What happens if we use a println instead of print --> Explain
            }
        }
        
    }
    static class RequestHandler implements HttpHandler
    {
        @Override
        public void handle(HttpExchange he) throws IOException
        {
            String response = "Welcome to my very first almost home made Web Server :-)";
           
// Opgave 1 -------------------------------------------------------------
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html>\n");
            sb.append("<head>\n");
            sb.append("<title>My fancy Web Site</title>\n");
            sb.append("<meta charset='UTF-8'>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
            sb.append("<h2>Welcome to my very first home made Web Server :-)</h2>\n");
            sb.append("</body>\n");
            sb.append("</html>\n");
            
            response = sb.toString();
            
            
            
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", "text/html");
            he.sendResponseHeaders(200, response.length());
            try (PrintWriter pw = new PrintWriter(he.getResponseBody()))
            {
                pw.print(response); //What happens if we use a println instead of print --> Explain
            }
        }
    }
}
