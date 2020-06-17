package com.example.student.kafka

import com.alibaba.fastjson.JSON
import org.apache.kafka.common.serialization.Serializer


class StudentJsonSerializer : Serializer<Any?> {
    override fun configure(configs: Map<String?, *>?, isKey: Boolean) {}
    override fun serialize(topic: String, data: Any?): ByteArray {
        return JSON.toJSONBytes(data)
    }

    override fun close() {}
}