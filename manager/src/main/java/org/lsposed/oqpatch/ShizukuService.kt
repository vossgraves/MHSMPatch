package org.lsposed.oqpatch

import org.lsposed.oqpatch.IShizukuService
import kotlin.system.exitProcess

class ShizukuService : IShizukuService.Stub() {
    override fun runShellCommand(cmd: String): String {
        return try {
            val process = Runtime.getRuntime().exec(cmd)
            val output = process.inputStream.bufferedReader().readText()
            val error = process.errorStream.bufferedReader().readText()
            process.waitFor()
            output + error
        } catch (e: Exception) {
            e.stackTraceToString()
        }
    }

    override fun destroy() {
        exitProcess(0)
    }
}
