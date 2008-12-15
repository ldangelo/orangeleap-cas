package com.mpower.cas.service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * An implementation of the PasswordSecurityPolicy that checks a String to
 * ensure it is at least a specified length and contains a configurable minimum
 * number of letters, numbers and special characters. Default values are all
 * zero, so an instance of this class without any properties set would consider
 * any non-null password as secure.
 * @version 1.0
 */
public class MinimalPasswordSecurityPolicy implements PasswordSecurityPolicy {

    private int minChar = 0;
    private int minNumeric = 0;
    private int minSymbols = 0;
    private int minLen = 0;

    //regex patterns
    private Pattern LETTERS = Pattern.compile("[a-zA-Z]");
    private Pattern NUMBERS = Pattern.compile("[0-9]");
    private Pattern SYMBOL = Pattern.compile("[^a-zA-Z0-9\\s]");

    @Override
    public boolean isSecure(String password) {
        // reject if null
        if(password == null) {
            return false;
        }

        password = password.trim();

        // reject if not long enough
        if(password.length() < minLen) {
            return false;
        }

        // check against the regular expressions to ensure
        // the password has a minimal number of each character
        // type (letter, number, symbol)
        if(minChar > 0) {
            Matcher match = LETTERS.matcher(password);
            if(matchCount(match) < minChar) {
                return false;
            }
        }

        if(minNumeric > 0) {
            Matcher match = NUMBERS.matcher(password);
            if(matchCount(match) < minNumeric) {
                return false;
            }
        }

        if(minSymbols > 0) {
            Matcher match = SYMBOL.matcher(password);
            if(matchCount(match) < minSymbols) {
                return false;
            }
        }
        
        return true;

    }

    /**
     * The minimum number of letters(a-z / A-Z) that must be present for
     * a password to be considered secure. Default is zero.
     */
    public void setMinLetters(int minChar) {
        if(minChar >=0)
        this.minChar = minChar;
    }

    /**
     * The minimum number of numbers (0-9) that must be present for
     * a password to be secure. Default is zero.
     */
    public void setMinNumbers(int minNumeric) {
        if(minNumeric >= 0)
        this.minNumeric = minNumeric;
    }

    /**
     * The minimum number of special characters (not a letter, number or
     * whitespace) that must be present for a password to be secure.
     * Default is zero.
     */
    public void setMinSymbols(int minSymbols) {
        if(minSymbols >= 0)
        this.minSymbols = minSymbols;
    }

    /**
     * The minimal length of a password for it to be secure. This is
     * checked before any of the more specific tests. Default is zero.
     */
    public void setMinLength(int minLen) {
        if(minLen >=0)
        this.minLen = minLen;
    }

    /*
    Utility method. Given a Match, execute find() as many times as
    it returns true and return the count of total matches.
     */
    private int matchCount(Matcher matcher) {

        int count = 0;

        while (matcher.find()) {
            count++;
        }

        return count;
    }

}
