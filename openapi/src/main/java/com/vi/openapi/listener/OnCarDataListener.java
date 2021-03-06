package com.vi.openapi.listener;

import com.vi.openapi.bean.CarAction;
import com.vi.openapi.bean.CarStatus;
import com.vi.openapi.bean.CarVersion;

/**
 * @author Vi
 * @date 2019-07-16 14:15
 * @e-mail cfop_f2l@163.com
 */

public interface OnCarDataListener {

    void carVersion(CarVersion carVersion);

    void carStatus(CarStatus carStatus);

    void carAction(CarAction carAction);

}
