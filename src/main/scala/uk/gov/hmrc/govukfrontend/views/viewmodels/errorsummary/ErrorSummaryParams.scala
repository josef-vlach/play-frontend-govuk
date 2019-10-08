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

package uk.gov.hmrc.govukfrontend.views.viewmodels.errorsummary

import play.api.libs.functional.syntax._
import play.api.libs.json._
import uk.gov.hmrc.govukfrontend.views.viewmodels.content.{Content, Empty}

case class ErrorSummaryParams(
  errorList: Seq[ErrorLink]       = Nil,
  classes: String                 = "",
  attributes: Map[String, String] = Map.empty,
  title: Content,
  description: Content = Empty
)

object ErrorSummaryParams {

  implicit val reads: Reads[ErrorSummaryParams] = (
    (__ \ "errorList").readWithDefault[Seq[ErrorLink]](Nil) and
      (__ \ "classes").readWithDefault[String]("") and
      (__ \ "attributes").readWithDefault[Map[String, String]](Map.empty) and
      Content.readsHtmlOrText((__ \ "titleHtml"), (__ \ "titleText")) and
      Content.readsHtmlOrText((__ \ "descriptionHtml"), (__ \ "descriptionText"))
  )(ErrorSummaryParams.apply _)

  implicit val writes = (
    (__ \ "errorList").write[Seq[ErrorLink]] and
      (__ \ "classes").write[String] and
      (__ \ "attributes").write[Map[String, String]] and
      Content.writesContent("titleHtml", "titleText") and
      Content.writesContent("descriptionHtml", "descriptionText")
  )(unlift(ErrorSummaryParams.unapply))
}