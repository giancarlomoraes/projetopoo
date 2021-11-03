package br.ufg.inf.projetopoo.projeto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.projetopoo.models.Admin;
import br.ufg.inf.projetopoo.repositories.AdminRepository;

public class InterfaceGrafica {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin cadastraAdmin(){
		List<Admin> admins = adminRepository.findAll();
		System.out.println(admins);
		return null;
	}
}
