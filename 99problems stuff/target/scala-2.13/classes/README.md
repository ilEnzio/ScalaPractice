A place to store code from the onboarding process


## Resource Links:
- **Scala Tutorial:** https://www.scala-exercises.org/scala_tutorial/terms_and_types
- **Essential Scala:** https://underscore.io/books/essential-scala/
- **99 Problems:** https://github.com/Banno/wiki/blob/master/_learning/scala/module/99problems.md
- **Scala with Cats** https://drive.google.com/drive/search?q=scala
- **Scala Standard Library 2.13.3** https://www.scala-lang.org/api/2.13.3/index.html


## Progress Log
<details><summary>WEEK 01</summary>
<p>

Day 001 - Scala exercises from website.  
This was useful as exercises to test knowledge, but not very pedigogical.
The fill in the blank method just wasn't helpful for me.

End Week 1 -
Completed chapters 1-3 Essential Scala w/ exercises, 99 Problems probs 1-6 , Scala Tutorial 1-7

</p>
</details>

<details><summary>WEEK 02</summary>
<p>

### 11/22/21
Completed probs 8-12
Spent too much time on problems 7, 13.   I feel like the resource
don't provide enough material to tackle those problems. Or maybe the problems themselves could have expandeable hints.


Q's:
- P09 my implementation forces me to reverse the output.  Am I missing somethin?
- P10 not sure if I understood the contraints of the of problem:
> Use the result of problem P09 to implement the so-called run-length encoding data compression method.
- P12 My helper function seems super janky.  I'm passing the element even though
  it doesn't change for the duration of the function, I believe.
- P13 my solution doesn't work.  Need another idea...

#### TIL:
- I used a default value for an **accumulator in the public interface**
  of a function.  But really that accumulator is an implementation detail,
  so it's **bad practice** to expose it that way.  Just use the accumalator
  in the interior helper function.

### 11/23/21

Implemented alternative version of prob 07
Impl: prob 13, ch04 CatSim, ch04 Shapes(traits)

Possible Hint for Prob 7:
Look at the scala docs for signature for flatmap?

Q's:
- What is the reading goal for the week?
-

#### TIL:
- `a: List[_]`  means `a` is of type `List`. You can use this in pattern
  matching.
-

### 11/24/21

Read half Essential Sca ch04

Q's:
1. From ch04 Essential Scala - What is the cake pattern? Why was I told to avoid it?
2. ~~How do I arrange a file? Should all the traits go together?~~
3. ~~How come we are using traits rather than abstract classes?~~
4. ~~I feel like this implementation is wrong (Color) - (putting name logic here)
   but don't know why it's better to put it in Draw.apply.  Why is it better to wait
   the name? Maybe we want like with late binding we somehow want objects to be as
   lightweight as possible?~~

5. section  4.2.2.2 https://books.underscore.io/essential-scala/essential-scala.html
   Is there a convention to refer to this object that's just performing random stuff on
   objects in the file/package?  This Draw object feels misplaced or somehow random.
   maybe it's just because it's named Draw.  Having objects have an apply method makes it hard
   to understand the naming convention, if there is one.


#### TIL:
- What a functor is.  Higher kinded type. takes a type and returns a type
- That tuples can be used as a poor man's case class.  Both are product types.  So think about use cases
- Group traits and the classes that implement them together in the file
-

### 11/25/21
- Prob 08 reimpl using fold
- reimpl prob 07 [A] using fold

Peeked at Scala with Cats Ch01

Essential Scala Ch04
- Short Division
- Stop on a Dime
- Calculator
- Water, Water...

Q's
-SOLVED ~~gave up on reimpl prob 07 using fold with Any; mental block~~

### 11/26/21

- reimpl prob 07 Any with fold during code review
- prob 14 w/ foldLeft; w/ map...flatMap
- prob 15 w/ foldLeft; w/ map...flatMap

Q's

#### TIL:
- There is a big difference between fold and foldLeft.
  fold is for when the order of the accumulation doesn't matter.


</p>
</details>



<details><summary>WEEK 03</summary>
<p>

### 11/28/21
**Essential Scala. chapter 4**
- Stop on a Dime - polymorphic dispatch vs pattern matching
- Calculator - structural recursion pattern
- A List of Methods - recursive data structures exercises

Q's
- Stuck in chapter 4 because I forgot how traverse a binary tree...

#### TIL:
- Abstract Syntax Tree - defining type to represent expressions to be operated on
- Lambda functions are just instances of FunctionN; written pretty much as
  the body of the apply method.
-


### 11/29/21

Q's
- from my gross prob 17: is There a way to exit early/short circuit from a map/fold type function?
- prob 18, I"m still confused as to why I need the case keyword in order to deconstruct in pattern matching
- prob 19. feels janky used foldleft, but I still have the shortcircuit prob.
- prob 20.  Inc I have a type mismatch.

#### TIL
- `flatMap` vs `map` and `flatten`... but I need to go over it again.

### 11/30/21

Q's


