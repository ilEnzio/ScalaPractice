package FunctionProgrammingCatsVid

object SummonerMethod {

  trait ByteEncoder[A]{
    def encode(a: A): Array[Byte]
  }

  object ByteEncoder {
    implicit object StringByteEncoder extends ByteEncoder[String] {
      override def encode(s: String): Array[Byte] = s.getBytes
    }

    //    def summon[A](implicit ev: ByteEncoder[A]): ByteEncoder[A] = ev
    //  }
        def apply[A](implicit ev: ByteEncoder[A]): ByteEncoder[A] = ev
  }
  implicit object Rot3StringByteEncoder extends ByteEncoder[String] {
    override def encode (s: String ): Array[Byte] =
      s.getBytes.map(b => (b+3).toByte)
  }

  def main(args: Array[String]): Unit = {


     ByteEncoder.StringByteEncoder.encode("hello")
    ByteEncoder[String].encode("hello")

  }
}
