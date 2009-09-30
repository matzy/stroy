//package org.openCage.application.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.openCage.application.impl.pojos.ApplicationByBuilder;
//import org.openCage.application.impl.pojos.VersionImpl;
//import org.openCage.application.protocol.Application;
//import org.openCage.application.protocol.ApplicationBuilder;
//import org.openCage.application.protocol.Author;
//import org.openCage.application.protocol.AuthorBuilder;
//import org.openCage.application.protocol.ContactBuilder;
//
//import com.google.inject.Inject;
//
//public class AppBuilder2 implements ApplicationBuilder {
//
//	private String             name = "stuff";
//	private final List<Author> authors = new ArrayList<Author>();
//	private final AuthorBuilder authorBuilder;
//
//	@Inject
//	public AppBuilder2( final AuthorBuilder authorBuilder ) {
//
//		this.authorBuilder = authorBuilder;
//	}
//
//	public Application build() {
//		return null; //new ApplicationByBuilder( name, authors, new VersionImpl(0,1,2,3) );
//	}
//
//	public ApplicationBuilder with(Author author) {
//		authors.add(author);
//		return this;
//	}
//
//	public AuthorBuilder author() {
//		return authorBuilder;
//	}
//
//	public ApplicationBuilder name( String name ) {
//		this.name = name;
//		return this;
//	}
//
//	public ContactBuilder contact() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
