package org.openCage.notabug;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.lang.inc.ImmuDate;
import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;
import org.openCage.ruleofthree.Property;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.Threes;
import org.openCage.ruleofthree.property.ImmuProperty;
import org.openCage.ruleofthree.property.ListProperty;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/23/12
 * Time: 7:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Task implements Property {

    private final UUID id;
    private final ListProperty<ImmuProperty<String>> reporters;
    private final ListProperty<ImmuProperty<String>> tags;
    private String name;
    private String description;
    private State state;
    private final ImmuDate created;
    private final Kind kind;
    private final ListProperty<Id> references;


    private VoidListeners listeners = new VoidListeners();

    @Inject
    public Task(@Named("id") UUID id,
                @Named("reporters") ListProperty<ImmuProperty<String>> reporters,
                @Named("tags") ListProperty<ImmuProperty<String>> tags,
                @Named("name") String name,
                @Named("description") String description,
                @Named("state") State state,
                @Named("created") ImmuDate date,
                @Named("kind") Kind kind,
                @Named("references") ListProperty<Id> references) {
        this.tags = tags;
        this.state = state;
        this.created = date;
        this.id = id;
        this.reporters = reporters;
        this.name = name;
        this.description = description;
        this.kind = kind;
        this.references = references;
    }

    private Task() {
        tags = new ListProperty<ImmuProperty<String>>();
        id = UUID.randomUUID();
        reporters = new ListProperty<ImmuProperty<String>>();
        name = "";
        description = "";
        state = State.open;
        created = ImmuDate.now();
        kind = Kind.feature;
        references = new ListProperty<Id>();
    }

    public static Task next( String title) {
        Task task =  new Task();

        task.name = title;

        return task;
    }


    @Override
    public VoidListenerControl getListenerControl() {
        return listeners;
    }

    @Override
    public Three toThree() {
        return  Threes.newMap().
                put("description", description).
                put( "id", id ).
                put( "name", name ).
                put( "reporters", reporters).
                put( "tags", tags ).
                put( "kind", kind ).
                put( "created", created ).
                put( "state", state );
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", kind=" + kind +
                ", tags=" + tags +
                ", reporters=" + reporters +
                ", name=" + name +
                ", description=" + description +
                ", state=" + state +
                '}';
    }

    public State getState() {
        return state;
    }

    public UUID getId() {
        return id;
    }
}
