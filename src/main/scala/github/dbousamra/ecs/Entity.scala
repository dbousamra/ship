package github.dbousamra.ecs

import java.util.UUID

import scala.collection.mutable.{ListBuffer => MList}
import scala.reflect.ClassTag

case class Entity(
  components: MList[Component] = MList.empty[Component]
) {
  var world: Option[Engine] = None
  val id: String = UUID.randomUUID().toString

  def addComponent(c: Component): Unit = {
    components += c
  }

  def getComponent[A <: Component: ClassTag]: Option[A] = {
    components
      .find {
        case component: A => true
        case _            => false
      }
      .map(_.asInstanceOf[A])
  }
}

object Entity {
  def create(components: Component*): Entity = {
    Entity(MList(components: _*))
  }
}
