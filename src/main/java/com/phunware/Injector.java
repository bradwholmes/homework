package com.phunware;

import dagger.ObjectGraph;

public class Injector {
    private static final ApplicationModule applicationModule = new ApplicationModule();
    private static ObjectGraph objectGraph = ObjectGraph.create(applicationModule);

    public static void inject(Object object) {
        objectGraph.inject(object);
    }

    public static void replaceWithTestModule(Object object) throws UseOnlyInTests {
        if(BuildConfig.DEBUG) {
            objectGraph = ObjectGraph.create(object);
        } else {
            throw new UseOnlyInTests();
        }
    }

    public static class UseOnlyInTests extends Exception { }
}
