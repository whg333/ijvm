package com.whg.ijvm.ch11.nativecall;

import com.whg.ijvm.ch11.runtime.RFrame;

public interface NativeMethod {
    void invoke(RFrame frame);
}
