package com.kjawank.forohub.domain.course;

import com.kjawank.forohub.domain.topic.DataTopic;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public record DataCourseTopics(
        @NotNull
        DataCourse curso,
        @NotNull
        Page<DataTopic> topicos
) {
}
