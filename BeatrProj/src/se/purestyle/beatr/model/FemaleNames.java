package se.purestyle.beatr.model;

import java.util.ArrayList;
import java.util.List;

public class FemaleNames {

	private static List<String> names = new ArrayList<String>();
	
	public static void populate() {
		
		names.add("Alice");
		names.add("Anna");
		names.add("Anne");
		names.add("Beatrice");
		names.add("Caroline");
		names.add("Charlotte");
		names.add("Claire");
		names.add("Diana");
		names.add("Edith");
		names.add("Eleanor");
		names.add("Eliza");
		names.add("Elizabeth");
		names.add("Emily");
		names.add("Emma");
		names.add("Eve");
		names.add("Faith");
		names.add("Florence");
		names.add("Frances");
		names.add("Grace");
		names.add("Helen");
		names.add("Irene");
		names.add("Jane");
		names.add("Jean");
		names.add("Jill");
		names.add("Judith");
		names.add("Julia");
		names.add("Kate");
		names.add("Katherine");
		names.add("Laura");
		names.add("Louisa");
		names.add("Lucy");
		names.add("Madeline");
		names.add("Margaret");
		names.add("Maria");
		names.add("Marian");
		names.add("Martha");
		names.add("Mary");
		names.add("Olivia");
		names.add("Pamela");
		names.add("Patricia");
		names.add("Rachel");
		names.add("Rebecca");
		names.add("Rose");
		names.add("Ruth");
		names.add("Sally");
		names.add("Sarah");
		names.add("Sophia");
		names.add("Susannah");
		names.add("Victoria");
		names.add("Virginia");
	}
	
	public static String getRandomName() {
		
		int random = (int) Math.round( Math.random() * ( names.size() - 1 ) );
		
		String nameToReturn = names.get( random );
		
		names.remove( random );
		
		return nameToReturn;
	}
}