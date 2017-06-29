package wanghg.examples

import java.nio.ByteBuffer

/**
  * Created by wanghg on 28/06/2017.
  */
object ByteBufferDemo {

  def main(args: Array[String]): Unit = {
    //
    val buf = ByteBuffer.allocate(1024)
    println(bufStatus(buf))
    //
    buf.put("hello".getBytes("UTF-8"))
    println(bufStatus(buf))
    //
    buf.put("scala".getBytes("UTF-8"))
    println(bufStatus(buf))
    //
    buf.flip()
    println(bufStatus(buf))
    //
    val bytes = new Array[Byte](5)
    buf.get(bytes)
    println(bufStatus(buf))
    //
    buf.compact()
    println(bufStatus(buf))
    //
    buf.put("Java".getBytes("UTF-8"))
    println(bufStatus(buf))
  }

  def bufStatus(buf: ByteBuffer) = {
    val pos = buf.position()
    val lim = buf.limit()
    val cap = buf.capacity()
    String.format("pos: %4d, lim: %10d, cap: %10d", pos: Integer, lim: Integer, cap: Integer)
  }
}
