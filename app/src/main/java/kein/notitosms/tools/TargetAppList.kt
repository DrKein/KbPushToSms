package kein.notitosms.tools

class TargetAppList {
    private val targetAppList = listOf(
            "com.kbstar.kbbank",
            "com.scbank.ma30")

    fun isTargetApp(pkgName: String): Boolean {
        return targetAppList.contains(pkgName)
    }

}
