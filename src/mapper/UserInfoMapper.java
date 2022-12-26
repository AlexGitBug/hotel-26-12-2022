package mapper;

import dto.UserDto;
import dto.UserInfoDto;
import entity.UserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoMapper implements Mapper<UserInfo, UserDto> {

    private static final UserInfoMapper INSTANCE = new UserInfoMapper();
    @Override
    public UserDto mapFrom(UserInfo object) {
        return UserDto.builder()
                .id(object.getId())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .email(object.getEmail())
                .telephone(object.getTelephone())
                .birthday(object.getBirthday().toString())
                .image(object.getImage())
                .build();

    }

    public static UserInfoMapper getInstance() {
        return INSTANCE;
    }
}