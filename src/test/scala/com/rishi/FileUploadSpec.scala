package com.rishi

import java.io.File

import akka.http.scaladsl.model.{StatusCodes, Multipart, ContentTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source


/**
 * Created by knol2015 on 21/5/16.
 */
class FileUploadSpec extends FlatSpec with Matchers with ScalatestRouteTest with FileUpload {
  override def testConfigSource = "akka.loglevel = WARNING"

  "File upload" should "not be able to upload file when file does not exist" in {
    val file = new File("")
    val formData = Multipart.FormData.fromFile("file", ContentTypes.`application/octet-stream`, file, 100000)
    Post(s"/user/upload/file", formData) ~> routes ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] shouldBe "Error in file uploading"
    }
  }

  it should "be able to upload file" in {
    val file = new File(getClass.getResource("/testFile").toString)
    val formData = Multipart.FormData.fromFile("file", ContentTypes.`application/octet-stream`, file, 100000)
    Post(s"/user/upload/file", formData) ~> routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] should include("File successfully uploaded")
    }
  }
}
