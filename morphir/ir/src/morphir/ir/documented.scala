/*
Copyright 2020 Morgan Stanley

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/


package morphir.ir

import morphir.ir.codec.documentedCodecs

object documented {

  def apply[A](doc: String, value: A): Documented[A] = Documented(doc, value)

  case class Documented[+A](doc: String, value: A) {

    @inline def toTuple: (String, A) = (doc, value)

    def map[B](f: A => B): Documented[B] =
      Documented(doc, f(value))
  }

  object Documented extends documentedCodecs.DocumentedCodec {
    def fromTuple[A](tuple: (String, A)): Documented[A] = Documented(tuple._1, tuple._2)
  }
}
