package br.com.aas.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.aas.entities.User;
import br.com.aas.entities.enums.Perfil;

public class UserDTO {
	@NotNull(message = "Campo Nome obrigatorio!!")
	@Length(max = 40, message = "Numero maximo de caracter 40!!")
	private String name;
	@NotNull(message = "Campo Login obrigatorio!!")
	@Length(max = 30, message = "Numero maximo de caracter 30!!")
	private String login;
	@NotNull(message = "Campo Senha obrigatorio!!")
	@Length(max = 30, message = "Numero maximo de caracter 30!!")
	private String password;
	@Length(max = 50, message = "Numero maximo de caracter 50!!")
	private String email;

	@NotNull(message = "Informe um perfil!!")
	private Set<Perfil> perfis = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [name=").append(name).append(", login=").append(login).append(", password=")
				.append(password).append(", email=").append(email).append(", perfis=").append(perfis).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((perfis == null) ? 0 : perfis.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (perfis == null) {
			if (other.perfis != null)
				return false;
		} else if (!perfis.equals(other.perfis))
			return false;
		return true;
	}

	public User toEntity() {
		ModelMapper map = new ModelMapper();
		return map.map(this, User.class);
	}
}
