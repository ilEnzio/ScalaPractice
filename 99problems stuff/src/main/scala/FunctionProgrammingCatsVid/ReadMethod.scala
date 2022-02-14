package FunctionProgrammingCatsVid

import scala.util.Try

object ReadMethod {


  trait Channel {
    def write[A](obj: A)(implicit enc: ByteEncoder[A]): Unit

    def read[A]()(implicit dec: ByteDecoder[A]): A
  }


  trait ByteDecoder[A]{
    def decode(bytes: Array[Byte]): Option[A]
  }

  object ByteDecoder{
    implicit val stringByteDecoder: ByteDecoder[String] = {
      instance[String](b => Try(new String(b)).toOption)
    }

    def instance[A](f: Array[Byte] => Option[A]): ByteDecoder[A] = new ByteDecoder[A] {
      override def decode(bytes: Array[Byte]): Option[A] =
        f(bytes)
    }

    def apply[A](implicit ev: ByteDecoder[A]): ByteDecoder[A] = ev
  }

  trait ByteEncoder[A]{
    def encode(a: A): Array[Byte]
  }

  object ByteEncoder {
    implicit val stringByteEncoder: ByteEncoder[String] = {
      instance[String](s => s.getBytes)
    }

    def instance[A](f: A => Array[Byte]): ByteEncoder[A] = new ByteEncoder[A] {
      override  def encode(a: A): Array[Byte] = f(a)
    }



  }
  implicit val rot3StringByteEncoder: ByteEncoder[String] = {
    ByteEncoder.instance[String](s => s.getBytes.map(b => (b+3).toByte))
  }


  def main(args: Array[String]): Unit = {

    val a: Array[Byte] = Array(98, 105, 101, 110, 32, 58, 41)

    println(ByteDecoder[String].decode(a))
  }


}
