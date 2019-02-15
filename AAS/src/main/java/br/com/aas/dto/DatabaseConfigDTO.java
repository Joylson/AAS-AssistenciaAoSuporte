package br.com.aas.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.aas.entities.DatabaseConfig;
import br.com.aas.entities.enums.Driver;

public class DatabaseConfigDTO {

	private long id;
	@NotEmpty(message = "Necessario informar uma url")
	@Length(max = 80, message = "limiter de 80 caracter na url")
	private String url;
	@NotEmpty(message = "Necessario informar uma user")
	@Length(max = 50, message = "limiter de 50 caracter no usuario")
	private String user;
	@Length(max = 30, message = "limiter de 30 caracter na senha")
	private String password;
	private Driver driver;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		DatabaseConfigDTO other = (DatabaseConfigDTO) obj;
		if (driver != other.driver)
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DatabaseConfigDTO [id=" + id + ", url=" + url + ", user=" + user + ", password=" + password
				+ ", driver=" + driver + "]";
	}

	public DatabaseConfig toEntity() {
		ModelMapper map = new ModelMapper();
		return map.map(this, DatabaseConfig.class);
	}

}
