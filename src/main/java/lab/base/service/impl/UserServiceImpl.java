package lab.base.service.impl;

import lab.base.entity.User;
import lab.base.layer.user.UserDTO;
import lab.base.mapper.user.UserMapper;
import lab.base.model.request.user.UserRequestDTO;
import lab.base.repo.UserRepository;
import lab.base.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }
//    @Override
//    public UserDTO signup(UserDTO  user) {
//        return userRepository.save(user);
//    }
    @Override
    public UserDTO signup(UserRequestDTO userDTO) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            // Xử lý logic khi người dùng đã tồn tại
            return null; // Hoặc bạn có thể throw một Exception tùy ý
        }

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Chuyển đổi từ DTO thành Entity
        User user = modelMapper.map(userDTO, User.class);

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        User savedUser = userRepository.save(user);

        // Chuyển đổi từ Entity thành DTO
        return modelMapper.map(savedUser, UserDTO.class);
    }


    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
