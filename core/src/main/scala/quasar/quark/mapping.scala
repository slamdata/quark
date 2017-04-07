package quasar.quark

import quasar.ejson.EJson
import quasar.std.DateLib.TemporalPart
import scala.Predef._
import scala.language.dynamics
import scala.Dynamic

import matryoshka.RecursiveT

trait MappingFunc[A <: Type, B <: Type] extends Dynamic { self =>
  import MappingFunc.Case

  def apply[F[_]: MappingOps](v: F[A]): F[B]

  def >>> [C <: Type](that: MappingFunc[B, C]): MappingFunc[A, C] = new MappingFunc[A, C] {
    def apply[F[_]: MappingOps](v: F[A]): F[C] = that.apply[F](self.apply[F](v))
  }

  def typed[C <: Type: HasType]: MappingFunc[A, C] = new MappingFunc[A, C] {
    def apply[F[_]: MappingOps](v: F[A]): F[C] = MappingOps[F].typed(self(v), HasType[C].typeOf)
  }

  def := [T[_[_]]: RecursiveT](e: T[EJson]): MappingFunc[A, Type.Unknown] = new MappingFunc[A, Type.Unknown] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Unknown] = MappingOps[F].set[T, B](self(v), e)
  }

  def extractCentury(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractCentury(self.apply[F](v))
  }

  def extractDayOfMonth(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractDayOfMonth(self.apply[F](v))
  }

  def extractDecade(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractDecade(self.apply[F](v))
  }

  def extractDayOfWeek(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractDayOfWeek(self.apply[F](v))
  }

  def extractDayOfYear(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractDayOfYear(self.apply[F](v))
  }

  def extractEpoch(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractEpoch(self.apply[F](v))
  }

  def extractMillennium(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractMillennium(self.apply[F](v))
  }

  def extractMonth(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractMonth(self.apply[F](v))
  }

  def extractQuarter(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractQuarter(self.apply[F](v))
  }

  def extractTimezone(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractTimezone(self.apply[F](v))
  }

  def extractTimezoneHour(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractTimezoneHour(self.apply[F](v))
  }

  def extractTimezoneMinute(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractTimezoneMinute(self.apply[F](v))
  }

  def extractWeek(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractWeek(self.apply[F](v))
  }

  def extractYear(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractYear(self.apply[F](v))
  }

  def extractHour(implicit W: TimeLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractHour(self.apply[F](v))
  }

  def extractIsoDayOfWeek(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractIsoDayOfWeek(self.apply[F](v))
  }

  def extractIsoYear(implicit W: DateLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractIsoYear(self.apply[F](v))
  }

  def extractMicroseconds(implicit W: TimeLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractMicroseconds(self.apply[F](v))
  }

  def extractMilliseconds(implicit W: TimeLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractMilliseconds(self.apply[F](v))
  }

  def extractMinute(implicit W: TimeLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractMinute(self.apply[F](v))
  }

  def extractSecond(implicit W: TimeLike[B]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].extractSecond(self.apply[F](v))
  }

  def date(implicit W: StringLike[B]): MappingFunc[A, Type.Date] = new MappingFunc[A, Type.Date] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Date] = MappingOps[F].date(self.apply[F](v))
  }

  def time(implicit W: StringLike[B]): MappingFunc[A, Type.Time] = new MappingFunc[A, Type.Time] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Time] = MappingOps[F].time(self.apply[F](v))
  }

  def timestamp(implicit W: IntLike[B]): MappingFunc[A, Type.Timestamp] = new MappingFunc[A, Type.Timestamp] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Timestamp] = MappingOps[F].timestamp(self.apply[F](v))
  }

  def interval(implicit W: StringLike[B]): MappingFunc[A, Type.Interval] = new MappingFunc[A, Type.Interval] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Interval] = MappingOps[F].interval(self.apply[F](v))
  }

  def startOfDay(implicit W: DateLike[B]): MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].startOfDay(self.apply[F](v))
  }

  def temporalTrunc(part: TemporalPart)(implicit W: TimestampLike[B]): MappingFunc[A, Type.Timestamp] = new MappingFunc[A, Type.Timestamp] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Timestamp] = MappingOps[F].temporalTrunc(part, self.apply[F](v))
  }

  def timeOfDay(implicit W: TimestampLike[B]): MappingFunc[A, Type.Time] = new MappingFunc[A, Type.Time] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Time] = MappingOps[F].timeOfDay(self.apply[F](v))
  }

  def toTimestamp(implicit W: IntLike[B]): MappingFunc[A, Type.Timestamp] = new MappingFunc[A, Type.Timestamp] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Timestamp] = MappingOps[F].toTimestamp(self.apply[F](v))
  }

  def typeOf: MappingFunc[A, Type.Str] = new MappingFunc[A, Type.Str] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Str] = MappingOps[F].typeOf(self.apply[F](v))
  }

  def unary_- (implicit W: NumberLike[B]): MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].negate(self(v))
  }

  // Binary operators:
  def + (that: MappingFunc[A, B])(implicit W: NumberLike[B]): MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].add(self(v), that(v))
  }

  def - (that: MappingFunc[A, B])(implicit W: NumberLike[B]): MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].subtract(self(v), that(v))
  }

  def * (that: MappingFunc[A, B])(implicit W: NumberLike[B]): MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].multiply(self(v), that(v))
  }

  def / (that: MappingFunc[A, B])(implicit W: NumberLike[B]): MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].divide(self(v), that(v))
  }

  def % (that: MappingFunc[A, B])(implicit W: NumberLike[B]): MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].modulo(self(v), that(v))
  }

  def ^ (that: MappingFunc[A, B])(implicit W: NumberLike[B]): MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].power(self(v), that(v))
  }

  def === [C <: Type](that: MappingFunc[A, C]): MappingFunc[A, Type.Bool] = new MappingFunc[A, Type.Bool] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Bool] = MappingOps[F].eq(self(v), that(v))
  }

  def !== [C <: Type](that: MappingFunc[A, C]): MappingFunc[A, Type.Bool] = new MappingFunc[A, Type.Bool] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Bool] = MappingOps[F].neq(self(v), that(v))
  }

  def < [C <: Type](that: MappingFunc[A, C]): MappingFunc[A, Type.Bool] = new MappingFunc[A, Type.Bool] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Bool] = MappingOps[F].lt(self(v), that(v))
  }

  def <= [C <: Type](that: MappingFunc[A, C]): MappingFunc[A, Type.Bool] = new MappingFunc[A, Type.Bool] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Bool] = MappingOps[F].lte(self(v), that(v))
  }

  def > [C <: Type](that: MappingFunc[A, C]): MappingFunc[A, Type.Bool] = new MappingFunc[A, Type.Bool] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Bool] = MappingOps[F].gt(self(v), that(v))
  }

  def >= [C <: Type](that: MappingFunc[A, C]): MappingFunc[A, Type.Bool] = new MappingFunc[A, Type.Bool] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Bool] = MappingOps[F].gte(self(v), that(v))
  }

  def ?? (that: MappingFunc[A, B]): MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].ifUndefined(self(v), that(v))
  }

  def && (that: MappingFunc[A, B])(implicit W: BoolLike[B]): MappingFunc[A, Type.Bool] = new MappingFunc[A, Type.Bool] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Bool] = MappingOps[F].and(self(v), that(v))
  }

  def || (that: MappingFunc[A, B])(implicit W: BoolLike[B]): MappingFunc[A, Type.Bool] = new MappingFunc[A, Type.Bool] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Bool] = MappingOps[F].or(self(v), that(v))
  }

  def within(that: MappingFunc[A, Type.Arr[B]]): MappingFunc[A, Type.Bool] = new MappingFunc[A, Type.Bool] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Bool] = MappingOps[F].within(self(v), that(v))
  }

  def makeMap[C <: Type](that: MappingFunc[A, C]): MappingFunc[A, Type.Map[B, C]] = new MappingFunc[A, Type.Map[B, C]] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Map[B, C]] = MappingOps[F].makeMap(self(v), that(v))
  }

  def to[C <: Type](that: MappingFunc[A, C])(implicit W1: IntLike[B], W2: IntLike[C]): MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].range(self(v), that(v))
  }

  /**
   * Switches based on a series of cases, which are pairs of predicates and
   * values to return if the predicates evaluate to true.
   * {{{
   * v.switch(
   *   (_ > bar) -> (_ # baz)
   * )
   * }}}
   */
  def switch[C <: Type: BoolLike](f1: Case[A, B, C], fs: Case[A, B, C]*): MappingFunc[A, C] = new MappingFunc[A, C] {
    def apply[F[_]: MappingOps](v: F[A]): F[C] =
      (f1 :: fs.toList).foldRight[F[C]](MappingOps[F].undefined[C]) { (f, acc) =>
        MappingOps[F].cond(f._1(self)(v), f._2(self)(v), acc)
      }
  }

  def ~ [C <: Type](that: MappingFunc[A, C]): MappingFunc[A, Type.Tuple2[B, C]] = new MappingFunc[A, Type.Tuple2[B, C]] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Tuple2[B, C]] = MappingOps[F].entuple(self.apply(v), that.apply(v))
  }

  def selectDynamic[C <: Type: HasType](name: String): MappingFunc[A, C] =
    ???
}

