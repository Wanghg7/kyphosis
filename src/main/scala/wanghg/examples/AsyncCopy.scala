package wanghg.examples

import java.io.File
import java.nio.ByteBuffer
import java.nio.channels.{AsynchronousFileChannel, CompletionHandler}
import java.nio.file.Paths
import java.nio.file.StandardOpenOption._

object AsyncCopy {

  private val lock = new Object()

  def main(args: Array[String]): Unit = {
    val src = AsynchronousFileChannel.open(new File("").toPath, READ)
    val dst = AsynchronousFileChannel.open(new File("").toPath, WRITE)
    val buf = ByteBuffer.allocateDirect(1024)
  }

}

