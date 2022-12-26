package entity;

import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import lombok.Builder;

import lombok.Data;

@Data
@Builder
public class Room {

    private Integer id;
    private NumberRoomEnum number;
    private QuantityBed quantityBed;
    private CategoryRoom categoryRoom;
    private FloorEnum floor;
    private int dayPrice;
    private RoomStatusEnum status;

}