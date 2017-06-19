package com.ashokn.controller;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ashok on 6/17/17.
 */
public class Message implements Serializable {

        private HttpStatus status;
        private String message;

        public Message(HttpStatus status, String message) {
            this.status = status;
            this.message = message;
        }
}
