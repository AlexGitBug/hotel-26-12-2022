package servlet;

import dto.UserInfoDto;
import entity.Enum.RoleEnum;
import entity.UserInfo;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.RoleService;
import service.UserInfoService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserInfoService userInfoService = UserInfoService.getInstance();
    private final RoleService roleService = RoleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userInfoDto = UserInfoDto.builder()
                .firstName(req.getParameter("name"))
                .lastName(req.getParameter("lastname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .roleId(req.getParameter("role"))
                .telephone(req.getParameter("telephoneNumber"))
                .birthday(req.getParameter("birthday"))
                .image(req.getPart("image"))
                .build();

        try {
            userInfoService.create(userInfoDto);
            resp.sendRedirect("/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}