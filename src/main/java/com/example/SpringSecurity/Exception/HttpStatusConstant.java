/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.Exception;

/**
 *
 * @author ADMIN
 */
public class HttpStatusConstant {
    // success
    public final static String SUCCESS_CODE = "00000";
    public final static String SUCCESS_MESSAGE = "Successful";

    // unknown error
    public final static String UNAVAILABLE_CODE = "99999";
    public final static String UNAVAILABLE_MESSAGE = "Something wrong!";

    // sql exception
    public final static String SQL_CONNECTION_ERROR_CODE = "00001";
    public final static String SQL_CONNECTION_ERROR_MESSAGE = "Some thing wrong with connection of database!";

    // unauthorized
    public final static String UNAUTHORIZED_CODE = "00010";
    public final static String UNAUTHORIZED_MESSAGE = "You are unauthorized!";
    public final static String AUTHENTICATION_FAIL_CODE = "00011";
    public final static String AUTHENTICATION_FAIL_MESSAGE = "Authentication failed!";

    // null pointer exception
    public final static String NULL_POINTER_OR_BAD_REQUEST_CODE = "00020";
    public final static String NULL_POINTER_OR_BAD_REQUEST_MESSAGE = "You passed wrong or blank input data!";

    // not found
    public final static String NOT_FOUND_CODE = "00030";
    public final static String USER_NOT_FOUND_MESSAGE = "User not found!";
    public final static String ADDRESS_NOT_FOUND_MESSAGE = "Address not found!";

    // invalid data
    public final static String INVALID_DATA_CODE = "00040";
    public final static String INVALID_DATA_MESSAGE = "Please input valid data!";
    
    // not found
    public final static String EXIST_CODE = "00050";
    public final static String USER_EXIST_MESSAGE = "User exist!";
    public final static String ADDRESS_EXIST_MESSAGE = "Address exist!";
}
