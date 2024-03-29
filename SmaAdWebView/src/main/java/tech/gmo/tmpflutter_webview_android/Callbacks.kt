package tech.gmo.tmpflutter_webview_android

interface Callbacks {
    fun onTaskFinished() {
        println("Task finished")
    }

    fun onTaskCancelled() {
        println("Task cancelled")
    }
}
