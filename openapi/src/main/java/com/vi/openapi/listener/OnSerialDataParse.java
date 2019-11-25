package com.vi.openapi.listener;

import com.vi.openapi.listener.OnVioDataListener;

import java.util.List;

/**
 * @author Vi
 * @date 2019/2/12 11:38 AM
 * @e-mail cfop_f2l@163.com
 */

public interface OnSerialDataParse {

    void parseData(String data, List<OnVioDataListener> mVioDataListener);

}
