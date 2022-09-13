package uz.pdp.libraryapp.record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.libraryapp.book.Book;
import uz.pdp.libraryapp.user.UserDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecordDto{
    private int id;
    private boolean isReturned;
    private LocalDateTime dateTime;
//    private LocalDateTime date = LocalDateTime.parse(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm")));
    private int userId;
    private int bookId;
    private String bookTitle;
    private String userFullName;
    private List<UserDto> userDtoList;
    private List<Book> bookList;
    private List<Integer> booksId;
}