object MappingFunc {
  type Case[A <: Type, B <: Type, C <: Type] = (MappingFunc[A, B] => MappingFunc[A, Type.Bool], MappingFunc[A, B] => MappingFunc[A, C])

  def now[A <: Type]: MappingFunc[A, Type.Timestamp] = new MappingFunc[A, Type.Timestamp] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Timestamp] = MappingOps[F].now
  }

  def left[A <: Type, B <: Type]: MappingFunc[Type.Tuple2[A, B], A] = new MappingFunc[Type.Tuple2[A, B], A] {
    def apply[F[_]: MappingOps](v: F[Type.Tuple2[A, B]]): F[A] = MappingOps[F].left(v)
  }

  def right[A <: Type, B <: Type]: MappingFunc[Type.Tuple2[A, B], B] = new MappingFunc[Type.Tuple2[A, B], B] {
    def apply[F[_]: MappingOps](v: F[Type.Tuple2[A, B]]): F[B] = MappingOps[F].right(v)
  }

  def constant[T[_[_]]: RecursiveT, A <: Type](e: T[EJson]): MappingFunc[A, Type.Unknown] = new MappingFunc[A, Type.Unknown] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Unknown] = MappingOps[F].constant(e)
  }

  def id[A <: Type]: MappingFunc[A, A] = new MappingFunc[A, A] {
    def apply[F[_]: MappingOps](v: F[A]): F[A] = v
  }
}

