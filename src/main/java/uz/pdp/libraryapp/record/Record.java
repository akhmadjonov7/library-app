package uz.pdp.libraryapp.record;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Record {
    private int id;
    private boolean isReturned;
    private LocalDateTime dateTime;
    private int userId;
}
