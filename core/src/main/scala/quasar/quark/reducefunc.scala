package quasar.quark

trait ReduceFunc[A <: Type, B <: Type] {
  def apply[F[_]: ReduceOps](v: F[A]): F[B]
}
object ReduceFunc {
  def count[A <: Type]: ReduceFunc[A, Type.Int] = new ReduceFunc[A, Type.Int] {
    def apply[G[_]: ReduceOps](v: G[A]): G[Type.Int] = ReduceOps[G].count(v)
  }

  def sum[A <: Type: NumberLike]: ReduceFunc[A, A] = new ReduceFunc[A, A] {
    def apply[G[_]: ReduceOps](v: G[A]): G[A] = ReduceOps[G].sum(v)
  }

  def average[A <: Type: NumberLike]: ReduceFunc[A, Type.Dec] = new ReduceFunc[A, Type.Dec] {
    def apply[G[_]: ReduceOps](v: G[A]): G[Type.Dec] = ReduceOps[G].average(v)
  }

  def min[A <: Type]: ReduceFunc[A, A] = new ReduceFunc[A, A] {
    def apply[G[_]: ReduceOps](v: G[A]): G[A] = ReduceOps[G].min(v)
  }

  def max[A <: Type]: ReduceFunc[A, A] = new ReduceFunc[A, A] {
    def apply[G[_]: ReduceOps](v: G[A]): G[A] = ReduceOps[G].min(v)
  }

  def arbitrary[A <: Type]: ReduceFunc[A, A] = new ReduceFunc[A, A] {
    def apply[G[_]: ReduceOps](v: G[A]): G[A] = ReduceOps[G].arbitrary(v)
  }

  def first[A <: Type]: ReduceFunc[A, A] = new ReduceFunc[A, A] {
    def apply[G[_]: ReduceOps](v: G[A]): G[A] = ReduceOps[G].first(v)
  }

  def last[A <: Type]: ReduceFunc[A, A] = new ReduceFunc[A, A] {
    def apply[G[_]: ReduceOps](v: G[A]): G[A] = ReduceOps[G].last(v)
  }

  def makeArray[A <: Type]: ReduceFunc[A, Type.Arr[A]] = new ReduceFunc[A, Type.Arr[A]] {
    def apply[G[_]: ReduceOps](v: G[A]): G[Type.Arr[A]] = ReduceOps[G].makeArray(v)
  }

  def makeMap[K <: Type, V <: Type]: ReduceFunc[Type.Tuple2[K, V], Type.Map[K, V]] = new ReduceFunc[Type.Tuple2[K, V], Type.Map[K, V]] {
    def apply[G[_]: ReduceOps](v: G[Type.Tuple2[K, V]]): G[Type.Map[K, V]] = ReduceOps[G].makeMap(v)
  }
}

trait ReduceOps[F[_]] {
  def count[A <: Type](v: F[A]): F[Type.Int]

  def sum[A <: Type: NumberLike](v: F[A]): F[A]

  def average[A <: Type: NumberLike](v: F[A]): F[Type.Dec]

  def min[A <: Type](v: F[A]): F[A]

  def max[A <: Type](v: F[A]): F[A]

  def arbitrary[A <: Type](v: F[A]): F[A]

  def first[A <: Type](v: F[A]): F[A]

  def last[A <: Type](v: F[A]): F[A]

  def makeArray[A <: Type](v: F[A]): F[Type.Arr[A]]

  def makeMap[A <: Type, B <: Type](kv: F[Type.Tuple2[A, B]]): F[Type.Map[A, B]]
}
object ReduceOps {
  def apply[F[_]](implicit W: ReduceOps[F]): ReduceOps[F] = W
}