final case class MappingFuncTuple[A <: Type, B <: Type, C <: Type](self: MappingFunc[A, Type.Tuple2[B, C]]) {
  def _1 : MappingFunc[A, B] = left
  def _2 : MappingFunc[A, C] = right

  def left: MappingFunc[A, B] = new MappingFunc[A, B] {
    def apply[F[_]: MappingOps](v: F[A]): F[B] = MappingOps[F].left(self(v))
  }

  def right: MappingFunc[A, C] = new MappingFunc[A, C] {
    def apply[F[_]: MappingOps](v: F[A]): F[C] = MappingOps[F].right(self(v))
  }
}

final case class MappingFuncMap[A <: Type, K <: Type, V <: Type](self: MappingFunc[A, Type.Map[K, V]]) {
  def merge(that: MappingFunc[A, Type.Map[K, V]]): MappingFunc[A, Type.Map[K, V]] = new MappingFunc[A, Type.Map[K, V]] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Map[K, V]] = MappingOps[F].concatMaps(self(v), that(v))
  }

  def projectKey(that: MappingFunc[A, K]): MappingFunc[A, V] = new MappingFunc[A, V] {
    def apply[F[_]: MappingOps](v: F[A]): F[V] = MappingOps[F].projectKey(self(v), that(v))
  }

  def deleteKey(that: MappingFunc[A, K]): MappingFunc[A, Type.Map[K, V]] = new MappingFunc[A, Type.Map[K, V]] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Map[K, V]] = MappingOps[F].deleteKey(self(v), that(v))
  }
}

