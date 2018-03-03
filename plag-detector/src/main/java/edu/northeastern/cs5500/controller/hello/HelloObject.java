package edu.northeastern.cs5500.controller.hello;

import javax.persistence.*;
@Entity(name="hello")

public class HelloObject {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;
	 public int getId() {
	  return id;
	 }
	 public void setId(int id) {
	  this.id = id;
	 }
 private String message;
 public String getMessage() {
  return message;
 }
 public void setMessage(String message) {
  this.message = message;
 }
 public HelloObject(String message) {
  this.message = message;
 }
 public HelloObject() {
 }
}