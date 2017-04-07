package quasar.quark

trait DataConstruct[F[_]] {
  def str(v: scala.Predef.String): F[Type.Str]

  def int(v: scala.math.BigInt): F[Type.Int]

  def dec(v: scala.math.BigDecimal): F[Type.Dec]

  def bool(v: scala.Boolean): F[Type.Bool]

  def null0: F[Type.Null]
}

trait DataDeconstruct[A] {
  def deconstruct[F[_]: DataConstruct]: F[A]
}
