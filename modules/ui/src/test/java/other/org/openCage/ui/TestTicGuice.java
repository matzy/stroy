package other.org.openCage.ui;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.Ignore;
import org.openCage.lang.Tic;
import org.openCage.localization.Localize;
import org.openCage.ui.Constants;

@Ignore
public class TestTicGuice {

    private Localize localize;// = Tic.get( Localize.class );

    @Inject
    public TestTicGuice(@Named(Constants.UI) Localize localize) {

        System.out.println(":: " + this.localize );
        this.localize = localize;

    }

}
