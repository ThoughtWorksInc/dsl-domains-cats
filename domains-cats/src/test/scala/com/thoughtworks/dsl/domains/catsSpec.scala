package com.thoughtworks.dsl.domains

import _root_.cats.instances.stream._
import com.thoughtworks.dsl.keywords.{Monadic, Shift, Yield}
import com.thoughtworks.dsl.domains.cats._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

/**
  * @author 杨博 (Yang Bo)
  */
class catsSpec extends AnyFreeSpec with Matchers {

  "Given a continuation that uses Yield and Monadic expressions" - {

    def asyncFunction: (Unit => Stream[String]) => Stream[String] = _ {
      !Yield("Entering asyncFunction")
      val subThreadId = !Monadic(Stream(0, 1))
      !Yield(s"Fork sub-thread $subThreadId")
      !Yield("Leaving asyncFunction")
    }

    "When create a generator that contains Yield, Shift, and Monadic expressions" - {

      def generator: Stream[String] = {
        !Yield("Entering generator")
        val threadId = !Monadic(Stream(0, 1))
        !Yield(s"Fork thread $threadId")
        !Shift(asyncFunction)
        Stream("Leaving generator")
      }

      "Then the generator should contains yield values" in {
        generator should be(
          Seq(
            /**/ "Entering generator",
            /****/ "Fork thread 0",
            /******/ "Entering asyncFunction",
            /********/ "Fork sub-thread 0",
            /**********/ "Leaving asyncFunction",
            /**********/ "Leaving generator",
            /********/ "Fork sub-thread 1",
            /**********/ "Leaving asyncFunction",
            /**********/ "Leaving generator",
            /****/ "Fork thread 1",
            /******/ "Entering asyncFunction",
            /********/ "Fork sub-thread 0",
            /**********/ "Leaving asyncFunction",
            /**********/ "Leaving generator",
            /********/ "Fork sub-thread 1",
            /**********/ "Leaving asyncFunction",
            /**********/ "Leaving generator"
          ))
      }

    }

  }

}
