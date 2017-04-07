package quasar

package object quark {
  implicit def MakeMappingFuncTuple[A <: Type, B <: Type, C <: Type](v: MappingFunc[A, Type.Tuple2[B, C]]) =
    MappingFuncTuple(v)

  implicit def MakeMappingFuncMap[A <: Type, K <: Type, V <: Type](v: MappingFunc[A, Type.Map[K, V]]) =
    MappingFuncMap(v)

  implicit def MakeMappingFuncArray[A <: Type, E <: Type](v: MappingFunc[A, Type.Arr[E]]) =
    MappingFuncArray(v)

  implicit def MakeDatasetTuple2[K <: Type, V <: Type](v: Dataset[Type.Tuple2[K, V]]) =
    DatasetTuple2(v)

  implicit def TupleToMappingFuncTuple[A <: Type, B <: Type, C <: Type](t: (MappingFunc[A, B], MappingFunc[A, C])): MappingFunc[A, Type.Tuple2[B, C]] =
    t._1 ~ t._2

}
