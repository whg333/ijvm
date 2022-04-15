package com.whg.ijvm.ch09.nativecall;

import com.whg.ijvm.ch09.runtime.RFrame;

public interface NativeMethod {
    void invoke(RFrame frame);
}
