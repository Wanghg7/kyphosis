package wanghg.examples

import java.nio.ByteBuffer

import scala.util.Random


object CopyBytes {

  def main(args: Array[String]): Unit = {
    val SIZE = 1040
    val src = genBytes(SIZE)
    val dst = new Array[Byte](SIZE)
    copy(src, dst)
    if (check(src, dst)) {
      println("Passed")
    } else {
      println("Failed")
    }
  }

  def copy(src: Array[Byte], dst: Array[Byte]): Unit = {
    val buf = ByteBuffer.allocate(32)

    def loop(nread: Int, nwritten: Int): Unit = {
      if (nread >= src.length || nwritten >= dst.length) {
        ()
      } else {
        // write to buffer
        var tmp = buf.position()
        System.err.println("  " + ByteBufferDemo.bufStatus(buf))
        buf.put(src, nread, Math.min(buf.remaining, src.length - nread))
        System.err.println("⇒ " + ByteBufferDemo.bufStatus(buf))
        val nread2 = nread + buf.position() - tmp
        // read from buffer
        buf.flip()
        tmp = buf.position()
        buf.get(dst, nwritten, buf.remaining)
        val nwritten2 = nwritten + buf.position() - tmp
        buf.compact()
        //
        loop(nread2, nwritten2)
      }
    }

    loop(0, 0)
  }

  def genBytes(size: Int): Array[Byte] = {
    val bytes = new Array[Byte](size)

    def loop(bytes: Array[Byte], n: Int, limit: Int): Array[Byte] = {
      if (n < limit) {
        bytes(n) = Random.nextInt(256).asInstanceOf[Byte]
        loop(bytes, n + 1, limit)
      } else {
        bytes
      }
    }

    loop(bytes, 0, size)
  }

  def check(xs: Array[Byte], ys: Array[Byte]): Boolean = {
    val len = xs.length
    if (len != ys.length) {
      System.err.println("check: length not match")
      false
    } else {
      def loop(i: Int): Boolean = {
        if (i >= len) {
          true
        } else if (xs(i) != ys(i)) {
          System.err.printf("check: element not match: %d\n", i.asInstanceOf[Integer])
          false
        } else {
          loop(i + 1)
        }
      }

      loop(0)
    }
  }
}
