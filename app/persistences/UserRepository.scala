package persistences

import anorm._
import anorm.SqlParser.{get, str}
import javax.inject.Inject
import models.User
import play.api.db.DBApi

import scala.concurrent.Future


/**
 * Helper for pagination.
 */
case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

@javax.inject.Singleton
class UserRepository @Inject()(dbapi: DBApi)(implicit ec: DatabaseExecutionContext) {

    private val db = dbapi.database("default")

    /**
     * Parse a User from a ResultSet
     */
    private val simple = {
      get[Option[Long]]("user.id") ~ get[String]("user.name") ~ get[String]("user.email") ~ get[String]("user.pw") ~ get[String]("user.role") map {
        case id ~ name ~ email ~ pw ~ role => User(id,name,email,pw,role.toInt)
      }
    }

    /**
     * Select a user by id.
     *
     * @param id Id of the user to select.
   */
    def findById(id: Long): Future[Option[User]] = Future {
      db.withConnection { implicit connection =>
        SQL"select * from user where id = $id".as(simple.singleOpt)
      }
    }(ec)

    def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Future[Page[(User)]] = Future {
      val offset = pageSize * page

      db.withConnection { implicit connection =>
        val users = SQL"""
                      select * from user
                    """.as(simple.*)
        Page(users, page, offset, users.length)
      }
    }(ec)

    /**
     * Insert a new user.
     *
     * @param user The user values.
     */
    def insert(user: User): Future[Option[Long]] = Future {
      db.withConnection { implicit connection =>
        SQL("""
            insert into user values (
              (nextval('user_id_seq')),
              {name}, {email}, {pw}, {role}
            )
        """).bind(user).executeInsert()
      }
    }(ec)

    /**
     * Delete a user.
     *
     * @param id Id of the user to delete.
     */
    def delete(id: Long) = Future {
      db.withConnection { implicit connection =>
        SQL"delete from user where id = ${id}".executeUpdate()
      }
    }(ec)
}
