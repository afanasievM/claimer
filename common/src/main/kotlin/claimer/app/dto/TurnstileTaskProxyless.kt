package claimer.app.dto

data class TurnstileTaskProxylessCreateTaskRequest(val clientKey: String, val task: Task)

data class Task(val type: String, val websiteURL: String, val websiteKey: String)

data class TurnstileTaskProxylessCreateTaskResponse(val errorId: Int, val taskId: Long)

data class TwoCapchGetTaskResultRequest(val clientKey: String, val taskId: Long)
data class TwoCapchGetTaskResultResponse(
    val errorId: Int,
    val status: String?,
    val errorCode: String?,
    val errorDescription: String?,
    val solution: TurnstileTaskSolution?,
    val cost: String?,
    val ip: String?,
    val createTime: Long?,
    val endTime: Long?,
    val solveCount: Int?
) {
    data class TurnstileTaskSolution(val token: String, val userAgent: String)
}
