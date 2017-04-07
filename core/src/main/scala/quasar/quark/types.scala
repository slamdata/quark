package quasar.quark

import scala.math.{BigInt, BigDecimal}
import java.time.{Instant, Period, Duration, LocalDate, LocalTime}

import scalaz.\/

sealed trait Type {
  type ScalaType
}
object Type {
  final case class Unknown() extends Type {
    type ScalaType = scala.Any
  }
  final case class Timestamp() extends Type {
    type ScalaType = Instant
  }
  final case class Date() extends Type {
    type ScalaType = LocalDate
  }
  final case class Time() extends Type {
    type ScalaType = LocalTime
  }
  final case class Interval() extends Type {
    type ScalaType = Duration \/ Period
  }
  final case class Int() extends Type {
    type ScalaType = BigInt
  }
  final case class Dec() extends Type {
    type ScalaType = BigDecimal
  }
  final case class Str() extends Type {
    type ScalaType = scala.Predef.String
  }
  final case class Map[A <: Type, B <: Type](key: A, value: B) extends Type {
    type ScalaType = scala.Predef.Map[A#ScalaType, B#ScalaType]
  }
  final case class Arr[A <: Type](element: A) extends Type {
    type ScalaType = scala.Array[A]
  }
  final case class Tuple2[A <: Type, B <: Type](_1: A, _2: B) extends Type {
    type ScalaType = (A#ScalaType, B#ScalaType)
  }
  final case class Bool() extends Type {
    type ScalaType = scala.Boolean
  }
  final case class Null() extends Type {
    type ScalaType = scala.Null
  }
  type UnknownMap = Map[Unknown, Unknown]
  val UnknownMap : UnknownMap = Map(Unknown(), Unknown())

  type UnknownArr = Arr[Unknown]
  val UnknownArr : UnknownArr = Arr(Unknown())

  type Record[A <: Type] = Map[Str, A]
  type UnknownRecord = Record[Unknown]
}

sealed trait HasType[A <: Type] {
  def typeOf: A
}
object HasType {
  import Type._

  def apply[A <: Type](implicit W: HasType[A]): HasType[A] = W

  implicit val UnknownHasType: HasType[Unknown] = new HasType[Unknown] {
    def typeOf = Unknown()
  }
  implicit val IntHasType: HasType[Int] = new HasType[Int] {
    def typeOf = Int()
  }
  implicit val DecHasType: HasType[Dec] = new HasType[Dec] {
    def typeOf = Dec()
  }
  implicit def MapHasType[A <: Type: HasType, B <: Type: HasType]: HasType[Map[A, B]] = new HasType[Map[A, B]] {
    def typeOf = Map(HasType[A].typeOf, HasType[B].typeOf)
  }
  implicit def ArrHasType[A <: Type: HasType]: HasType[Arr[A]] = new HasType[Arr[A]] {
    def typeOf = Arr(HasType[A].typeOf)
  }
}


// def convert[A <: Type](v: DataDeconstruct[A]): A#ScalaType = v.deconstruct(new DataConstruct[String \/ ?] {
//   def str(v: scala.Predef.String): String \/
//
//   def int(v: scala.math.BigInt): FInt
//
//   def dec(v: scala.math.BigDecimal): FDec
//
//   def bool(v: scala.Boolean): FBool
//
//   def null0: FNull
// })