#### TIL
- `case` keyword can be used to create an anonymous class??


### 12/01/21
- Solved prob 20

#### TIL
- `Option.empty[A]` is the same as` None: Option[A]`
- using `foldRight` can sometimes save me from iterating twice through the list


### 12/02/21

- Solved prob 12, 14 using fold, and for comprehensions
- Scala Exercises: standard library
- Essential Scala: chap 4 - Calculator(Suc,Fail), model Json Object INC

Q's
- ~~can't use a for comprehension~~
- I still don't understand Either despite passing the exercise.
- ~~got wrecked trying to Model the JSON object; I have new plan but
  still am a bit confused.~~
- ~~could not fully understand the Calculator solution~~
- what does this mean? 4.5.5
  ~~>In classic functional programming style we have no objects, only data without methods and functions.~~
  ^ this is an indication of the Expression Problem

#### TIL
- for comprehension are syntactic sugar for flatMap pattern



### 12/03/21
- Essential Scala ch04 - Json Model

Q's

-


#### TIL
- Definition for semi-group, Monoid
- The Expression Problem - whether to use pattern matching or Polymorphic dispatch.  One allow easier addition
  of data; the other allows easier addition of method/functions/operations.
- Type Classes - semi-group, Monoid
- One might say "Int has a monoid Instance (using plus/addition)"


</p>
</details>


<details><summary>WEEK 04</summary>
<p>

### 12/06/21

- Essential Scala ch05 - read through chapter,
  but did not complete exercises yet.
- 99problem #18 with fold, w/o ZipWithIndex

Q's

-


#### TIL

- use List.empty rather than List()
- if all you're doing is checking the condition, you don't need a pattern match, in other
  words I don't need to deconstruct.
- with tuples, consider deconstructing and naming the parts for readability
- think about how you can reuse the code , and comment stuff with maths!


### 12/07/21

- Essential Scala Ch04 JSON impl string representation of model
- [Kayak] wrote shell script to automate Submission Count/Diff
- Essential Scala Ch04 attempt Music Model impl
- Essential Scala Ch 05 Generic List - impl length, contains, getAtIdx
- Essential Scala Ch05 two versions of fold: stack and tail rec
- Ess Scala Ch05 reimpl sum, length, prod w/ custom fold

Q's
- ~~Failed on Music Model - code duplicate, etc...~~
- Adjusted music model; when build it, the api is very janky.
  This is low priority, so come back to it.
- Ch05 - Generic List how come changing End to be generic forces
  us to change it from object to class?  Hmmm.. maybe because all Ends
  are now no longer the same?
- EssScala Ch05 - Failed at tail rec generic fold:
  >Implement a generalised version of fold and rewrite
  > double in terms of it. (5.2.3.1)


#### TIL

- Variatic - any number of parameters
- Several shell commands and brief history of shell
  [which, chmod, #!, ]
-  don't model things that aren't important to model.
- Dynamic model - you run the risk having the same information expressed
  in multiple ways

### 12/08/21

- [Kayak] Socket vs WebSocket vs HTTTP;
- [Kayak] Walk through HTTP service plumping
- [Kayak] Minor improvement to Submission log script
- [Kayak] Begin Bash track on Exercism.io
- Essential Scala ch05 - reimpl double using fold,

Q's
 
-

#### TIL
- [Kayak] web sockets are stateful (as oppose to http)
- FoldRight is not tail Recrusive!  FoldLeft is Tail Recursive.
- use the appropriate parens with Tuples; the shortcut syntax has
  been deprecated
-

### 12/09/21

- Finally completed 5.3.1 Fold using.  Was having type mismatch problems.
- EssSca 05 - Impl generic binary and fold
- EssSca 05 - tree to string using fold
- EssSca 05 - generic sum type, product type Either
- EssSca 05 - impl Maybe;  fold on Maybe
- EssSca 05 - impl Fold on generic sum/Either
- EssSca 05 - use Map on linkedlist
-

Q's

- ~~In tree to string 5.3.4.1 still confused hot str => str qualifies as A=>B~~
- ~~don't understand my solution for fold on maybe - similar to above~~

#### TIL

- Placeholder Syntax for scala function literals
- Converting methods to functions; This is cool because I think
  I accidentally did this and was confused
- Finally understand Either!  lol YaaTaa!
- instead of using None, try to use Option.empty



### 12/10/21

- Essential Scala Chapter 06 - pair-programmed to impl Unique
-


Q's



#### TIL


- The general idea is a monad represents a value in some context.
- learned `C[A] <: Seq[A]`


</p>
</details>


<details><summary>WEEK 05</summary>
<p>

### 12/12/21

-    Scala W/ Cats Ch01


Q's
- ~~1.1.2  I undestand that they are using the implicit, but don't
understand why it's necessary.~~  Solved I think...
- Does the complier search the whole project for implicits? Or 
it knows from the import statements?

#### TIL

- A Scala type class is represented by a trait with
at least one type parameter.  

</p> 
</details>