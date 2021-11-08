object Bob {
  def response(statement: String): String = statement.trim match {
    case s if s == "" => "Fine. Be that way!"
    case s if s.endsWith("?") && s.stripSuffix("?").forall(!_.isLetter)  => "Sure."
    case s if s.forall(!_.isLetter)  => "Whatever."
    case s if s.endsWith("?") && s.toUpperCase == s => "Calm down, I know what I'm doing!"
    case s if s.toUpperCase == s => "Whoa, chill out!"
    case s if s.endsWith("?") => "Sure."
    case _ => "Whatever."
  }


}


// bobahop's solution
//object Bob {
//  def response(statement: String): String = {
//    val input = statement.trim()
//    val isShout =
//      input.exists(_.isLetter) && input.toUpperCase() == input
//    (input.isEmpty(), input.endsWith("?"), isShout) match {
//      case (_, true, true) => "Calm down, I know what I'm doing!"
//      case (_, _, true)    => "Whoa, chill out!"
//      case (_, true, _)    => "Sure."
//      case (true, _, _)    => "Fine. Be that way!"
//      case _               => "Whatever."
//    }
//  }