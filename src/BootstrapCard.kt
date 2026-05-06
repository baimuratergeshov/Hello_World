class BootstrapCard(
    private val title: String,
    private val text: String,
    private val buttonText: String = "Learn More"
) {
    fun render(): String {
        return """
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">$title</h5>
                    <p class="card-text">$text</p>
                    <a href="#" class="btn btn-primary">$buttonText</a>
                </div>
            </div>
        """.trimIndent()
    }
}
