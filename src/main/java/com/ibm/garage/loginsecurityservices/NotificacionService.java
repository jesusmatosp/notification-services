package com.ibm.garage.loginsecurityservices;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.garage.loginsecurityservices.bean.Data;
import com.ibm.garage.loginsecurityservices.utils.MailUtils;

@Service
public class NotificacionService {

	@Autowired
	private RestTemplate restTemplate;
	
	public Data notificarUsuario(String username, String message) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    // String url = "http://localhost:8003/users/" + username;
	    String url = "https://user-services-garage-ibm-jm.mybluemix.net/users/" + username;
	    Data usuario = restTemplate.exchange(url, HttpMethod.GET, entity, Data.class).getBody();
	    try {
			MailUtils.sendEmailGmailAccount(usuario.getEmail(), false, null, message, "AVISO DE NOTIFICACIÓN DE MICROSERVICIO DE NOTIFICACIONES");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return usuario;
	}
	
}
