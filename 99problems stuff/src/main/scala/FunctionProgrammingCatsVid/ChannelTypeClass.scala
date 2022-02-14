package FunctionProgrammingCatsVid

import java.io.FileOutputStream
import java.nio.ByteBuffer
import scala.util.Using

object ChannelTypeClass {

  trait ByteEncoder[A]{
    def encode(a: A): Array[Byte]

  }

  object ByteEncoder{

    implicit object IntByteEncoder extends ByteEncoder[Int] {
      override def encode(a: Int): Array[Byte] = {
        val bb = ByteBuffer.allocate(4)
        bb.putInt(a)
        bb.array()
      }
    }

    implicit object StringByteEncoder extends ByteEncoder[String] {
      override def encode(s: String): Array[Byte] = {
        s.getBytes()
      }
    }
  }

  trait Channel {
    def write[A](obj: A)(implicit enc: ByteEncoder[A]): Unit
    // def write[A: ByteEncoder](obj: A): Unit
  }

  object FileChannel extends Channel {
    override def write[A](obj: A)(implicit enc: ByteEncoder[A]): Unit = {

      val bytes: Array[Byte] = enc.encode(obj)

      Using(new FileOutputStream("/Users/kellycho/Desktop/Repos/scalaTemp/ScalaPractice/99problems stuff/testwrite")) { os =>
        os.write(bytes)
        os.flush()
      }
    }
  }

//  implicit object Rot3StringByteEncoder extends ByteEncoder[String] {
//    override def encode(s: String): Array[Byte] =
//      s.getBytes.map(b => (b+3).toByte)
//  }

  case class Switch (isOn: Boolean)
  object Switch{
    implicit object SwitchByteEncoder extends ByteEncoder[Switch] {
      override def encode(s: Switch): Array[Byte] =
        if (s.isOn) "1".getBytes() else "0".getBytes()
    }
  }

  def main(args: Array[String]): Unit = {

//    FileChannel.write[Int](5)
    FileChannel.write[String]("werld Test")
    FileChannel.write[Switch](Switch(false))

  }

}
