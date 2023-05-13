package com.github.jumagaliev1.backendAssignment.model.request;

import lombok.*;
import java.util.*;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {
    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long source_id;

    @NotNull
    private List<Long> topic_id;
}
