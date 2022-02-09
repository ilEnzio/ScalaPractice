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


  def main(args: Array[String]): Unit = {

//    FileChannel.write[Int](5)
    FileChannel.write[String]("erlr's Test")

  }

}
