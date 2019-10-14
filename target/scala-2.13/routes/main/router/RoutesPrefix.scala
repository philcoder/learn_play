// @GENERATOR:play-routes-compiler
// @SOURCE:/home/philipp/central/app/workspace_idea/learn-play/conf/routes
// @DATE:Mon Oct 14 16:41:22 BRT 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
