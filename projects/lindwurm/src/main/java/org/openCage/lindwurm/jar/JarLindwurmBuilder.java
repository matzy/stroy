package org.openCage.lindwurm.jar;

import org.openCage.kleinod.io.FileUtils;
import org.openCage.kleinod.io.IOUtils;
import org.openCage.lindwurm.Ignore;
import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.LindwurmBuilder;
import org.openCage.lindwurm.SimpleTreeNode;
import org.openCage.lindwurm.content.ReducedContent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static org.openCage.kleinod.collection.Iterators.iterate;


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

public class JarLindwurmBuilder implements LindwurmBuilder  {

    @Override
    public LindenNode build(Ignore ignore, File file) {

        if ( ignore.match("/" + file.getName())) {
            throw new Error( "top level dir is in ignore pattern" );
        }

        return add( ignore, file );
    }


    private LindenNode add( Ignore ignore, File file ) {


        Map<String, LindenNode> nodes = new HashMap<String, LindenNode>();

        LindenNode root = SimpleTreeNode.makeDir(new ReducedContent(file.getName(), FileUtils.getExtension(file)));
        nodes.put("/", root );

        JarFile zf = null;
        try {
            zf = new JarFile( file );

            for ( JarEntry entry : iterate( zf.entries() )) {

                String   elemPath  = FileUtils.normalizePath(entry.getName());
                String   parentPath = new File( elemPath ).getParent();
                String   name       = new File( elemPath ).getName();

                if ( ignore.match( elemPath )) {
                    // filter
                    continue;
                }

                LindenNode node   = null;
                LindenNode parent = null;

                parent = nodes.get( parentPath );

                if ( parent == null ) {
                        // assume filtered
                        continue;
                }

                if ( entry.isDirectory() ) {
                    node = SimpleTreeNode.makeDir( new ReducedContent(name, FileUtils.getExtension(name)));
                } else {
                    node = new SimpleTreeNode( new JarContent( file.getAbsolutePath(), entry, name) );
                }

                parent.dir().addChild( node );

                nodes.put(elemPath, node);
            }
        } catch( IOException e ) {
            throw new IllegalArgumentException( e );
        } finally {
            IOUtils.closeQuietly(zf);
        }


        return root;
    }

}
