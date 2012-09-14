package org.openCage.ruleofthree.property;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import org.openCage.kleinod.observe.Observer;
import org.openCage.ruleofthree.*;
import org.openCage.ruleofthree.jtothree.ThreeToJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.openCage.ruleofthree.property.ListenerControls.listenerControl;

/***** BEGIN LICENSE BLOCK *****
* BSD License (2 clause)
* Copyright (c) 2006 - 2012, Stephan Pfab
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/
public class PropStoreImpl implements Observer, PropertyStore {

    private final ThreeMap<Three>  readableProps;
    private final ThreeMap<Object> props;
    private ThreeToJ               threeToJ = new ThreeToJ();
    private final PropertyStoreRW  propertyStoreRW;

    private final static Logger LOG = Logger.getLogger( PropStoreImpl.class.getName() );


    @Inject
    public PropStoreImpl(PropertyStoreRW propertyStoreRW) {
        this.propertyStoreRW = propertyStoreRW;

        readableProps = propertyStoreRW.getThreedProps();
        props         = propertyStoreRW.getProps();

    }

    @Override
    public void add( ThreeKey key, Object prop ) {
        synchronized (this) {
            threeToProp( key, prop );
            propertyStoreRW.setDirty();
        }
    }

    private void threeToProp( ThreeKey key, Object prop ) {
        synchronized (this) {
            listenerControl(prop).add(this);
            props.put( key, prop );
        }
    }


//    public void add( String key, Property prop ) {
//        add( new ThreeKey(key), prop );
//    }


    public <T> T get( String key, Class<T> clazz ) {
        return get( new ThreeKey( key ), clazz  );
    }

    @Override
    public <T> T get( ThreeKey key, TypeLiteral<T> typeLiteral) {
        synchronized ( this ) {

            T prop =  (T)props.get(key);

            if ( prop != null ) {
                return prop;
            }

            if ( readableProps == null ) {
                return null;
            }

            Three rdlbVal  = readableProps.get(key);

            if ( rdlbVal == null ) {
                return null;
            }


            T ret = null;
            try {
                ret = threeToJ.get( typeLiteral, rdlbVal);
            } catch ( IllegalArgumentException exp ) {
                exp.printStackTrace();
                LOG.warning( "can not get prop for key " + key + " literal " + typeLiteral  );
                return null;
            }

            threeToProp( key, ret );

            return ret;
        }
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {

        return getAll( TypeLiteral.get(clazz));
    }

    @Override
    public <T> List<T> getAll(TypeLiteral<T> literal ) {

        List<T> ret = new ArrayList<T>();

        for ( ThreeKey key : readableProps.keySet() ) {
            if ( !props.containsKey(key)) {
                try {

                    ret.add( get( key, literal ));
                    // TODO better exception
                } catch ( Throwable err ) {
                    err.printStackTrace();
                    LOG.warning( "can not build a " + literal + " from " + readableProps.get(key) );
                    // can't build that class
                    // i.e. is something else
                    // i.e. don't bring to props
                }
            }
        }
        for (Map.Entry<ThreeKey,Object> prop : props.entrySet() ) {
            if ( !readableProps.containsKey( prop.getKey())) {
                if ( literal.getRawType().equals( prop.getValue().getClass())) {
                    ret.add((T) prop.getValue());
                }
            }
        }
        return ret;

    }

    @Override
    public <T> T get( ThreeKey key, Class<T> clazz ) {

        return get( key, TypeLiteral.get(clazz));
    }

    @Override
    public void call() {
        System.out.println("a change!");
        propertyStoreRW.setDirty();
    }

}
