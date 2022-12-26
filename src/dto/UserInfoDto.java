package dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;


@Value
@Builder
public class UserInfoDto {

    Integer id;
    String firstName;
    String lastName;
    String email;
    String password;
    String roleId;
    String telephone;
    String birthday;
    Part image;
}