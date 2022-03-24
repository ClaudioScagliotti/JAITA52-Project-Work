package com.group.projectwork.service;

import com.group.projectwork.entity.Utente;
import com.group.projectwork.repository.UtenteDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteSRV {

  @Autowired
  UtenteDB udb;

  public Utente getByEmail(String email) {
    return udb.findByEmail(email).get();
  }

}