final case class MappingFuncArray[A <: Type, E <: Type](self: MappingFunc[A, Type.Arr[E]]) {
  def length: MappingFunc[A, Type.Int] = new MappingFunc[A, Type.Int] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Int] = MappingOps[F].length(self(v))
  }

  def ++ (that: MappingFunc[A, Type.Arr[E]]): MappingFunc[A, Type.Arr[E]] = new MappingFunc[A, Type.Arr[E]] {
    def apply[F[_]: MappingOps](v: F[A]): F[Type.Arr[E]] = MappingOps[F].concatArrays(self(v), that(v))
  }

  def projectIndex[C <: Type](that: MappingFunc[A, C])(implicit W2: IntLike[C]): MappingFunc[A, E] = new MappingFunc[A, E] {
    def apply[F[_]: MappingOps](v: F[A]): F[E] = MappingOps[F].projectIndex(self(v), that(v))
  }
}

trait MappingOps[F[_]] extends DataConstruct[F] {
  def now: F[Type.Timestamp]

  def undefined[A <: Type]: F[A]

  def constant[T[_[_]]: RecursiveT](e: T[EJson]): F[Type.Unknown]

  def set[T[_[_]]: RecursiveT, A](v: F[A], e: T[EJson]): F[Type.Unknown]

  def entuple[A <: Type, B <: Type](l: F[A], r: F[B]): F[Type.Tuple2[A, B]]

  def left[A <: Type, B <: Type](v: F[Type.Tuple2[A, B]]): F[A]

  def right[A <: Type, B <: Type](v: F[Type.Tuple2[A, B]]): F[B]

  def typed[A <: Type, B <: Type: HasType](v: F[A], t: B): F[B]

  // Unary mapping ops:
  def length[A <: Type](v: F[Type.Arr[A]]): F[Type.Int]

