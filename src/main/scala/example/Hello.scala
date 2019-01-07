package example

import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

object Hello extends App {

  val foo: Foo = Qux(13, Some(14.0))

  val json = foo.asJson.noSpaces
  println(json)

  val decodedFoo = decode[Foo](json).toOption
  println(decodedFoo)

  val large = Large(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26)

  val largeJson = large.asJson.mapObject(("other", large.other.asJson) +: _).noSpaces
  println(largeJson)

  val decodedLarge = decode[Large](largeJson).toOption
  println(decodedLarge)
}

sealed trait Foo

case class Bar(xs: Vector[String]) extends Foo

case class Qux(i: Int, d: Option[Double]) extends Foo

case class Large(
                  a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int,
                  h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int,
                  o: Int, p: Int, q: Int, r: Int, s: Int, t: Int,
                  u: Int, v: Int, w: Int, x: Int, y: Int, z: Int
                ) {
  val other: Int = a + b
}
