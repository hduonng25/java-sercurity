package lab.base.layer.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String username;
    private String password;
    private String roles; // Ví dụ: "ROLE_USER,ROLE_ADMIN"
}
