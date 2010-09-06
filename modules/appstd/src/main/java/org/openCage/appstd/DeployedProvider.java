package org.openCage.appstd;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.openCage.artig.GetDeployed;
import org.openCage.artig.stjx.Deployed;

public class DeployedProvider extends GetDeployed implements Provider<Deployed> {

    @Inject
    public DeployedProvider( @Named("APPSTD") String name ) {
        super( name );
    }
}

