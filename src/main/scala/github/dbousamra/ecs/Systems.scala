package github.dbousamra.ecs

trait System {

  var engine: Option[Engine] = None

  def process(es: List[Entity], delta: Int): Unit
}
