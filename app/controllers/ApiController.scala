package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import models._

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ApiController @Inject()(userDao: UsersDAO) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def sample = Action {
    val json : JsValue = Json.parse("""
      {
        "result" : "200",
        "message" : "success"
      }
    """)
    Ok(json)
  }

  def slickSample = Action {
    val user: User = User(None, "abc")
    userDao.insert(user)
    val users : Seq[User] = Await.result(userDao.all, Duration.Inf)
    Ok(Json.toJson(users.map(u => Json.obj("id" -> u.id, "name" -> u.name))))
  }

}
