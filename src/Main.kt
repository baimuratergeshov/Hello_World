import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer
import java.awt.Desktop
import java.net.InetSocketAddress
import java.net.URI

fun pageTemplate(title: String, navbar: BootstrapNavbar, body: String, footer: BootstrapFooter): String {
    return """
        <!DOCTYPE html>
        <html lang="fr">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>$title</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>
        <body>
            ${navbar.render()}
            $body
            ${footer.render()}
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>
        </html>
    """.trimIndent()
}

fun sendResponse(exchange: HttpExchange, html: String) {
    val bytes = html.toByteArray()
    exchange.responseHeaders.set("Content-Type", "text/html; charset=UTF-8")
    exchange.sendResponseHeaders(200, bytes.size.toLong())
    exchange.responseBody.use { it.write(bytes) }
}

fun main() {
    val port = 8080
    val server = HttpServer.create(InetSocketAddress(port), 0)

    val navbar = BootstrapNavbar(
        "Hello World App",
        listOf(
            "Accueil" to "/",
            "À propos" to "/about",
            "Contact" to "/contact"
        )
    )
    val footer = BootstrapFooter("Hello World App - Tous droits réservés", 2025)

    server.createContext("/") { exchange ->
        val cards = listOf(
            BootstrapCard("Carte 1", "Description de la carte 1", "Ouvrir"),
            BootstrapCard("Carte 2", "Description de la carte 2"),
            BootstrapCard("Carte 3", "Description de la carte 3", "Explorer"),
            BootstrapCard("Carte 4", "Description de la carte 4", "Voir"),
            BootstrapCard("Carte 5", "Description de la carte 5"),
            BootstrapCard("Carte 6", "Description de la carte 6", "Découvrir")
        )
        val container = BootstrapContainer(cards.map { it.render() })

        val body = """
            <div class="container my-4">
                <h1 class="text-center">Hello World!</h1>
            </div>
            ${container.render()}
        """.trimIndent()

        sendResponse(exchange, pageTemplate("Hello World - Kotlin", navbar, body, footer))
    }

    server.createContext("/about") { exchange ->
        val body = """
            <div class="container my-5">
                <h1 class="text-center mb-4">À propos</h1>
                <p class="lead text-center">Ce projet est un serveur web en Kotlin utilisant Bootstrap.</p>
                <p class="text-center">Il a été réalisé dans le cadre d'un TP SLAM.</p>
            </div>
        """.trimIndent()

        sendResponse(exchange, pageTemplate("À propos - Kotlin", navbar, body, footer))
    }

    server.createContext("/contact") { exchange ->
        val body = """
            <div class="container my-5">
                <h1 class="text-center mb-4">Contact</h1>
                <form class="mx-auto" style="max-width: 500px;">
                    <div class="mb-3">
                        <label for="name" class="form-label">Nom</label>
                        <input type="text" class="form-control" id="name" placeholder="Votre nom">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" placeholder="votre@email.com">
                    </div>
                    <div class="mb-3">
                        <label for="message" class="form-label">Message</label>
                        <textarea class="form-control" id="message" rows="4" placeholder="Votre message..."></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Envoyer</button>
                </form>
            </div>
        """.trimIndent()

        sendResponse(exchange, pageTemplate("Contact - Kotlin", navbar, body, footer))
    }

    server.start()
    println("Serveur démarré sur http://localhost:$port")

    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        Desktop.getDesktop().browse(URI("http://localhost:$port"))
    }
}
