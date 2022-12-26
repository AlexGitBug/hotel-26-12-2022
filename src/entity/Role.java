package entity;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder

public class Role {

    Integer id;
    String rank;

}