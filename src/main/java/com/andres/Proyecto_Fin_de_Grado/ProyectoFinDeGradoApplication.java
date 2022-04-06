package com.andres.Proyecto_Fin_de_Grado;

import com.andres.Proyecto_Fin_de_Grado.Model.Rol;
import com.andres.Proyecto_Fin_de_Grado.Model.Usuario;
import com.andres.Proyecto_Fin_de_Grado.Service.ServicioUsuarioImp;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.sql.SQLOutput;
import java.util.Collection;

@SpringBootApplication
public class ProyectoFinDeGradoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinDeGradoApplication.class, args);


	}

		@Bean
		PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	CommandLineRunner run(ServicioUsuarioImp servicioUsuarioImp){
		return args -> {
			try {
				Rol userRole = new Rol();
				userRole.setNombre("ROL_USER");
				Rol adminRole = new Rol();
				adminRole.setNombre("ROL_ADMIN");

				servicioUsuarioImp.guardarRol(userRole);
				servicioUsuarioImp.guardarRol(adminRole);

				Usuario usuario1 = new Usuario();
				usuario1.setUsuario("andres.arcones.crespo@gmail.com");
				usuario1.setContrasenia("1234");
				usuario1.setNombre("Andrés");
				usuario1.setApellidos("Arcones Crespo");
				usuario1.getRoles().add(userRole);
				usuario1.getRoles().add(adminRole);

				Usuario usuario2 = new Usuario();
				usuario2.setUsuario("santiago.ramonycajal@gmail.com");
				usuario2.setContrasenia("1234");
				usuario2.setNombre("Santiago");
				usuario2.setApellidos("Ramon y Cajal");
				usuario2.getRoles().add(userRole);

				Usuario usuario3 = new Usuario();
				usuario3.setUsuario("steve.Jobs@gmail.com");
				usuario3.setContrasenia("1234");
				usuario3.setNombre("Steve");
				usuario3.setApellidos("Jobs");
				usuario3.getRoles().add(userRole);

				Usuario usuario4 = new Usuario();
				usuario4.setUsuario("jawed.karim@gmail.com");
				usuario4.setContrasenia("1234");
				usuario4.setNombre("Jawed");
				usuario4.setApellidos("Karim");
				usuario4.getRoles().add(userRole);

				servicioUsuarioImp.guardarUsuario(usuario1);
				servicioUsuarioImp.guardarUsuario(usuario2);
				servicioUsuarioImp.guardarUsuario(usuario3);
				servicioUsuarioImp.guardarUsuario(usuario4);

				System.out.println(servicioUsuarioImp.getUsuarios());
			}catch (Exception e){
				e.printStackTrace();
			}
		};
	}






}