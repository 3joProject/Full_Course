package com.fullcourse.board;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private LocalDateTime boardDate;
    private String boardImage;
    private MultipartFile file;
    
    
}
