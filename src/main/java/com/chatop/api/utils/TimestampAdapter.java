package com.chatop.api.utils;

import java.sql.Timestamp;

public class TimestampAdapter {

    public static Timestamp convertTimestampFromOpenApi(String openApiTimestamp) {
        String convert = openApiTimestamp.replace('T', ' ');
        convert = convert.replace('Z', ' ');
        return Timestamp.valueOf(convert);
    }
}
