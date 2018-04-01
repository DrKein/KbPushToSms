package kein.notitosms.base

interface BaseMvpPresenter<in V> {

    fun isDetachView(): Boolean

    fun attachView(view: V)

    fun detachView()
}
