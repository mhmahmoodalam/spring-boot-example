package com.fev.blog.blogger_app.dto.tags;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TagsListResponse {

    private List<String> tags;

}
