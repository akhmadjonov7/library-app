package uz.pdp.libraryapp.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryDao categoryDao;

    @PostMapping
    public String create(Category category) {
        categoryDao.create(category);
        return "redirect:/categories";
    }

    @GetMapping
    public String read(@RequestParam(value = "status", defaultValue = "") String status,Model model) {
        if (status.equals("error")) {
            model.addAttribute("error","You cannot delete this category!!!");
        }
        List<CategoryDto> categories = categoryDao.read();
        model.addAttribute("categories", categories);
        return "category/view-categories";
    }

    @PostMapping("/edit")
    public String update(Category category) {
        categoryDao.update(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        int delete = categoryDao.delete(id);
        if (delete==1) {
            return "redirect:/categories";
        }
        return "redirect:/categories?status=error";
    }

    @GetMapping({"/get-form", "/edit"})
    public String getForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            Category category = categoryDao.readById(id);
            model.addAttribute("category", category);
        }
        return "category/category-form";
    }

    @GetMapping("/{id}")
    public String readById(@PathVariable("id") int id, Model model) {
        Category category = categoryDao.readById(id);
        model.addAttribute("category", category);
        return "category/category-by-id";
    }
}
