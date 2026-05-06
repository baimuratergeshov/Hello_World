class BootstrapNavbar(private val brandName: String, private val links: List<Pair<String, String>>) {
    fun render(): String {
        val navItems = links.joinToString("") { (name, url) ->
            """<li class="nav-item"><a class="nav-link" href="$url">$name</a></li>"""
        }
        return """
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">$brandName</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">
                            $navItems
                        </ul>
                    </div>
                </div>
            </nav>
        """.trimIndent()
    }
}
