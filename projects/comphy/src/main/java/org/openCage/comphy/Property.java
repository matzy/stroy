package org.openCage.comphy;

/**
 * A Property here is an object that can be (a) serialized, i.e. stored persistently and (b) readable and
 * guaranties changes to be persisted and informs interested parties about changes
 * => it is observable and readalizable
 *
 */
public interface Property extends Observable, Readalizable {
}
