package uz.pdp.libraryapp.record;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.libraryapp.book.Book;
import uz.pdp.libraryapp.book.BookDao;
import uz.pdp.libraryapp.book.BookDto;
import uz.pdp.libraryapp.user.UserDao;
import uz.pdp.libraryapp.user.UserDto;

import java.util.List;

@Controller
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {
    public final UserDao userDao;
    public final BookDao bookDao;
    public final RecordDao recordDao;
    @PostMapping
    public String create(RecordDto recordDto){
        List<String> booksCannotGet = recordDao.create(recordDto);
        if (booksCannotGet.size()==0) {
            return "redirect:/records";
        }
        return "redirect:/records?error=book";
    }
    @GetMapping
    public String read(@RequestParam(value = "error", defaultValue = "") String error, Model model){
        List<RecordDto> recordList = recordDao.read();
        model.addAttribute("records",recordList);
        switch (error){
            case "book" -> model.addAttribute("error", "We don't hava some books which you choose!!!");
            case "false" -> model.addAttribute("error", "You cannot delete this record!!!");
        }
        return "/record/view-records";
    }
    @PostMapping("/edit")
    public String update(RecordDto recordDto){
        recordDao.update(recordDto);
        return "redirect:/records";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        boolean delete = recordDao.delete(id);
        if (!delete) {
            return "redirect:/records?error=false";
        }
        return "redirect:/records";
    }
    @GetMapping("/return/{id}")
    public String returned(@PathVariable("id") int id){
        recordDao.returned(id);
        return "redirect:/records";
    }
    @GetMapping({"/get-form","/edit/{id}"})
    public String getForm(@PathVariable(value = "id", required = false) Integer id, Model model){
        if (id!=null){
            RecordDto recordDto = recordDao.readToEdit(id);
            for (Book book : recordDto.getBookList()) {
                System.out.println("book = " + book);
            }

            model.addAttribute("record",recordDto);
        }
        List<Book> books = recordDao.readBooksToEdit();

        List<UserDto> userDtoList = userDao.read();
        model.addAttribute("bookList",books);
        model.addAttribute("userDtoList",userDtoList);
        return "record/record-form";
    }
//    @GetMapping("/not-return/{id}")
//    public String notReturned(@PathVariable("id") int id){
//        recordDao.notReturned(id);
//        return "redirect:/records";
//    }

}
