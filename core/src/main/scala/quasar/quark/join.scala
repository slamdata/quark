package quasar.quark

sealed trait Join[A <: Type, B <: Type] {
  def value: Join.ValueType[A, B]
}
object Join {
  type ValueType[A <: Type, B <: Type] = MappingFunc[Type.Tuple2[A, B], Type.Tuple2[A, B]] => MappingFunc[Type.Tuple2[A, B], Type.Bool]
}
final case class inner[A <: Type, B <: Type](value: Join.ValueType[A, B]) extends Join[A, B]
final case class leftOuter[A <: Type, B <: Type](value: Join.ValueType[A, B]) extends Join[A, B]
final case class rightOuter[A <: Type, B <: Type](value: Join.ValueType[A, B]) extends Join[A, B]
final case class fullOuter[A <: Type, B <: Type](value: Join.ValueType[A, B]) extends Join[A, B]
