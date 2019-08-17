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

package uk.gov.hmrc.govukfrontend.views.components

import play.api.libs.functional.syntax._
import play.api.libs.json._
import uk.gov.hmrc.govukfrontend.views.html.components._

class tagSpec
    extends RenderHtmlSpec(
      Seq(
        "tag-default",
        "tag-inactive"
      )
    ) {
  "tag" should {
    "render the default example with strong element and text" in {
      val component = Tag.apply()(HtmlContent("alpha")).select(".govuk-tag")

      component.first().tagName() shouldBe "strong"
      component.first().text()    shouldBe "alpha"
    }
  }

  override implicit val reads: Reads[HtmlString] = (
    readsContents and
      (__ \ "classes").readWithDefault[String]("") and
      (__ \ "attributes").readWithDefault[Map[String, String]](Map.empty)
  )((contents, classes, attributes) => HtmlString(Tag.apply(classes, attributes)(contents)))
}