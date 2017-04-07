package quasar.quark

sealed trait Sort[A <: Type] {
  def value: MappingFunc[A, A] => MappingFunc[A, _]
}
final case class ascending[A <: Type](value: MappingFunc[A, A] => MappingFunc[A, _]) extends Sort[A]
final case class descending[A <: Type](value: MappingFunc[A, A] => MappingFunc[A, _]) extends Sort[A]
