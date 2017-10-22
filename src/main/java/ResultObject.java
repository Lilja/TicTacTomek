package main.java;

/**
 * A type of return type that can contain more than an integer or string.
 * Because sometimes you need both a return value and a string.
 */
public class ResultObject {
    public int code = -1;
    public int value = -1;
    public String message;
}
