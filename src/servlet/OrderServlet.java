package servlet;

import dto.OrderDto;
import dto.UserInfoDto;
import entity.Enum.ConditionEnum;;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OrderService;
import service.RoleService;
import service.RoomService;
import service.UserInfoService;
import util.JspHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static util.UrlPath.LOGIN;
import static util.UrlPath.ORDER_DONE;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

//    private final OrderService orderService = OrderService.getInstance();
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        int id = Integer.parseInt(req.getParameter("id"));
//
//
////        var userInfoId = Integer.parseInt(req.getParameter("userId"));
////        var roomId = Integer.parseInt(req.getParameter("roomId"));
//        var beginTime = LocalDateTime.parse(req.getParameter("beginTime"));
//        var endTime = LocalDateTime.parse(req.getParameter("endTime"));
//        var condition = ConditionEnum.valueOf(req.getParameter("condition"));
//        var message = req.getParameter("message");
//
//  //      userInfoId, roomId,
//
//                orderService.delete(id);
//        orderService.findById(id);
//        orderService.findAll();
//        orderService.save(beginTime, endTime, condition, message);
//        orderService.update(id,beginTime, endTime, condition, message);
//    }


    private final OrderService orderService = OrderService.getInstance();
    private final UserInfoService userInfoService = UserInfoService.getInstance();
    private final RoomService roomService = RoomService.getInstance();
    private final RoleService roleService = RoleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userinfoid", userInfoService.findAll());
        req.setAttribute("roomid", roomService.findAll());
        req.setAttribute("roles", roleService.findAll());
        req.setAttribute("conditions", ConditionEnum.values());


        req.getRequestDispatcher(JspHelper.getPath("orders"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var orderDto = OrderDto.builder()
                .userInfoId(req.getParameter("userinfoid"))
                .roomId(req.getParameter("roomid"))
                .beginTimeOfTheOrder(req.getParameter("begintime"))
                .endTimeOfTheOrder(req.getParameter("endtime"))
                .condition(req.getParameter("conditions"))
                .message(req.getParameter("message"))
                .build();

        try {
            orderService.create(orderDto);
            resp.sendRedirect(ORDER_DONE);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}