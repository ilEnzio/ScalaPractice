package FunctionProgrammingCatsVid

object InstanceMethod {



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

  }


}
