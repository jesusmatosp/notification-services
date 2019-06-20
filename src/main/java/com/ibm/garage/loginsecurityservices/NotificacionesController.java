package com.ibm.garage.loginsecurityservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.garage.loginsecurityservices.bean.Data;

@RestController
public class NotificacionesController {

	@Autowired
	private NotificacionService service;
	
	@PostMapping("/notificar")
	public String enviarNotificaciones(@RequestBody NotificacionRequest request) {
		Data resultado = service.notificarUsuario(request.getUser(), request.getMessage());
		return "mensaje enviado a: " +  resultado.getName() + " al correo: "+resultado.getEmail();
	}
	
}
