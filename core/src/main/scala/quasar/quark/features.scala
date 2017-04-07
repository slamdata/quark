package quasar.quark

sealed trait BoolLike[A]
object BoolLike {
  implicit val BoolBoolLike = new BoolLike[Type.Bool] { }
}

sealed trait DateLike[A]
object DateLike {
  implicit val DateDateLike       = new DateLike[Type.Date] { }
  implicit val TimestampDateLike  = new DateLike[Type.Timestamp] { }
}

sealed trait TimeLike[A]
object TimeLike {
  implicit val TimeTimeLike       = new TimeLike[Type.Time] { }
  implicit val TimestampTimeLike  = new TimeLike[Type.Timestamp] { }
}

sealed trait TimestampLike[A]
object TimestmapLike {
  implicit val TimestampTimestampLike = new TimestampLike[Type.Timestamp] { }
}

sealed trait NumberLike[A]
object NumberLike {
  implicit val IntNumberLike = new NumberLike[Type.Int] { }
  implicit val DecNumberLike = new NumberLike[Type.Dec] { }
}

sealed trait StringLike[A]
object StringLike {
  implicit val StringStringLike = new StringLike[Type.Str] { }
}

sealed trait IntLike[A]
object IntLike {
  implicit val IntIntLike = new IntLike[Type.Int] { }
}

sealed trait DecLike[A]
object DecLike {
  implicit val DecDecLike = new DecLike[Type.Dec] { }
}
