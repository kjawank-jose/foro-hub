package com.kjawank.forohub.domain.topic;

import com.kjawank.forohub.domain.topic.answer.DataResponse;

public record StatusTopicResponse(
        DataTopic topico,
        DataResponse respuesta
) {
}
