package Probs

object P09 {

  //  P09 (**) Pack consecutive duplicates of list elements into sublists.
  //    If a list contains repeated elements they should be placed in separate sublists.
  //
  //    Example:
  //
  //    scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //
  //  res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
  //

  // TODO Implement span
  //

  // Don't write primitive recursion - always use higher level 'combinators' i.e. foldLeft
  // TODO: use foldLeft
  def pack[A](l: List[A]): List[List[A]] = {
    // try making this a private method above this
    // having less things in scope, means less things can go wrong ^^
    def doPack(list: List[A], subAcc: List[A], acc: List[List[A]]): List[List[A]] = list match {
      case Nil => acc
      // code smells - implicit 'importance' to head
      // code smell - duplicated code
      // you should be using acc as the state, but here you are using list as the state
      case h :: s :: t if (h == s) => doPack(h :: t, s :: subAcc, acc)
      case h :: t => doPack(t, Nil, (h :: subAcc) :: acc)
    }

    doPack(l, Nil, Nil).reverse
  }

  def pack_v2[A](l: List[A]): List[List[A]] = {
    // try making this a private method above this
    // having less things in scope, means less things can go wrong ^^
    def doPack(list: List[A], subAcc: List[A], acc: List[List[A]]): List[List[A]] = list match {
      case Nil => acc
      // code smells - implicit 'importance' to head
      // code smell - duplicated code
      // TODO: try tupling (or nested pattern match) - when sub acc is non empty do one thing, when empty do another
      case h :: s :: t if (h == s) => doPack(h :: t, s :: subAcc, acc)
      case h :: t => doPack(t, Nil, (h :: subAcc) :: acc)
    }

    doPack(l, Nil, Nil).reverse
  }

  def pack_v3[A](l: List[A]): List[List[A]] = {
    l.foldLeft(List[List[A]](), Option.empty[A]) { case ((acc, ele), v) =>
      //        println(s"$v -->-->------ $acc")
      if (Some(v) == ele) ((v :: acc.head) :: acc.tail, Some(v))
      else (List(v) :: acc, Some(v))
    }._1.reverse
  }

  def main(args: Array[String]): Unit = {

    val test01 = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    println(pack(test01))
    println((pack_v3(test01)))
  }

}
