package com.hmc.mynews.base;

/**
 * Created by hmcxunxi on 2017/3/18.
 */

public interface BasePresenter<T extends BaseView> {

    /**
     * 关联view
     * @param view
     */
    void attachView(T view);

    /**
     * 解除关联
     */
    void detachView();
}
