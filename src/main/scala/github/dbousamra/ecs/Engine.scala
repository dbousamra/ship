package github.dbousamra.ecs

import scala.collection.mutable.{ListBuffer => MList}

class Engine {
  private var entities: MList[Entity] = MList.empty[Entity]

  private var systems: MList[System] = MList.empty[System]

  def addEntity(e: Entity): Unit = {
    entities += e
    e.world = Some(this)
  }

  def addSystem(s: System): Unit = {
    systems += s
    s.engine = Some(this)
  }

  def process(): Unit = {
    systems.foreach { system =>
      system.process(entities.toList, 1)
    }
  }

}
