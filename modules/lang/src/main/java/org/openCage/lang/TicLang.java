package org.openCage.lang;

import org.openCage.lang.functions.F0;

public class TicLang {

    public void bind() {
        Sisl.bind( BackgroundExecutor.class, new F0<BackgroundExecutor>() {
            @Override
            public BackgroundExecutor call() {
                return new BackgroundExecutorImpl();
            }
        });

//        Sisl.bind( SingletonApp.class, new F0<SingletonApp>() {
//            @Override
//            public SingletonApp call() {
//                return new FriendlySingletonApp();
//            }
//        });
    }
}
