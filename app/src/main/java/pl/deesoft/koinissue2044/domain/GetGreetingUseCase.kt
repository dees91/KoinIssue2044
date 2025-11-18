package pl.deesoft.koinissue2044.domain

class GetGreetingUseCase {
    private var callCount = 0

    fun execute(name: String): String {
        callCount++
        return "Hello from Koin, $name! (Call count: $callCount)"
    }

    fun getCallCount(): Int = callCount
}
