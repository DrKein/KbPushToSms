package kein.notitosms.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseMvpActivity<in V: BaseMvpView, P: BaseMvpPresenter<V>>
    :AppCompatActivity(), BaseMvpContract, BaseMvpView {

    protected lateinit var presenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        if (isPresenterExists()) {
            presenter.detachView()
        }
        super.onDestroy()
    }

    override fun isPresenterExists(): Boolean {
        return this::presenter.isInitialized
    }

    protected abstract fun createPresenter(): P
}