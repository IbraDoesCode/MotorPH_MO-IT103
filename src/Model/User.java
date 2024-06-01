package Model;

public class User {

    private int employee_id;
    private String username;
    private String password;
    private String last_name;
    private String first_name;
    private String position;
    private String department;
    private String role;


    public User(int employee_id, String username, String password, String last_name, String first_name, String position, String department, String role) {
        this.employee_id = employee_id;
        this.username = username;
        this.password = password;
        this.last_name = last_name;
        this.first_name = first_name;
        this.position = position;
        this.department = department;
        this.role = role;

    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public boolean validateCredentials(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean isAdmin(){
        return this.role.equals("Admin");
    }
}
