package dao;

import entity.*;
import entity.Enum.ConditionEnum;
import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();
    private static final String FIND_ALL_SQL = """
            SELECT orders.id,
                   orders.user_info_id,
                  orders.room_id,
                  orders.begin_time,
                  orders.end_time,
                  orders.condition,
                  orders.message,
                  ui.first_name,
                  ui.last_name,
                  ui.email,
                  ui.password,
                  ui.telephone,
                  ui.birthday,
                  r.number_room,
                  r.quantity_bed_id,
                  r.category_room_id,
                  r.floor,
                  r.day_price,
                  r.status
                FROM orders
                JOIN user_info ui ON ui.id = orders.user_info_id
                JOIN room r on orders.room_id = r.id
            """;

    private static final String UPDATE_SQL = """
            UPDATE orders
            SET begin_time = ?,
                end_time = ?,
                condition = ?,
                message = ?
            WHERE id = ?
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE orders.id = ?
            """;
    private static final String SAVE2_SQL = "INSERT INTO orders(user_info_id, room_id, begin_time, end_time, condition, message) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SAVE_SQL = """
            INSERT INTO orders (begin_time, end_time, condition, message) 
            VALUES (?, ?, ?, ?)
            """;


    private static final String DELETE_SQL = """
            DELETE FROM orders
            WHERE id  = ?
            """;

    private OrderDao() {
    }

    public List<Order> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<Order> findById(int id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = buildOrder(resultSet);
            }
            return Optional.ofNullable(order);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private static Order buildOrder(ResultSet resultSet) {
//        try {
//            var userRole = Role.builder()
//                    .id(resultSet.getObject("id", Integer.class))
//                    .rank(resultSet.getObject("rank", String.class))
//                    .build();
//            var userInfo = UserInfo.builder()
//                    .id(resultSet.getInt("user_info_id"))
//                    .firstName(resultSet.getString("first_name"))
//                    .lastName(resultSet.getString("last_name"))
//                    .email(resultSet.getString("email"))
//                    .password(resultSet.getString("password"))
//                    .roleId(resultSet.getInt("role_id"))
//                    .telephone(resultSet.getString("telephone"))
//                    .birthday(Timestamp.valueOf(resultSet.getString("birthday")).toLocalDateTime().toLocalDate())
//                    .build();
//
//            var room = Room.builder()
//                    .id(resultSet.getInt("id"))
//                    .number(NumberRoomEnum.valueOf(resultSet.getObject("number_room", String.class)))
//                    .floor(FloorEnum.valueOf(resultSet.getObject("floor", String.class)))
//                    .dayPrice(resultSet.getInt("day_price"))
//                    .status(RoomStatusEnum.valueOf(resultSet.getObject("status", String.class)))
//                    .build();

//            return Order.builder()
//                    .id(resultSet.getInt("id"))
//                    .userInfoId(userInfo)
//                    .roomId(room)
//                    .beginTimeOfTheOrder(resultSet.getTimestamp("begin_time").toLocalDateTime().toLocalDate())
//                    .endTimeOfTheOrder(resultSet.getTimestamp("end_time").toLocalDateTime().toLocalDate())
//                    .condition(ConditionEnum.valueOf(resultSet.getObject("condition", String.class)))
//                    .message(resultSet.getObject("message", String.class))
//                    .build();

//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public void update(Order order) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
//                preparedStatement.setInt(1, order.getUserInfoId().getId());
//                preparedStatement.setInt(2, order.getRoomId().getId());
            preparedStatement.setObject(1, order.getBeginTimeOfTheOrder());
            preparedStatement.setObject(2, order.getEndTimeOfTheOrder());
            preparedStatement.setObject(3, order.getCondition().name());
            preparedStatement.setObject(4, order.getMessage());
            preparedStatement.setInt(5, order.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Order save(Order order) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE2_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, order.getUserInfoId().getId());
            preparedStatement.setObject(2, order.getRoomId().getId());
            preparedStatement.setObject(3, order.getBeginTimeOfTheOrder());
            preparedStatement.setObject(4, order.getEndTimeOfTheOrder());
            preparedStatement.setObject(5, order.getCondition().name());
            preparedStatement.setObject(6, order.getMessage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt("id"));
            }
            return order;
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


    public static OrderDao getInstance() {
        return INSTANCE;
    }
}