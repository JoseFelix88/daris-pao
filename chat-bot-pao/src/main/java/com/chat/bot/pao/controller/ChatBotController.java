package com.chat.bot.pao.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

import com.chat.bot.pao.model.Libro;

@Controller
@SessionScope
public class ChatBotController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6225751155353626349L;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
}
