package com.example;

import com.example.persistence.entity.PermissionEntity;
import com.example.persistence.entity.RoleEntity;
import com.example.persistence.entity.RoleEnum;
import com.example.persistence.entity.UserEntity;
import com.example.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringDockerSwaggerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDockerSwaggerApplication.class, args);
	}


	@Autowired
	UserRepository userRepository;


	@Override
	public void run(String... args){
		/* CREATE PERMISSIONS */
		PermissionEntity readComic = PermissionEntity.builder()
				.name("READ_COMIC")
				.build();

		PermissionEntity readCharacter = PermissionEntity.builder()
				.name("READ_CHARACTER")
				.build();

		PermissionEntity createUser = PermissionEntity.builder()
				.name("CREATE_USER")
				.build();

		PermissionEntity invalidUser = PermissionEntity.builder()
				.name("INVALID_USER")
				.build();

		/* CREATE ROLES */
		RoleEntity admin = RoleEntity.builder()
				.roleEnum(RoleEnum.ADMIN)
				.permissionList(Set.of(readComic, readCharacter, createUser, invalidUser))
				.build();

		RoleEntity user = RoleEntity.builder()
				.roleEnum(RoleEnum.USER)
				.permissionList(Set.of(readCharacter, readComic))
				.build();

		/* CREATE USERS */
		UserEntity userJuan = UserEntity.builder()
				.username("ADMIN")
				.password("$2a$10$AwBc8cZfghF3qTfa9dei1uI8gVtWPRccli6//zPjQmydF3StGKLpC")
				.isEnabled(true)
				.accountNoLocked(true)
				.accountNoExpired(true)
				.credentialNoExpired(true)
				.roles(Set.of(admin))
				.build();

		UserEntity userSantiago = UserEntity.builder()
				.username("USER")
				.password("$2a$10$AwBc8cZfghF3qTfa9dei1uI8gVtWPRccli6//zPjQmydF3StGKLpC")
				.isEnabled(true)
				.accountNoLocked(true)
				.accountNoExpired(true)
				.credentialNoExpired(true)
				.roles(Set.of(user))
				.build();

		userRepository.saveAll(List.of(userSantiago, userJuan));
	}

}
