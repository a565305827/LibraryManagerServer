package domain;

/**
 * insert into tb_student
 * (name, sex, birthday, tel, email, createDate, operator) VALUES
 * ("刘一峰", "男", "2019-10-1", "13313364785", "yifeng@163.com", NOW(),
 * "吴磊");
 */
public class Student {
    private String stuid;

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    private String name;
    private String sex;
    private String birthday;
    private String tel;
    private String email;
    private String createDate;
    private String operator;
    private String pwd;
    private String utype;

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuid='" + stuid + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", createDate='" + createDate + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
