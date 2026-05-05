import com.sun.net.httpserver.HttpServer
import java.awt.Desktop
import java.net.InetSocketAddress
import java.net.URI


class BootstrapCard(
    private val title: String,
    private val text: String,
    private val buttonText: String = "Learn More"
) {
    fun render(): String {
        return """
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">$title</h5>
                    <p class="card-text">$text</p>
                    <a href="#" class="btn btn-primary">$buttonText</a>
                </div>
            </div>
        """.trimIndent()
    }
}

class BootstrapNavbar(private val brandName: String, private val links: List<Pair<String, String>>) {
    fun render(): String {
        val navItems = links.joinToString("") { (name, url) ->
            """<li class="nav-item"><a class="nav-link" href="$url">$name</a></li>"""
        }

        return """
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">$brandName</a>
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

class BootstrapFooter(private val text: String, private val year: Int) {
    fun render(): String {
        return """
            <footer class="bg-dark text-white text-center py-3 mt-5">
                <div class="container">
                    <p class="mb-0">$text &copy; $year</p>
                </div>
            </footer>
        """.trimIndent()
    }
}

class BootstrapContainer(private val content: String) {
    fun render(): String {
        return """
            <div class="container my-5">
                $content
            </div>
        """.trimIndent()
    }
}

fun main() {
    val port = 8080
    val server = HttpServer.create(InetSocketAddress(port), 0)

    server.createContext("/") { exchange ->
        val navbar = BootstrapNavbar(
            "Hello World App",
            listOf(
                "Home" to "#",
                "About" to "#about",
                "Contact" to "#contact"
            )
        )

        val cards = listOf(
            BootstrapCard(
                "Carte 1",
                "description de la carte 1",
                "Ouvrir"
            ),
            BootstrapCard(
                "Carte 2",
                "description de la carte 2"
            ),
            BootstrapCard(
                "Carte 3",
                "description de la carte 3",
                "Explorer"
            )
        )

        val cardsHtml = """
            <div class="row g-4">
                ${cards.joinToString("") { "<div class=\"col-md-4\">${it.render()}</div>" }}
            </div>
        """.trimIndent()

        val container = BootstrapContainer(
            """
                <h1 class="text-center mb-5">Hello World!</h1>
                $cardsHtml
            """.trimIndent()
        )

        val footer = BootstrapFooter("Hello World App - Tous droits réservés", 2024)

        val htmlResponse = """
            <!DOCTYPE html>
            <html lang="fr">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Hello World - Kotlin</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            </head>
            <body>
                ${navbar.render()}
                ${container.render()}
                ${footer.render()}
                
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            </body>
            </html>
        """.trimIndent()

        exchange.responseHeaders.set("Content-Type", "text/html; charset=UTF-8")
        exchange.sendResponseHeaders(200, htmlResponse.toByteArray().size.toLong())
        exchange.responseBody.use { output ->
            output.write(htmlResponse.toByteArray())
        }
    }

    server.start()
    println("Serveur démarré sur http://localhost:$port")

    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        Desktop.getDesktop().browse(URI("http://localhost:$port"))
    }

    println("Appuyez sur Ctrl+C pour arrêter le serveur")
}