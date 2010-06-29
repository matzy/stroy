package org.openCage.sip;

import com.google.inject.Binder;
import com.google.inject.BindingBuilder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 20.06.2010
 * Time: 17:05:13
 * To change this template use File | Settings | File Templates.
 */
public class SipBinder implements Binder {

    private List<BindingBuilder> bindingBuilders = new ArrayList<BindingBuilder>();
    private Set<Module> known = new HashSet<Module>();
    private String moduleName;

    @Override
    public void install(Module module) {
        if ( !known.contains( module )) {
            known.add( module );
            String current = moduleName;
            moduleName = module.getClass().getName();
            module.configure( this );
            moduleName = current;
        } else {
            System.out.println("known module installed: " + module.getClass().getName() );
        }
    }

    @Override
    public <T> BindingBuilder<T> bind(Class<T> clazz) {
        BindingBuilder<T> builder = new BindingBuilder( clazz );
        builder.setModuleName( moduleName );
        bindingBuilders.add( builder );
        return builder;
    }

    @Override
    public <T> BindingBuilder<T> bind(TypeLiteral<T> tTypeLiteral) {
        BindingBuilder<T> builder = new BindingBuilder( tTypeLiteral );
        bindingBuilders.add( builder );
        return builder;
    }

    public List<BindingBuilder> getBuilders() {
        return bindingBuilders;
    }

    public void setCurrentModuleName(String name) {
        this.moduleName = name;
    }
}
