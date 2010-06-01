package org.openCage.lang;

import org.openCage.lang.functions.F0;

public class TicLang {

    public void bind() {
        Tic.bind( BackgroundExecutor.class, new F0<BackgroundExecutor>() {
            @Override
            public BackgroundExecutor call() {
                return new BackgroundExecutorImpl();
            }
        });

//        Tic.bind( SingletonApp.class, new F0<SingletonApp>() {
//            @Override
//            public SingletonApp call() {
//                return new FriendlySingletonApp();
//            }
//        });
    }
}
