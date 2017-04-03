import java.io.File
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHandler

import scala.xml.Node

object JettyLauncher {

  def main(args: Array[String]): Unit = {
    val server = new Server(1977)
    val handler = new ServletHandler()
    server.setHandler(handler)
    handler.addServletWithMapping(classOf[HelloServlet], "/*")
    handler.addServletWithMapping(classOf[LoveServlet], "/love")
    handler.addServletWithMapping(classOf[FinderServlet], "/finder/*")
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

class FinderServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val writer = resp.getWriter
    val path = req.getPathInfo match {
      case null => "."
      case "" => "."
      case pi => "." + pi
    }
    writer.println(
      <h1>current path:
        {path}
      </h1>)
    writer.println(
      <ul>
        {for (file <- new File(path).listFiles) yield
        <li>
          {file.getName}
        </li>}
      </ul>)
  }

}
