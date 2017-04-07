package quasar.quark

import scala.Predef.String
import scala.Int
import scala.Predef._

/**
 * A dataset is a set of values loaded from the file system. Datasets may be
 * mapped, filtered, sorted, joined, and otherwise transformed.
 */
sealed trait Dataset[A <: Type] { self =>
  def apply[F[_]: SetOps]: F[A]

  /**
   * Performs a runtime type filter to select for the subset of values in the
   * dataset that conform to the specified type.
   */
  def typed[B <: Type: HasType]: Dataset[B] = map(_.typed[B])

  /**
   * Distincts the dataset by the values inside the datset.
   */
  def distinct: Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].distinct(self.apply)
  }

  /**
   * Distincts the dataset by the specified projection on values of the dataset.
   */
  def distinctBy[B <: Type](f: MappingFunc[A, A] => MappingFunc[A, B]): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].distinctBy(self.apply, f(MappingFunc.id[A]))
  }

  /**
   * Groups this dataset by the specified projection on the values of the
   * dataset. Set-level operations applied to a grouped dataset are applied
   * within each group.
   */
  def groupBy[B <: Type](f: MappingFunc[A, A] => MappingFunc[A, B]): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].groupBy(self.apply, f(MappingFunc.id[A]))
  }

  /**
   * Joins this dataset with the specified dataset, according to the specified
   * join type and predicate.
   */
  def join[B <: Type](that: Dataset[B])(j: Join[A, B]): Dataset[Type.Tuple2[A, B]] = new Dataset[Type.Tuple2[A, B]] {
    def apply[F[_]: SetOps]: F[Type.Tuple2[A, B]] = ???
  }

  /**
   * Zips this dataset and the specified dataset together according to the
   * natural join of the two datasets.
   */
  def zip[B <: Type](that: Dataset[B]): Dataset[Type.Tuple2[A, B]] = new Dataset[Type.Tuple2[A, B]] {
    def apply[F[_]: SetOps]: F[Type.Tuple2[A, B]] = SetOps[F].autojoin(self.apply, that.apply[F])
  }

  /**
   * Maps over every value in this dataset, applying the specified
   * transformation to each element.
   */
  def map[B <: Type](f: MappingFunc[A, A] => MappingFunc[A, B]): Dataset[B] = new Dataset[B] {
    def apply[F[_]: SetOps]: F[B] = SetOps[F].map(self.apply[F], f(MappingFunc.id[A]))
  }

  /**
   * Maps over every value in the dataset, applying the specified array-
   * generating transformation to each element, and flattening the array.
   */
  def flatMap[B <: Type](f: MappingFunc[A, A] => MappingFunc[A, Type.Arr[B]]): Dataset[B] = new Dataset[B] {
    def apply[F[_]: SetOps]: F[B] = SetOps[F].flattenArrayElements(SetOps[F].map(self.apply[F], f(MappingFunc.id[A])))
  }

  /**
   * Zips this dataset together with the specified dataset, by using the self-
   * join of the two datasets.
   */
  def zipWith[B <: Type, C <: Type](that: Dataset[B])(f: MappingFunc[Type.Tuple2[A, B], Type.Tuple2[A, B]] => MappingFunc[Type.Tuple2[A, B], C]): Dataset[C] = zip(that).map(f)

  /**
   * Unions this dataset together with the specified dataset, retaining all
   * duplicates.
   */
  def union(that: Dataset[A]): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].union(self.apply, that.apply)
  }

  /**
   * Intersects this dataset together with the specified dataset, retaining only
   * values that are a member of both datasets.
   */
  def intersect(that: Dataset[A]): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].intersect(self.apply, that.apply)
  }

  /**
   * Removes all values from this dataset that are values of the specified
   * dataset.
   */
  def except(that: Dataset[A]): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].except(self.apply, that.apply)
  }

  /**
   * Filters the dataset by the specified predicate, removing values that do
   * not satisfy the predicate.
   */
  def filter(f: MappingFunc[A, A] => MappingFunc[A, Type.Bool]): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].filter[A, Type.Bool](self.apply, f(MappingFunc.id[A]))
  }

  /**
   * Takes the first `n` values from the dataset.
   */
  def take(n: Int): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].take(self.apply, SetOps[F].int(n))
  }

  /**
   * Drops the first `n` values from the dataset.
   */
  def drop(n: Int): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].drop(self.apply, SetOps[F].int(n))
  }

  /**
   * Randomly samples `n` values from the dataset.
   */
  def sample(n: Int): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].sample(self.apply, SetOps[F].int(n))
  }

  /**
   * sortBy(ascending(_.length), descending(_.age))
   */
  def sortBy(v1: Sort[A], vs: Sort[A]*): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = ???
  }

  /**
   * Counts the values in the dataset.
   */
  def count: Dataset[Type.Int] = new Dataset[Type.Int] {
    def apply[F[_]: SetOps]: F[Type.Int] = SetOps[F].reduce(self.apply, ReduceFunc.count)
  }

  /**
   * Sums all the values in the dataset.
   */
  def sum(implicit W: NumberLike[A]): Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].reduce(self.apply, ReduceFunc.sum)
  }

  /**
   * Computes the average of all the values in the dataset.
   */
  def average(implicit W: NumberLike[A]): Dataset[Type.Dec] = new Dataset[Type.Dec] {
    def apply[F[_]: SetOps]: F[Type.Dec] = SetOps[F].reduce(self.apply, ReduceFunc.average)
  }

  /**
   * Selects the minimum value in the dataset.
   */
  def min: Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].reduce(self.apply, ReduceFunc.min)
  }

  /**
   * Selects the maximum value in the dataset.
   */
  def max: Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].reduce(self.apply, ReduceFunc.max)
  }

  /**
   * Selects the first value in the dataset.
   */
  def first: Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].reduce(self.apply, ReduceFunc.first)
  }

  /**
   * Selects the last value in the dataset.
   */
  def last: Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].reduce(self.apply, ReduceFunc.last)
  }

  /**
   * Selects an arbitrary value in the dataset.
   */
  def arbitrary: Dataset[A] = new Dataset[A] {
    def apply[F[_]: SetOps]: F[A] = SetOps[F].reduce(self.apply, ReduceFunc.arbitrary)
  }

  /**
   * Packs the dataset into an array.
   */
  def makeArray: Dataset[Type.Arr[A]] = new Dataset[Type.Arr[A]] {
    def apply[F[_]: SetOps]: F[Type.Arr[A]] = SetOps[F].reduce(self.apply, ReduceFunc.makeArray)
  }

  /**
   * Combines this dataset (the keys) with the specified dataset (the values)
   * to form a map, using the natural join of the two datasets.
   */
  def makeMap[B <: Type](that: Dataset[B]): Dataset[Type.Map[A, B]] = DatasetTuple2(zip(that)).makeMap
}

object Dataset {
  /**
   * Creates an empty dataset of any type that contains no values.
   */
  def empty[A <: Type]: Dataset[A] = new Dataset[A] {
    def apply[F[_]](implicit F: SetOps[F]): F[A] = F.empty[A]
  }

  /**
   * Creates a dataset containing the root of the Quasar file system.
   */
  def root: Dataset[Type.Unknown] = new Dataset[Type.Unknown] {
    def apply[F[_]](implicit F: SetOps[F]) = F.root
  }

  /**
   * Loads the data at a specified path in the Quasar file system.
   */
  def load(path: String): Dataset[Type.Unknown] = new Dataset[Type.Unknown] {
    def apply[F[_]](implicit F: SetOps[F]) = F.read(path)
  }
}

final case class DatasetTuple2[K <: Type, V <: Type](self: Dataset[Type.Tuple2[K, V]]) {
  /**
   * Packs the datset into a map.
   */
  def makeMap: Dataset[Type.Map[K, V]] = new Dataset[Type.Map[K, V]] {
    def apply[F[_]: SetOps]: F[Type.Map[K, V]] = SetOps[F].reduce(self.apply, ReduceFunc.makeMap)
  }
}
