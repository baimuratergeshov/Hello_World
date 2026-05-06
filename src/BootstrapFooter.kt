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
