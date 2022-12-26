package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {


    Integer id;
    String firstName;
    String lastName;
    String email;
    String telephone;
    String birthday;
    String image;
}