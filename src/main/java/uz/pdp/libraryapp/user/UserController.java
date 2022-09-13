package uz.pdp.libraryapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    public final UserDao userDao;
    @PostMapping
    public String create(User user,Model model) {
        boolean exist = userDao.exist(user.getPhoneNumber());
        if (exist) {
            model.addAttribute("user",user);
            return "user/user-error-form";
        }
        userDao.create(user);
        return "redirect:/users";
    }
    @GetMapping
    public String read(@RequestParam(value = "status", defaultValue = "") String status,Model model){
        if (status.equals("error")) {
            model.addAttribute("error","You cannot delete this user!!!");
        }
        List<UserDto> userDtoList = userDao.read();
        model.addAttribute("users",userDtoList);
        return "user/view-users";
    }
    @PostMapping("/edit")
    public String update(User user,Model model) {
        boolean update = userDao.update(user);
        if (update) {
            return "redirect:/users";
        }

        return "redirect:/users/edit?id=" + user.getId()+"&status=error";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        int delete = userDao.delete(id);
        if (delete==1) {
            return "redirect:/users";
        }
        return "redirect:/users?status=error";
    }

    @GetMapping("/{id}")
    public String readById(@PathVariable("id") int id, Model model) {
        User user = userDao.readById(id);
        model.addAttribute("user", user);
        return "user/user-by-id";
    }

    @GetMapping({"/get-form", "/edit"})
    public String getForm(@RequestParam(value = "id", required = false) Integer id,@RequestParam(value = "status" , defaultValue = "") String status,Model model) {
        if (id != null) {
            User user = userDao.readById(id);
            if (status.equals("error")) {
                    model.addAttribute("error","This phone number has already signed up!!!");
                    user.setPhoneNumber("");
            }
            model.addAttribute("user", user);
        }
        return "user/user-form";
    }
}
