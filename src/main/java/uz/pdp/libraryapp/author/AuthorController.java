package uz.pdp.libraryapp.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    public final AuthorDao authorDao;

    @PostMapping
    public String create(Author author) {
        authorDao.create(author);
        return "redirect:/authors";
    }

    @GetMapping
    public String read(@RequestParam(value = "status", defaultValue = "") String status, Model model) {
        if (status.equals("error")) {
            model.addAttribute("error","You cannot delete this author!!!");
        }
        List<AuthorDto> authors = authorDao.read();
        model.addAttribute("authors", authors);
        return "author/view-authors";
    }

    @PostMapping("/edit")
    public String update(Author author) {
        authorDao.update(author);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        int delete = authorDao.delete(id);
        if (delete==1) {
            return "redirect:/authors";
        }
        return "redirect:/authors?status=error";
    }

    @GetMapping("/{id}")
    public String readById(@PathVariable("id") int id, Model model) {
        Author author = authorDao.readById(id);
        model.addAttribute("author", author);
        return "author/author-by-id";
    }

    @GetMapping("/get-add-form")
    public String getAddForm() {
        return "author/author-add-form";
    }
    @GetMapping("/get-edit-form/{id}")
    public String getEditForm(@PathVariable("id") Integer id, Model model){
        Author author = authorDao.readById(id);
        model.addAttribute("author", author);
        return "/author/author-edit-form";
    }

}
