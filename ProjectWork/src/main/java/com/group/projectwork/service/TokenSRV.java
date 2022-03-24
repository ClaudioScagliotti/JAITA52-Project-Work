package com.group.projectwork.service;

import com.group.projectwork.entity.Token;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.exception.TokenExpiredException;
import com.group.projectwork.repository.TokenDB;
import com.group.projectwork.utility.StringUtils;

import java.time.Duration;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenSRV {

    @Autowired
    TokenDB db;

    private Token updToken(Token token) {
    	token.setIns(new Date(System.currentTimeMillis()));
    	return this.db.save(token);
    }
    
    private Token getByValue(String tokenValue) throws TokenExpiredException {
    	var opt = this.db.findByValore(tokenValue);
    	if(opt.isEmpty())
    		throw new EntityNotFoundException();
    	
    	var token = opt.get();
    	
    	Date date = new Date(System.currentTimeMillis());
    	var duration = Duration.between(token.getIns().toInstant(),date.toInstant());
    	if(duration.abs().getSeconds()/60 > 15) {
    		this.db.delete(token);
    		throw new TokenExpiredException();
    	}
    	
    	return this.updToken(token);
    }

    public boolean isValid(String token) {
    	try {
    		this.getByValue(token);
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    }

    public Token generate(Utente u) {
    	Token token = new Token();
    	token.setUtente(u);
    	token.setValore(StringUtils.random(25));
    	return this.updToken(token);
    }

    public void delete(Utente u) 
    {
    	var opt = this.db.findByUtente(u);
    	if(opt.isEmpty())
    		return;
    		
    	this.db.delete(opt.get());
    }

	public void delete(String tokenValue) {
		try {
			var token = this.getByValue(tokenValue);
			this.db.delete(token);
		} catch (TokenExpiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
