/*
 * Computer Science 3501 - Fall 2007, slightly updated 2021
 * Framework for testing Java regular expressions
 * Version 1.0
 *
 * This class contains 3 regular expressions functions to match, split, and replace substrings
 * using the Java regular expression classes quickly and easily. Instead of directly dealing
 * with pattern creation, managing matcher objects, and running loops on strings, you need
 * only invoke the relevant function. The output for each function is meaningful.
 *
 * The matcher function will print the number of matches, and the matched substrings.
 *
 * The split function will print the number of fragments, and the fragment substrings.
 *
 * The replace function will print the altered output.
 *
 * Simply program in your regular expressions, optional parameters, and your input string(s)
 * as calls to these functions.
 *
 * Read the function javadocs below for specific usage information.
 * -Daniel Selifonov
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegExTester {

	public static void main(String[] args) {
		
		// Problem 1 
		System.out.println("Problem 1");
		doRegExMatch("nfa|dfa", Pattern.CASE_INSENSITIVE, "I love nfas on the weekend, but prefer dfas during the week");
		
		// Problem 2
		System.out.println("Problem 2");
		doRegExMatch("\\d+", Pattern.CASE_INSENSITIVE, "618. My WoW username is thelegend28. I've received over 249 suspensions for my negative in-game behavior. Il fait 12 C sur Lille ce matin! ");
	
		// Problem 3
		System.out.println("Problem 3");
		doRegExMatch("-\\d+", Pattern.CASE_INSENSITIVE, "-120. My WoW username is thelegend28 and my friend goes by IM-HIM-12. I've received over -249 suspensions for my negative in-game behavior. Il fait -12 C sur Lille ce matin!");
		
		// Problem 4
		System.out.println("Problem 4");
		doRegExMatch("\\bt.*?\\b", Pattern.CASE_INSENSITIVE, "Tim tattled to the teacher again, he's so annoying, that guy!");

		// Problem 5 
		System.out.println("Problem 5");
		doRegExMatch("\\b[A-z]*t[A-z]*\\b|\\b[A-z]*i[A-z]*\\b", Pattern.CASE_INSENSITIVE, "Timmy did not even read the chapter, what a weirdo!");

		// Problem 6 
		System.out.println("Problem 6");
		doRegExSplit("\\|", "I love putting|random | in my sentences ! It's always interesting to | see where I decide to | put t|he next one|! ");
		
		// Problem 7 
		System.out.println("\nProblem 7");
		doRegExReplace("today", Pattern.CASE_INSENSITIVE, "yesterday", "I went to McDonald's earlier today. Sadly, I'd forgotten it's closed today, so today, I didn't get my hamburger! ");

	}

	/**
	 * Match the regular expression pattern against the input, with optional pattern
	 * flags.
	 *
	 * The pattern is expected to be a valid Java regular expression as described in
	 * the "Summary of regular-expression constructs" within the documentation:
	 * http://java.sun.com/javase/6/docs/api/java/util/regex/Pattern.html
	 *
	 * Pattern flags are also described in the documentation. For this lab, you will
	 * likely need to provide either "0" or "Pattern.CASE_INSENSITIVE" in this
	 * parameter.
	 *
	 * The input can be any Java string. It will be matched against the pattern with
	 * any patternFlag modifiers.
	 *
	 * This function will print the pattern, input, and match count. This will be
	 * followed by a list of all matched sub-strings.
	 *
	 * @param pattern      A regular-expression pattern.
	 * @param patternFlags Processing modification flags for the pattern.
	 * @param input        A string to test.
	 */
	private static void doRegExMatch(String pattern, int patternFlags, String input) {
		List<String> matches = new ArrayList<>();
		Pattern pat = Pattern.compile(pattern, patternFlags);
		Matcher mat = pat.matcher(input);
		int count;
		for (count = 0; mat.find(); count++) {
			matches.add(input.substring(mat.start(), mat.end()));
		}
		System.out.println(
				"Matched (" + (patternFlags == Pattern.CASE_INSENSITIVE ? "case insensitive" : "case sensitive")
						+ "): \"" + pattern + "\" in \"" + input + "\" " + count + " times.");
		for (int i = 0; i < count; i++) {
			System.out.println("\t Match #" + (i + 1) + ": " + matches.get(i) + "\n");
		}
		System.out.println();
	}

	/**
	 * Fragment the input string by the given regular expression pattern.
	 *
	 * Java allows you to split strings by regular expressions. This function will
	 * take a regular expression and an input string, then will produce a list of
	 * all fragments produced when the input string is split by the regular
	 * expression defined delimiter.
	 *
	 * @param pattern A regular-expression pattern.
	 * @param input   A string to fragment.
	 */
	private static void doRegExSplit(String pattern, String input) {
		Pattern pat = Pattern.compile(pattern);
		String[] parts = pat.split(input);
		System.out.print(
				"Found " + parts.length + " fragments after splitting \"" + input + "\" by \"" + pattern + "\": [");
		for (int i = 0; i < parts.length; i++) {
			System.out.print("\"" + parts[i] + "\"");
			if (i < (parts.length - 1))
				System.out.print(", ");
		}
		System.out.println("].");
		System.out.println();
	}

	/**
	 * Replace all occurrences of the pattern, with the string literal defined in
	 * replace, over the input string, with patternFlag processing flags.
	 *
	 * Java allows replacement of regular expression defined regions in a string by
	 * any string literal. This function will do that, and print the results.
	 *
	 * @param pattern      The pattern defining the replacement regions.
	 * @param patternFlags Any modification flags on the pattern matching.
	 * @param replace      A string literal used to replace the pattern regions.
	 *                     (This is not a regular expression string.)
	 * @param input        The string upon which to perform the matching and
	 *                     replacement.
	 */
	private static void doRegExReplace(String pattern, int patternFlags, String replace, String input) {
		// Examples:
		Pattern pat = Pattern.compile(pattern, patternFlags);
		Matcher mat = pat.matcher(input);
		String replaced = mat.replaceAll(replace);
		System.out.println(
				"Replaced all (" + (patternFlags == Pattern.CASE_INSENSITIVE ? "case insensitive" : "case sensitive")
						+ ") occurrences of \"" + pattern + "\" in sentence \"" + input + "\" with \"" + replace
						+ "\" to make: \"" + replaced + "\".");
		System.out.println();

		// Write your code here:
	}
}