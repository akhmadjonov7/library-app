package uz.pdp.libraryapp.book;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.libraryapp.author.AuthorDao;
import uz.pdp.libraryapp.category.CategoryDao;
import uz.pdp.libraryapp.language.LanguageDao;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    // FIELD INJECTION
    // SETTER INJECTION
    // CONSTRUCTOR INJECTION


    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final LanguageDao languageDao;
    private final CategoryDao categoryDao;


    @PostMapping
    public String create(BookDto bookDto, Model model) {
        System.out.println("Some changes");
        System.out.println("Ba'zi o'zgarishlar");
        boolean exist = bookDao.exist(bookDto.getIsbn());
        if (!exist) {
            bookDao.create(bookDto);
            return "redirect:/books";
        }
        model.addAttribute("book",bookDto);
        model.addAttribute("authorList", authorDao.read());
        model.addAttribute("languagesList", languageDao.read());
        model.addAttribute("categoriesList", categoryDao.read());
        return "book/book-form-error";
    }

    @GetMapping
    public String read(@RequestParam(value = "status", defaultValue = "") String status,Model model) {
        if (status.equals("error")) {
            model.addAttribute("error","You cannot delete this book!!!");
        }
        List<BookDto> allBooksFromDb = bookDao.read();
        model.addAttribute("kitoblar", allBooksFromDb);
        return "book/view-books";
    }

    @PostMapping("/edit")
    public String update(BookDto bookDto) {
        boolean update = bookDao.update(bookDto);
        if (update) {
            return "redirect:/books";
        }
        return "redirect:/books/edit?id="+bookDto.getId()+"&status=error";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        boolean delete = bookDao.delete(id);
        if (delete) {
            return "redirect:/books";
        }
        return "redirect:/books?status=error";
    }

    @GetMapping({"/get-form", "/edit"})
    public String getForm(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "status", defaultValue = "") String status,Model model) {
        if (id != null) {
            BookDto bookDto = bookDao.readById(id);
            model.addAttribute("book", bookDto);
            if (status.equals("error")) {
                model.addAttribute("error","We have book with this ISBN");
                bookDto.setIsbn(null);
            }
        }

        model.addAttribute("authorList", authorDao.read());
        model.addAttribute("languagesList", languageDao.read());
        model.addAttribute("categoriesList", categoryDao.read());
        return "book/book-form";
    }

    @GetMapping("/{id}")
    public String readById(@PathVariable("id") int id, Model model) {
        BookDto bookDto = bookDao.readById(id);
        model.addAttribute("book", bookDto);
        return "book/book-by-id";
    }

    @GetMapping("/add-amount/{id}")
    public String addAmount(@PathVariable("id") int id,Model model){
        model.addAttribute("bookId",id);
        return "book/add-form";
    }
    @PostMapping("/add")
    public String addToAmount(BookDto bookDto){
        bookDao.addToAmount(bookDto);
        return "redirect:/books";
    }
}
