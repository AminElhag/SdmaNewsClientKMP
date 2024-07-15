package networking.util

enum class NetworkError(val message: String) : Error {
    REQUEST_TIMEOUT("Request time out"),
    UNAUTHORIZED("Unauthorized"),
    CONFLICT("Conflict"),
    TOO_MANY_REQUESTS("Too many requests"),
    NO_INTERNET("No internet"),
    PAYLOAD_TOO_LARGE("Payload too large"),
    SERVER_ERROR("server error"),
    SERIALIZATION("Serialization"),
    UNKNOWN("unknown");
}