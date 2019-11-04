package persistences

import javax.inject.Inject
import javax.inject.Singleton
import models.User
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.PostgresProfile.api._

class UserTableDef(tag: Tag) extends Table[User](tag, "user") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[Option[String]]("name")
  def email = column[String]("email")
  def pw = column[String]("pw")
  def role = column[Int]("role")

  override def * = (id, name, email, pw, role) <> ((User.apply _).tupled, User.unapply)
}

@Singleton
class UserRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  val userDao = TableQuery[UserTableDef]

  def add(user: User): Future[Int] = {
    dbConfig.db.run(userDao += user)
  }

  def update(user: User): Future[Int] = {
    dbConfig.db.run(userDao.update(user))
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(userDao.filter(_.id === id).delete)
  }

  def findById(id: Long): Future[Option[User]] = {
    dbConfig.db.run(userDao.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[User]] = {
    dbConfig.db.run(userDao.result)
  }
}
