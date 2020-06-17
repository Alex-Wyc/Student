package com.example.student.kafka

import com.alibaba.fastjson.JSON
import org.apache.kafka.common.serialization.Deserializer


class StudentJsonDeserializer : Deserializer<Any> {
    override fun configure(configs: Map<String?, *>?, isKey: Boolean) {}
    override fun deserialize(topic: String, data: ByteArray): Any {
        return JSON.parseObject(data, Any::class.java)
    }

    override fun close() {}
}