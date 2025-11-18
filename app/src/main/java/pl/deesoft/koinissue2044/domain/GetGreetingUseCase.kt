package pl.deesoft.koinissue2044.domain

class GetGreetingUseCase {
    fun execute(name: String): String {
        return "Hello from Koin, $name!"
    }
}
