/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.govukfrontend
package views
package components

import org.scalatest.{Matchers, WordSpec}

abstract class RenderHtmlSpec(exampleNames: Seq[String]) extends WordSpec with Matchers with FixturesRenderer {
  exampleNames foreach { exampleName =>
    s"$exampleName" should {
      "render the same html as the nunjucks renderer" in {
        twirlHtml(exampleName) match {
          case Left(error) => fail(error)
          case Right(html) =>
            parseAndCompressHtml(html) shouldBe parseAndCompressHtml(nunjucksHtml(exampleName))
        }
      }
    }
  }
}