package entity;

import entity.Enum.ConditionEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
public class Order {
    private Integer id;
    private UserInfo userInfoId;
    private Room roomId;
    private LocalDate beginTimeOfTheOrder;
    private LocalDate endTimeOfTheOrder;
    private ConditionEnum condition;
    private String message;
}