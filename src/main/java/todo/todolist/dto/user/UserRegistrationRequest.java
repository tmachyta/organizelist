package todo.todolist.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import todo.todolist.lib.FieldsValueMatch;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)

@Data
public class UserRegistrationRequest {
    @NotBlank
    @Size(min = 8, max = 50)
    private String email;
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
    private String repeatPassword;
    @NotBlank
    @Size(min = 2, max = 35)
    private String firstName;
    @Size(min = 2, max = 35)
    private String lastName;
    @Size(min = 13, max = 13)
    private String phone;
}
