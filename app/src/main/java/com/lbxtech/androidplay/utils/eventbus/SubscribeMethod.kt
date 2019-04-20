package com.lbxtech.androidplay.utils.eventbus

import java.lang.reflect.Method

class SubscribeMethod(var method: Method, var threadMode: ThreadMode, var type: Class<*>)
