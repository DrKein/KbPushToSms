package kein.notitosms.base

abstract class BaseMvpPresenterImp<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun isDetachView(): Boolean {
        return view == null
    }
}
