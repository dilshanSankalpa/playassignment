package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import play.api.db._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(db: Database, cc: ControllerComponents) extends AbstractController(cc) {
  /** establishing the db connection in order to execute queries */
  val connection = db.getConnection()
  val statement = connection.createStatement
  val query = "SELECT * FROM user"
  val resultset = statement.executeQuery(query)

  var i = 0
  var indexNo = new Array[Int](4)
  var firstName = new Array[String](4)
  var lastName = new Array[String](4)


  while(resultset.next()){
    indexNo(i) = resultset.getInt("index_num")
    firstName(i) = resultset.getString("first_name")
    lastName(i) = resultset.getString("last_name")
    i = i + 1
  }

    //This is a main home function that passes the data associated within the arrays to the home.scala.html/
    def index: Action[AnyContent] = Action {
      Ok(views.html.index(
        indexNo(0),indexNo(1),indexNo(2),indexNo(3),
        firstName(0),firstName(1),firstName(2),firstName(3),
        lastName(0),lastName(1),lastName(2),lastName(3)
      ))
    }

  }