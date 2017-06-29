package wanghg.examples

import java.io.File
import java.nio.ByteBuffer
import java.nio.channels.{AsynchronousFileChannel, CompletionHandler}
import java.nio.file.StandardOpenOption._

object AsyncCopy {

  private val lock = new Object()

  val handler: CompletionHandler[Integer, Status] = new CompletionHandler[Integer, Status] {
    override def failed(exc: Throwable, status: Status): Unit = ???

    override def completed(n: Integer, status: Status): Unit = {
      printf("thread id: %d\n", Thread.currentThread().getId())
      if (n == -1) {
        lock.synchronized {
          lock.notify()
        }
      } else if (status.op) {
        // read completed
        printf("%20s: %10d %10d\n", "read completed", n, status.nread)
        status.buf.flip()
        status.dst.write(status.buf, status.nwritten, status.copy(op = false, nread = status.nread + n), handler)
      } else {
        // write completed
        status.buf.compact()
        printf("%20s: %10d %10d\n", "write completed", n, status.nwritten)
        status.src.read(status.buf, status.nread, status.copy(op = true, nwritten = status.nwritten + n), handler)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val src = AsynchronousFileChannel.open(new File("/Users/wanghg/Desktop/src.iso").toPath, READ)
    val dst = AsynchronousFileChannel.open(new File("/Users/wanghg/Desktop/dst.iso").toPath, WRITE, CREATE, TRUNCATE_EXISTING)
    println(src.getClass)
    println(dst.getClass)
    val buf = ByteBuffer.allocateDirect(1024 * 1024)
    src.read(buf, 0, Status(src, dst, buf, true, 0, 0), handler)
    lock.synchronized {
      lock.wait()
    }
  }

}

case class Status(src: AsynchronousFileChannel, dst: AsynchronousFileChannel, buf: ByteBuffer,
                  op: Boolean, nread: Int, nwritten: Int)

