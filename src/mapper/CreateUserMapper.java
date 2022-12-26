package mapper;

import dao.RoleDao;
import dto.UserInfoDto;
import entity.Role;
import entity.UserInfo;
import lombok.NoArgsConstructor;
import util.LocalDateFormatter;


import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<UserInfoDto, UserInfo> {

    private static final String IMAGE_FOLDER = "users/";
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private final RoleDao roleDao = RoleDao.getInstance();
    @Override
    public UserInfo mapFrom(UserInfoDto object) {
        return UserInfo.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .email(object.getEmail())
                .password(object.getPassword())
                .roleId(roleDao.findById(Integer.parseInt(object.getRoleId())).get())
                .telephone(object.getTelephone())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}