package persistences

import models.User

import scala.collection.mutable.ListBuffer

object UserRepository {
  val users = new ListBuffer[User]()

  def add(user: User)={
    users.addOne(user)
  }

  def list = {
    users.toList
  }

  def findById(id:Int) = {
    users.find(elem => elem.id == id)
  }

  def remove(id:Int)={
    var index = -1;
    var count = 0;

    for(elem <- users){
      if(elem.id == id){
        index = count
      }
      count += 1
    }

    if(index != -1){
      users.remove(index)
    }else{
      throw new NoSuchElementException("object id not found")
    }
  }

  def update(user: User)={
    remove(user.id)
    add(user)
  }
}