  def extractCentury[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractDayOfMonth[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractDecade[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractDayOfWeek[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractDayOfYear[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractEpoch[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractMillennium[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractMonth[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractQuarter[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractTimezone[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractTimezoneHour[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractTimezoneMinute[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractWeek[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractYear[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractHour[A <: Type: TimeLike](v: F[A]): F[Type.Int]

  def extractIsoDayOfWeek[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractIsoYear[A <: Type: DateLike](v: F[A]): F[Type.Int]

  def extractMicroseconds[A <: Type: TimeLike](v: F[A]): F[Type.Int]

  def extractMilliseconds[A <: Type: TimeLike](v: F[A]): F[Type.Int]

  def extractMinute[A <: Type: TimeLike](v: F[A]): F[Type.Int]

  def extractSecond[A <: Type: TimeLike](v: F[A]): F[Type.Int]

  def date[A <: Type: StringLike](v: F[A]): F[Type.Date]

  def time[A <: Type: StringLike](v: F[A]): F[Type.Time]

  def timestamp[A <: Type: IntLike](v: F[A]): F[Type.Timestamp]

  def interval[A <: Type: StringLike](v: F[A]): F[Type.Interval]

  def startOfDay[A <: Type: DateLike](v: F[A]): F[A]

  def temporalTrunc[A <: Type: TimestampLike](part: TemporalPart, v: F[A]): F[Type.Timestamp]

  def timeOfDay[A <: Type: TimestampLike](v: F[A]): F[Type.Time]

  def toTimestamp[A <: Type: IntLike](v: F[A]): F[Type.Timestamp]

  def typeOf[A <: Type](v: F[A]): F[Type.Str]

  def negate[A <: Type: NumberLike](v: F[A]): F[A]

  // Binary mapping ops:
  def add[A <: Type: NumberLike](l: F[A], r: F[A]): F[A]

  def multiply[A <: Type: NumberLike](l: F[A], r: F[A]): F[A]

  def subtract[A <: Type: NumberLike](l: F[A], r: F[A]): F[A]

  def divide[A <: Type: NumberLike](l: F[A], r: F[A]): F[A]

  def modulo[A <: Type: NumberLike](l: F[A], r: F[A]): F[A]

  def power[A <: Type: NumberLike](l: F[A], r: F[A]): F[A]

  def eq[A <: Type, B <: Type](l: F[A], r: F[B]): F[Type.Bool]

  def neq[A <: Type, B <: Type](l: F[A], r: F[B]): F[Type.Bool]

  def lt[A <: Type, B <: Type](l: F[A], r: F[B]): F[Type.Bool]

  def lte[A <: Type, B <: Type](l: F[A], r: F[B]): F[Type.Bool]

  def gt[A <: Type, B <: Type](l: F[A], r: F[B]): F[Type.Bool]

  def gte[A <: Type, B <: Type](l: F[A], r: F[B]): F[Type.Bool]

  def ifUndefined[A <: Type](l: F[A], r: F[A]): F[A]

  def and[A <: Type: BoolLike, B <: Type: BoolLike](l: F[A], r: F[B]): F[Type.Bool]

  def or[A <: Type: BoolLike, B <: Type: BoolLike](l: F[A], r: F[B]): F[Type.Bool]

  def within[A <: Type, B <: Type](l: F[A], r: F[Type.Arr[B]]): F[Type.Bool]

  def makeMap[A <: Type, B <: Type](key: F[A], value: F[B]): F[Type.Map[A, B]]

  def concatMaps[K <: Type, V <: Type](l: F[Type.Map[K, V]], r: F[Type.Map[K, V]]): F[Type.Map[K, V]]

  def projectIndex[A <: Type, B <: Type: IntLike](array: F[Type.Arr[A]], index: F[B]): F[A]

  def projectKey[K <: Type, V <: Type](map: F[Type.Map[K, V]], key: F[K]): F[V]

  def deleteKey[K <: Type, V <: Type](map: F[Type.Map[K, V]], key: F[K]): F[Type.Map[K, V]]

  def concatArrays[E <: Type](l: F[Type.Arr[E]], r: F[Type.Arr[E]]): F[Type.Arr[E]]

  def range[A <: Type: IntLike, B <: Type: IntLike](from: F[A], to: F[B]): F[Type.Int]

  // Ternary mapping ops:
  def between[A <: Type](v: F[A], start: F[A], end: F[A]): F[Type.Bool]

  def cond[A <: Type: BoolLike, B <: Type](p: F[A], ifTrue: F[B], ifFalse: F[B]): F[B]

  def search[A <: Type: StringLike, B <: Type: StringLike, C <: Type: BoolLike](str: F[A], pat: F[B], caseInsensitive: F[C]): F[A]

  def substring[A <: Type: StringLike, B <: Type: IntLike, C <: Type: IntLike](str: F[A], from: F[B], to: F[C]): F[A]

  def guard[A <: Type, B <: Type, C <: Type](v: F[A], tpe: B, yesMatch: F[C], noMatch: F[C]): F[C]
}

object MappingOps {
  def apply[F[_]](implicit F: MappingOps[F]): MappingOps[F] = F
}
