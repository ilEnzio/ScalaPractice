package FPSimplified.Ch042


import scala.io.StdIn.readLine
import scala.util.Random
import cats.effect.IO
import cats.effect.unsafe.implicits.global

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


  sealed trait GameEvent

  final case object PromptPrinted extends GameEvent
  final case class InputCollected(id: Int, choice: String) extends GameEvent
  final case class FlipExecuted(id: Int, result: String) extends GameEvent
  final case class ResultGenerated(wasCorrectGuess: Boolean) extends GameEvent
  final case object ResultPrinted extends GameEvent   //

  // Game Commands
  sealed trait GameCommand
  final case object PrintPrompt extends GameCommand
  final case object GetInput extends GameCommand
  final case object ExecuteFlip extends GameCommand
  final case class GenerateResult(flips: FlipEventStore, choices: PlayerChoiceStore) extends GameCommand
  final case class PrintResult(state: GameState) extends GameCommand

  final case class GameState(result: String, total: Int, correct: Int)

  // PromptPlayer component??
  // I/O
  def printPrompt: IO[Unit] = {
    IO.delay(println("(h)eads, (t)ails, or (q)uit: "))
  //  PromptPrinted
  }

  // GetInput
  // Not pure I/O??
  def getInput: IO[String] = {
    IO.delay{
      val choice = readLine.trim.toUpperCase
      choice match {
        case x if (x == "T" || x == "H") => x
        case _ => "Q"
      }
    }
  }

  // TODO generate UUID's
  // TODO This feels like two things...
//  def collectInput: InputCollected = {
//    val choice = getInput
//    choice match {
//      case x if (x == "T" || x == "H") => InputCollected(1, x)
//      case _  => InputCollected(0, "Q")
//    }
//  }

  def createId: IO[String] = {
    IO(randomUUID().toString())
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

// This is not pure though...
//  def flipCoin: FlipExecuted = {
//    val coinflip = for {
//    flip <- doCoinFlip
//    } yield flip
//
//    FlipExecuted(1, coinflip.unsafeRunSync())
//  }


  // GenerateResult
  def compareGuessAndResult(flip: FlipExecuted, guess: InputCollected): ResultGenerated = {
    ResultGenerated(flip.result == guess.choice)
  }

  // Print Results
  // This is where I have to fold through the message store
  // State Monad???

  // Message/Data Store - where I store state transition/events
  // Do I need a Command to interact with the EventStore?

  sealed trait GameEventStore {
    def add(event: GameEvent): GameEventStore
  }

  final case class PlayerChoiceStore(store: List[GameEvent]) extends GameEventStore{
    override def add(event: GameEvent): GameEventStore = this.copy(store = store :+ event)
  }
  final case class FlipEventStore(store: List[GameEvent]) extends GameEventStore {
    override def add(event: GameEvent): GameEventStore = this.copy(store = store :+ event)

  }




    def formattedResult(state: GameState): String = {
      val current = state.result match {
        case "H" => "Heads"
        case "T" => "Tails"
      }
      s"Flip was $current. #Flips: ${state.total}, #Correct: ${state.correct}"
    }


  // when an e happens
  //  store the e
  // match to the next command.

  def handleCommand(c: GameCommand): GameEvent = c match {
    case PrintPrompt => ???
    case GetInput => ???
    case ExecuteFlip => ???
    case x: GenerateResult => ???
    case x: PrintResult => ???
  }

  def handleEvent(e: GameEvent, store: GameEventStore): GameCommand = e match {
    case PromptPrinted => ???
    case x: InputCollected => ???//store.add(e)
    case x: FlipExecuted => ???//store.add(e)
    case x: ResultGenerated => ???
    case ResultPrinted => ???
  }

  def main(args: Array[String]): Unit = {
    def mainLoop(eStore: FlipEventStore): IO[Unit] =
      for {
        _ <- printPrompt
        choice <- getInput
        flipResult <- doCoinFlip
        _ <- if (choice == "Q") {
          println(s"$choice and $flipResult")
          IO(())
        } else mainLoop(eStore)
      } yield ()

    mainLoop(FlipEventStore(Nil)).unsafeRunSync()
  }

}
