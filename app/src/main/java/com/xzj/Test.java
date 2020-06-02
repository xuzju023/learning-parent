package com.xzj;


import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ThreadLocal<HashMap<Object, Object>> hashMapThreadLocal = ThreadLocal.withInitial(HashMap::new);
    }
}
