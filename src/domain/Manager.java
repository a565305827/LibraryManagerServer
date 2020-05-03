package domain;

public class Manager {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String id;
	private String name;
	private String pwd;
	private String utype;

	public String getUtype() {
		return utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Manager{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", pwd='" + pwd + '\'' +
				'}';
	}
}
