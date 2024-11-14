package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/calcular")
public class Calculo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Utilizar StringWriter para construir el HTML
        StringWriter stringWriter = new StringWriter();
        try (PrintWriter out = new PrintWriter(stringWriter)) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Valores de Seno y Coseno</title>");
            out.println("<link rel='stylesheet' type='text/css' href='" + req.getContextPath() + "/estilos.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Tabla de Valores de Seno y Coseno</h1>");
            out.println("<table>");
            out.println("<thead><tr><th>Ángulo (grados)</th><th>Seno</th><th>Coseno</th></tr></thead>");
            out.println("<tbody>");

            // Calcular seno y coseno para ángulos de 0 a 360 grados en intervalos de 15 grados
            for (int grados = 0; grados <= 360; grados += 15) {
                double radianes = Math.toRadians(grados);
                double seno = Math.sin(radianes);
                double coseno = Math.cos(radianes);
                out.println("<tr>");
                out.println("<td>" + grados + "°</td>");
                out.println("<td>" + String.format("%.4f", seno) + "</td>");
                out.println("<td>" + String.format("%.4f", coseno) + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("<br><a href='/calculooo/index.html' class='btn-volver'>Volver al formulario</a>");
            out.println("</body>");
            out.println("</html>");
        }

        // Escribir el contenido generado al response
        try (PrintWriter out = resp.getWriter()) {
            out.println(stringWriter.toString());
        }
    }
}