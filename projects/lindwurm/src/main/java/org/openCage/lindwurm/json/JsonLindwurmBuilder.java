package org.openCage.lindwurm.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openCage.kleinod.io.IOUtils;
import org.openCage.lindwurm.Ignore;
import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.LindwurmBuilder;
import org.openCage.lindwurm.SimpleTreeNode;
import org.openCage.lindwurm.xml.TextContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/23/12
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonLindwurmBuilder implements LindwurmBuilder {
    @Override
    public LindenNode build(Ignore ignore, File root) {

        try {
            return build( ignore, new FileInputStream(root));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    public LindenNode build(Ignore ignore, InputStream is ) {
        try {
            JSONObject jsobj = new JSONObject( IOUtils.toString(is));

            LindenDirNode root = SimpleTreeNode.makeDir("root");
            add( root, jsobj );

            return root;

        } catch (JSONException e) {
            // TODO
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.

    }

    private void add( LindenDirNode parent, JSONObject jsobj ) {
        for ( Object key : loopy(jsobj.keys()) ) {
            try {
                Object elem = jsobj.get((String) key);
                mkNode( parent, (String) key, elem);
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                throw new Error("impl me");
            }
        }
    }

    private void mkNode( LindenDirNode parent, String key, Object elem ) {
        if ( elem instanceof String ) {
            parent.addChild( new SimpleTreeNode(new TextContent( key, (String)elem )));
        } else if ( elem instanceof JSONObject ) {
            LindenDirNode child = SimpleTreeNode.makeDir( key);
            add( child, (JSONObject)elem);
            parent.addChild( child );
        } else if ( elem instanceof JSONArray ) {
            JSONArray arr = (JSONArray)elem;
            for ( int i = 0; i < arr.length(); i++ ) {
                try {
                    mkNode( parent, "" + i, arr.get(i));
                } catch (JSONException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    throw new Error("impl me");
                }
            }
        }

    }


    public Iterable loopy( final Iterator it ) {
        return new Iterable() {
            @Override
            public Iterator iterator() {
                return it;
            }
        };
    }
}
