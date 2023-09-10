package lab.base.service;

import lab.base.entity.User;
import lab.base.layer.user.UserDTO;
import lab.base.model.request.user.UserRequestDTO;

public interface UserService {
    UserDTO signup(UserRequestDTO user);

    UserDTO findByUsername(String username);

    boolean existsByUsername(String username);
}
