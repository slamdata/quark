package quasar.quark

import quasar.ejson._
import quasar.common.SortDir

import scala.Predef.String
import matryoshka.RecursiveT
import scalaz.NonEmptyList

trait SetOps[F[_]] extends DataConstruct[F] {
  def constant[T[_[_]]: RecursiveT](e: T[EJson]): F[Type.Unknown]

  def empty[A <: Type]: F[A]

  def root: F[Type.Unknown]

  def read(path: String): F[Type.Unknown]

  def autojoin[A <: Type, B <: Type](l: F[A], r: F[B]): F[Type.Tuple2[A, B]]

  def let[A, B](v: F[A], binding: F[A] => F[B]): F[B]

  def sort[A <: Type](v: F[A], on: NonEmptyList[(MappingFunc[A, _], SortDir)]): F[A]

  def union[A <: Type](l: F[A], r: F[A]): F[A]

  def intersect[A <: Type](l: F[A], r: F[A]): F[A]

  def except[A <: Type](l: F[A], r: F[A]): F[A]

  def groupBy[A <: Type, B <: Type](v: F[A], f: MappingFunc[A, B]): F[A]

  def distinct[A <: Type](v: F[A]): F[A]

  def distinctBy[A <: Type, B <: Type](v: F[A], f: MappingFunc[A, B]): F[A]

  def filter[A <: Type, B <: Type: BoolLike](v: F[A], f: MappingFunc[A, B]): F[A]

  def take[A <: Type, B <: Type: IntLike](v: F[A], count: F[B]): F[A]

  def drop[A <: Type, B <: Type: IntLike](v: F[A], count: F[B]): F[A]

  def sample[A <: Type, B <: Type: IntLike](v: F[A], count: F[B]): F[A]

  def map[A <: Type, B <: Type](v: F[A], f: MappingFunc[A, B]): F[B]

  def reduce[A <: Type, B <: Type](v: F[A], f: ReduceFunc[A, B]): F[B]

  def flattenArrayElements[A <: Type](v: F[Type.Arr[A]]): F[A]

  def flattenArrayIndices[A <: Type](v: F[Type.Arr[A]]): F[Type.Int]

  def flattenMapKeys[K <: Type, V <: Type](v: F[Type.Map[K, V]]): F[K]

  def flattenMapValues[K <: Type, V <: Type](v: F[Type.Map[K, V]]): F[V]

  def zoomArrayElements[A <: Type](v: F[Type.Arr[A]]): F[A]

  def zoomArrayIndices[A <: Type](v: F[Type.Arr[A]]): F[Type.Int]

  def zoomMapKeys[K <: Type, V <: Type](v: F[Type.Map[K, V]]): F[K]

  def zoomMapValues[K <: Type, V <: Type](v: F[Type.Map[K, V]]): F[V]

  // def join[A <: Type, B <: Type](l: F[A], r: F[B]): Joined[F, A, B]
}

object SetOps {
  def apply[F[_]](implicit F: SetOps[F]): SetOps[F] = F
}
