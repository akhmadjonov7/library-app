package uz.pdp.libraryapp.language;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {
    public final LanguageDao languageDao;

    @PostMapping
    public String create(Language lang) {
        languageDao.create(lang);
        return "redirect:/languages";
    }

    @GetMapping
    public String read(@RequestParam(value = "status", defaultValue = "") String status,Model model) {
        if (status.equals("error")) {
            model.addAttribute("error","You cannot delete this language!!!");
        }
        List<Language> lang = languageDao.read();
        model.addAttribute("languages", lang);
        return "language/view-languages";
    }

    @PostMapping("/edit")
    public String update(Language lang) {
        languageDao.update(lang);
        return "redirect:/languages";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        int delete = languageDao.delete(id);
        if (delete == 1) {
            return "redirect:/languages";
        }
        return "redirect:/languages?status=error";
    }

    @GetMapping({"/get-form", "/edit"})
    public String getForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            Language lang = languageDao.readById(id);
            model.addAttribute("lang", lang);
        }
        return "language/language-form";
    }
}
