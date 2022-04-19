package com.whg.ijvm.ch10.nativecall;

import com.whg.ijvm.ch10.runtime.RFrame;

public interface NativeMethod {
    void invoke(RFrame frame);
}
