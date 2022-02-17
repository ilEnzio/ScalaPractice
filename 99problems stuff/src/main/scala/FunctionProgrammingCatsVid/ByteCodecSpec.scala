package FunctionProgrammingCatsVid

object ByteCodecSpec {


  trait ByteDecoder[A]{
    def decode(bytes: Array[Byte]): Option[A]
  }

  trait ByteEncoder[A]{
    def encode(a: A): Array[Byte]
  }


  trait ByteCodec[A] extends ByteDecoder[A] with ByteEncoder[A]

  trait ByteCodecLaws[A] {
    def codec: ByteCodec[A]

    def isomorphism(a: A): Boolean =
      codec.decode(codec.encode(a)) == Some(a)
  }

//  trait ByteCodecTests[A] extends Laws {
//    def laws: ByteCodecLaws[A]
//
//    def byteCodec(implicit arb: Arbitrary[A]): RuleSet = new DefaultRuleSet(
//      name = "byteCodec",
//      parent = None,
//      props = "isomorphism" -> forAll(laws.isomorphism _)
//    )
//  }


}
