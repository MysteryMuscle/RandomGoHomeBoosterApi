package org.mysterymuscle.randomgohomebooster.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeckDto {

    private Long id;
    private String name;
    private String description;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
