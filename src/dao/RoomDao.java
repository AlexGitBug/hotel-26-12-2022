package dao;

import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import entity.Room;
import entity.RoomStatusEnum;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao {

    private static final RoomDao INSTANCE = new RoomDao();

    private static final String FIND_ALL_SQL = """
            SELECT *
                FROM room
            """;

//    qb.id,
//    qb.capacity,
//    cr.id,
//    cr.kind,
//    join quantity_bed qb on qb.id = room.quantity_bed_id
//    join category_room cr on cr.id = room.category_room_id

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE room.id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO room (number_room, quantity_bed_id, category_room_id, floor, day_price, status)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String DELETE_SQL = """
                DELETE FROM room
                WHERE id  = ?
                """;

    private static final String UPDATE_SQL = """
            UPDATE room
            SET number_room = ?,
                quantity_bed_id = ?,
                category_room_id = ?,
                floor = ?,
                day_price = ?,
                status = ?
            WHERE id = ?
            """;

    public List<Room> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                rooms.add(buildRoom(resultSet));
            }
            return rooms;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Room buildRoom(ResultSet resultSet) throws SQLException {
        return Room.builder()
                .id(resultSet.getInt("id"))
                .number(NumberRoomEnum.valueOf(resultSet.getObject("number_room", String.class)))
//                .quantityBed(quantityBed)
//                .categoryRoom(categoryRoom)
                .floor(FloorEnum.valueOf(resultSet.getObject("floor", String.class)))
//                .quantityBed(quantityBed)
//                .categoryRoom(categoryRoom)
                .dayPrice(resultSet.getObject("day_price", Integer.class))
                .status(RoomStatusEnum.valueOf(resultSet.getObject("status", String.class)))
                .build();
    }

    public Optional<Room> findById(int id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Room room = null;
            if (resultSet.next()) {
                room = buildRoom(resultSet);
            }
            return Optional.ofNullable(room);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }


    public Room save(Room room) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, room.getNumber().toString());
            preparedStatement.setInt(2, room.getQuantityBed().getId());
            preparedStatement.setInt(3, room.getCategoryRoom().getId());
            preparedStatement.setString(4, room.getFloor().toString());
            preparedStatement.setInt(5, room.getDayPrice());
            preparedStatement.setString(6, room.getStatus().toString());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                room.setId(generatedKeys.getInt("id"));
            }
            return room;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public boolean delete(int id) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public void update(Room room) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, room.getNumber().toString());
            preparedStatement.setInt(2, room.getQuantityBed().getId());
            preparedStatement.setInt(3, room.getCategoryRoom().getId());
            preparedStatement.setString(4, room.getFloor().toString());
            preparedStatement.setInt(5, room.getDayPrice());
            preparedStatement.setString(6, room.getStatus().toString());
            preparedStatement.setInt(7, room.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }
    public static RoomDao getInstance() {
        return INSTANCE;
    }
}