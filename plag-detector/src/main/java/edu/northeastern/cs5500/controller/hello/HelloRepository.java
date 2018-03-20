package edu.northeastern.cs5500.controller.hello;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository
extends JpaRepository<HelloObject, Integer> {
}

