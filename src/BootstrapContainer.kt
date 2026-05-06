class BootstrapContainer(private val elements: List<String>) {
    fun render(): String {
        val cols = elements.joinToString("") {
            "<div class=\"col-12 col-sm-6 col-md-4 mb-4\">$it</div>"
        }
        return """
            <div class="container my-5">
                <div class="row">
                    $cols
                </div>
            </div>
        """.trimIndent()
    }
}
