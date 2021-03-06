package FPSimplified.Ch042


import scala.io.StdIn.readLine
import scala.util.Random
import cats.effect.IO
import cats.effect.unsafe.implicits.global

import java.util.UUID
import java.util.UUID.randomUUID


object AFunctionalGame {
  //
  //  Coin Flip: A simple FP game
  //    To get started using state in a Scala application,
  //  I’ll build a little game you can play at the command line. The application will
  //  flip a coin (a virtual coin), and as the player, your goal is to guess whether
  //    the result is heads or tails. The computer will keep track of the total number
  //  of flips and the number of correct guesses. When you start the game, you’ll see
  //    this command-line prompt: (h)eads, (t)ails, or (q)uit: _ This is how the application
  //    prompts you for your guess. Enter h for heads, t for tails, or q to quit the game.
  //  If you enter h or t , the application will flip a virtual coin, then let you know if
  //  your guess was correct or not. As an example of how it works,
    //  I just played the game and made four guesses, and the input/output of that
    //  session looks like this:
    //  (h)eads, (t)ails, or (q)uit: h
    //Flip was Heads. #Flips: 1, #Correct: 1
    //(h)eads, (t)ails, or (q)uit: h
    //Flip was Tails. #Flips: 2, #Correct: 1
    //(h)eads, (t)ails, or (q)uit: h
    //Flip was Heads. #Flips: 3, #Correct: 2
    //(h)eads, (t)ails, or (q)uit: t
    //Flip was Tails. #Flips: 4, #Correct: 3
    //(h)eads, (t)ails, or (q)uit: q
    //=== GAME OVER ===
    //#Flips: 4, #Correct: 3
    //


  // I think the "edge" of my program is actually the concept of a "round";
  // meaning at the end of each round I need to source and display the state.
  // Therefore it's ok to call unsafeRunSync on rounds.


  // TODO Refactor
  //  - group the display/print stuff
  //  - store the commands ??
  //  - store the events ??

//  final case object Start{
//    def startRound(commandStore: CommandStore): GameMessageStore = {
//      commandStore.add(PrintPrompt)
//    }
//  }
  sealed trait GameMessage

  sealed trait GameEvent extends GameMessage

  final case object PromptPrinted extends GameEvent
  final case class InputCollected(id: UUID, choice: String) extends GameEvent
  final case class FlipExecuted(id: UUID, result: String) extends GameEvent
  final case class RoundGenerated(round: Round) extends GameEvent
  final case class ResultGenerated(totalFlips: Int, correctGuesses: Int) extends GameEvent
  final case object ResultPrinted extends GameEvent   //

  // Game Commands
  sealed trait GameCommand extends GameMessage

  final case object PrintPrompt extends GameCommand
  final case object GetInput extends GameCommand
  final case object ExecuteFlip extends GameCommand
  final case class GenerateResult(rounds: RoundStore) extends GameCommand
  final case class PrintResult(result: ResultGenerated) extends GameCommand

  // Round
  final case class Round (guess: InputCollected, actual: FlipExecuted)

  final case class RoundStore(store: List[Round]) {
    def add(r: Round): RoundStore = {
            RoundStore(r :: store)
    }
  }


final object PrintUtil{

  // PromptPlayer component??
  val choicePrompt: String = {
    "(h)eads, (t)ails, or (q)uit: "
  }

  def formatResult(result: ResultGenerated): String = {
    s"#Flips: ${result.totalFlips}, #Correct: ${result.correctGuesses}"
  }

  def printItem(s: String): IO[Unit] = {
    IO.delay(println(s))
  }
}

  // GetInput
  def getInput: IO[String] = {
    IO.delay{
      val choice = readLine.trim.toUpperCase
      choice match {
        case x if (x == "T" || x == "H") => x
        case _ => "Q"
      }
    }
  }

