import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHandler

object JettyLauncher {

  def main(args: Array[String]): Unit = {
    val server = new Server(1977)
    val handler = new ServletHandler()
    server.setHandler(handler)
    handler.addServletWithMapping(classOf[HelloServlet], "/*")
    handler.addServletWithMapping(classOf[LoveServlet], "/love")
    server.start()
    server.join()
  }

}

class HelloServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.getWriter.println("Hello, world!")
  }

}

class LoveServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.getWriter.println("I love you!")
  }

}
