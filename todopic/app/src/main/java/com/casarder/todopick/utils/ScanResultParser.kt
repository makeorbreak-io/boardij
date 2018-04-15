package com.casarder.todopick.utils

/**
 * Created by ritaf_000 on 14/04/2018.
 */
class ScanResultParser () {
    companion object {
        fun divideTasks(result: String): List<String> {
            val text = result.trim()
            val tasks: List<String>

            tasks = if(text[0] == '-') {
                text.split("^-|\n-".toRegex())
            } else {
                text.split('\n')
            }

            return tasks
        }

        fun addNewTaskChar(result: String): String {
            if(result.trim().isNotBlank()){
                return "-" + result.trim().replace("\n", "\n-").replace("--", "-").toLowerCase()
            }
            return result
        }
    }
}