  def generateResult(rounds: RoundStore): IO[(Int, Int)] = {
    val (total, guesses) =  rounds.store.foldLeft((0, 0)){ case (s, v) =>
        v.actual.result == v.guess.choice match {
          case true =>  (s._1 + 1, s._2 + 1)
          case false => (s._1 + 1, s._2)
        }
    }
    IO(total, guesses)
  }

  // I forgot when to use IO.delay.... :(
  def createId: IO[UUID] = {
    IO(randomUUID())
  }


  //Flip Coin
  // Not pure.  I/O??
  def doCoinFlip: IO[String] = {
    val r = new Random
    IO.delay {
      val coinFlip = r.nextInt(2)
      coinFlip match {
        case 0 => "H"
        case 1 => "T"
      }
    }
  }



  // GenerateResult
//  def compareGuessAndResult(flip: FlipExecuted, guess: InputCollected): ResultGenerated = {
//    ResultGenerated(flip.result == guess.choice)
//  }

  // Message/Data Store - where I store state transition/events
  // Do I need a Command to interact with the EventStore?
//

//  sealed trait GameMessageStore {
//    def add(message: GameMessage): GameMessageStore
//  }
//
//  final case class CommandStore(store: List[GameMessage]) extends GameMessageStore {
//    override def add(Message: GameMessage): GameMessageStore = ???
//  }
//  final case class PlayerChoiceStore(store: List[GameMessage]) extends GameMessageStore{
//    override def add(event: GameMessage): GameMessageStore = this.copy(store = store :+ event)
//  }
//  final case class FlipEventStore(store: List[GameMessage]) extends GameMessageStore {
//    override def add(event: GameMessage): GameMessageStore = this.copy(store = store :+ event)
//
//  }



  // when an e happens
  //  store the e
  // match to the next command.
// This is "accommodating" the commands it's sent - moving them from low
  // precision/coupling to high precision
  def handleCommand(c: GameCommand): IO[GameEvent] = c match {
    case PrintPrompt => for {
      _ <- PrintUtil.printItem(PrintUtil.choicePrompt)
    } yield PromptPrinted
    case GetInput  => for {
      id <- createId
      choice <- getInput
    } yield InputCollected(id, choice)
    case ExecuteFlip => for {
      id <- createId
      res <- doCoinFlip
    } yield FlipExecuted(id, res)
    case GenerateResult(x) =>  for {
      res <- generateResult(x)
      (total, guesses) = res
    } yield ResultGenerated(total, guesses)
    case PrintResult(x) => for {
      _ <- PrintUtil.printItem(PrintUtil.formatResult(x))
    } yield ResultPrinted
  }
//
//  def handleEvent(e: GameEvent, store: GameMessageStore): GameCommand = e match {
//    case PromptPrinted => ???
//    case x: InputCollected => ???//store.add(e)
//    case x: FlipExecuted => ???//store.add(e)
//    case x: ResultGenerated => ???
//    case ResultPrinted => ???
//  }

  def main(args: Array[String]): Unit = {
    def mainLoop(roundStore: RoundStore): IO[Unit] =
      for {
        _ <- handleCommand(PrintPrompt)
        choice <- handleCommand(GetInput)
        flip <- handleCommand(ExecuteFlip)
        //        This is weird... :(
        playerChoice = choice match {
          case x: InputCollected => x
        }
        flipResult = flip match {
          case x: FlipExecuted => x
        }
        // is CreateRound an Command? w/ an associated event?
        round = Round(playerChoice, flipResult)
        newRoundStore =
          if (round.guess.choice == "Q") roundStore
          else roundStore.add(round)
        result <- handleCommand(GenerateResult(newRoundStore))
        currResult = result match {
          case x: ResultGenerated => x
        }
        _ <- if (round.guess.choice == "Q") {
           handleCommand(PrintResult(currResult))
        } else {
          println(PrintUtil.formatResult(currResult))
          mainLoop(newRoundStore)
        }
      } yield ()


    mainLoop(RoundStore(Nil)).unsafeRunSync()
  }

}
