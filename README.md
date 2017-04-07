# Quark Analytics DSL for Quasar

Quark is an embedded DSL for the Quasar Analytics compiler.

With a syntax reminiscent of Spark, Quark allows developers to easily create complex workflows on semi-structured data that can be compiled and deployed to any target infrastructure supported by the Quasar Analytics compiler.

In contrast to Spark, Quark is specialized for semi-structured data, rather just structured data. In addition, Quark does not serialize any code and has a purely-functional, declarative interface.

On the flip side, Quark does not permit arbitrary user-defined code, but rather carefully limits the space of operations to those supported by the Quasar Analytics compiler.

# Build

# Usage

```scala
import quasar.quark._

object Example {
  def main(args: Array[String]): Unit = {
    val dataset = Dataset.load("/prod/profiles")

    val averageAge = dataset.groupBy(_.country[Str]).map(_.age[Int]).reduceBy(_.average)

    val stream = averageAge.evalTo("/prod/profiles_2")
  }
}
```
