package com.kjawank.forohub.domain.topic;

import com.kjawank.forohub.domain.topic.answer.DataResponse;
import org.springframework.data.domain.Page;

public record DataTopicResponses(
        DataTopic topico,
        Page<DataResponse> respuestas) {

}
