package juliewww.compose.multiplatform.openai.client

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform